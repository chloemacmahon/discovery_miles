package za.ac.nwu.ac.domain.dto.exchange_rate;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Component
public class ExchangeRate {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long exchangeRateId;

    @Column (length = 3)
    @Size(max = 3)
    private String countryCode;

    private String countryName;

    private char currencySymbol;

    private double toDollarExchangeRate;

    public ExchangeRate() {
    }

    public ExchangeRate(@Size(max = 3) String countryCode, String countryName, char currencySymbol, double toDollarExchangeRate) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencySymbol = currencySymbol;
        this.toDollarExchangeRate = toDollarExchangeRate;
    }

    public double calculateDollarAmount(double amountToExchange) {
        return (double)Math.round(amountToExchange / getToDollarExchangeRate()*100)/100;
    }
}
