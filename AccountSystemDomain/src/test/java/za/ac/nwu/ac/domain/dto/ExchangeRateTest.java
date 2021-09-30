package za.ac.nwu.ac.domain.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;

@SpringBootTest (classes = {ExchangeRate.class})
class ExchangeRateTest {

    @Test
    void calculateDollarAmount() {
        ExchangeRate exchangeRate = new ExchangeRate("RSA","South Africa",'R',14.90);
        Assertions.assertEquals(exchangeRate.calculateDollarAmount(100.0),6.71);
    }
}