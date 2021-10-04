package za.ac.nwu.ac.domain.dto.exchange_rate;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Component
public class ExchangeRate {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long exchangeRateId;

    @Column (length = 3, unique = true)
    @Size(max = 3)
    @NotEmpty
    private String countryCode;

    @Column (length = 3, unique = true)
    @NotEmpty
    private String countryName;

    @NotEmpty
    private char currencySymbol;

    @NotEmpty
    private double toRandExchangeRate;

    public ExchangeRate() {
    }

    public ExchangeRate(@Size(max = 3) String countryCode, String countryName, char currencySymbol, double toRandExchangeRate) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencySymbol = currencySymbol;
        this.toRandExchangeRate = toRandExchangeRate;
    }

    public double calculateRandAmount(double amountToExchange) {
        return (double)Math.round(amountToExchange / getToRandExchangeRate()*100)/100;
    }
}
