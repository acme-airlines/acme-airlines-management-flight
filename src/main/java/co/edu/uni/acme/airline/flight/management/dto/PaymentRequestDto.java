package co.edu.uni.acme.airline.flight.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private String cardNumber;
    private String cardHolder;
    private Integer expiryMonth;
    private Integer expiryYear;
    private String cvv;
    private Double amount;
}
