package co.edu.uni.acme.airline.flight.management.service.Impl;

import co.edu.uni.acme.aerolinea.commons.dto.UserAuditDto;
import co.edu.uni.acme.aerolinea.commons.dto.UserDTO;
import co.edu.uni.acme.aerolinea.commons.entity.*;
import co.edu.uni.acme.airline.flight.management.dto.PaymentRequestDto;
import co.edu.uni.acme.airline.flight.management.dto.PaymentResponseDto;
import co.edu.uni.acme.airline.flight.management.repository.PaymentCardRepository;
import co.edu.uni.acme.airline.flight.management.repository.PaymentFlightUserRepository;
import co.edu.uni.acme.airline.flight.management.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentCardRepository cardRepo;
    private final PaymentRepository paymentRepo;
    private final PaymentFlightUserRepository paymentFlightUserRepository;
    private final UserAuditService userAuditService;

    /**
     * 1) Busca la tarjeta por número.
     * 2) Verifica que el titular, mes y año de expiración coincidan.
     * 3) (CVV: sólo checamos longitud mínima; no lo guardamos ni comparamos en BD)
     * 4) Verifica saldo suficiente.
     * 5) Descuenta el monto, guarda tarjeta y crea el registro en payment.
     */
    @Transactional
    public PaymentResponseDto processPayment(PaymentRequestDto request) {
        // --- 1) Buscar por número de tarjeta ---
        PaymentCardEntity tarjeta = cardRepo.findByCardNumber(request.getCardNumber())
                .orElse(null);
        if (tarjeta == null) {
            return new PaymentResponseDto(null, "DECLINED", "La tarjeta no existe");
        }

        // --- 2) Verificar titular, mes y año de expiración ---
        if (!tarjeta.getCardHolderName().equalsIgnoreCase(request.getCardHolder())) {
            return new PaymentResponseDto(null, "DECLINED", "Titular de tarjeta inválido");
        }
        if (!tarjeta.getExpiryMonth().equals(request.getExpiryMonth()) ||
                !tarjeta.getExpiryYear().equals(request.getExpiryYear())) {
            return new PaymentResponseDto(null, "DECLINED", "Fecha de expiración inválida");
        }

        // --- 3) Validar CVV mínimo (solo formato) ---
        String cvv = request.getCvv();
        if (cvv == null || cvv.length() < 3 || cvv.length() > 4) {
            return new PaymentResponseDto(null, "DECLINED", "CVV inválido");
        }

        // --- 4) Verificar saldo suficiente ---
        BigDecimal montoSolicitado = BigDecimal.valueOf(request.getAmount());
        BigDecimal disponible = BigDecimal.valueOf(tarjeta.getAvailableAmount());
        if (disponible.compareTo(montoSolicitado) < 0) {
            return new PaymentResponseDto(null, "DECLINED", "Saldo insuficiente en la tarjeta");
        }

        // --- 5) Crear registro en payment_flight_user ---
        // Generamos un código único para payment_flight_user
        String codigoPfUser = "PFU" + UUID.randomUUID().toString().substring(0, 8);
        FlightEntity flight = new FlightEntity();
        flight.setCodeFlight(request.getCodeFlight());
        PassengerEntity passenger = new PassengerEntity();
        passenger.setCode(request.getCodePassenger());
        PaymentFlightUserEntity pfUser = new PaymentFlightUserEntity();
        pfUser.setCodeUserFlightUser(codigoPfUser);
        pfUser.setCodeFlightFk(flight);        // requerido en DTO
        pfUser.setCodePassengerFk(passenger);  // requerido en DTO
        pfUser = paymentFlightUserRepository.save(pfUser);

        // --- 5) Descontar monto y guardar tarjeta ---
        Double nuevoSaldo = disponible.subtract(montoSolicitado).doubleValue();
        tarjeta.setAvailableAmount(nuevoSaldo);
        cardRepo.save(tarjeta);

        // --- 6) Crear registro en payment ---
        String nuevoCodigoPago = "PAY" + UUID.randomUUID().toString().substring(0, 8);
        PaymentEntity pago = new PaymentEntity();
        pago.setCodePayment(nuevoCodigoPago);
        pago.setDateCreated(LocalDate.now());
        pago.setDatePayment(LocalDate.now());
        pago.setCodePaymentFlightPassengerFk(pfUser);
        pago.setTimePayment(LocalTime.now());
        pago.setTotalPayment(montoSolicitado.toPlainString());
        paymentRepo.save(pago);

        UserAuditDto userAuditDto = new UserAuditDto();
        UserDTO userDTO = new UserDTO();
        userDTO.setCodeUser("U001");
        userAuditDto.setAction("INSERT INTO to payment, payment_card, payment_flight_user and SELECT to payment_card");
        userAuditDto.setCodeUser(userDTO);
        userAuditService.saveAuditUser(userAuditDto);

        return new PaymentResponseDto(nuevoCodigoPago, "APPROVED", "Pago procesado correctamente");
    }
}
