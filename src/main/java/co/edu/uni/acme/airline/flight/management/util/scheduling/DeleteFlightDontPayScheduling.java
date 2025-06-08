package co.edu.uni.acme.airline.flight.management.util.scheduling;

import co.edu.uni.acme.airline.flight.management.service.IFlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteFlightDontPayScheduling {

    private final IFlightService flightService;

   // @Scheduled(fixedRate = 5000)
   @Scheduled(cron = "0 0/10 * * * *")
    public void tareaCada5Segundos() {
        flightService.deleteFlightDontPay();
    }

}
