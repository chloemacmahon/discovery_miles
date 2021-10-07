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

    /**
     * Represents a exchange rate for a country
     */

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long exchangeRateId;

    @Column (length = 3, unique = true)
    private String countryCode;

    @Column (unique = true)
    private String countryName;

    private char currencySymbol;

    private double toRandExchangeRate;

    public ExchangeRate() {
    }

    /**
     *
     * @param countryCode The unique 3 letter country code
     * @param countryName The country's name
     * @param currencySymbol The currency symbol for  the country
     * @param toRandExchangeRate The exchange rate to South African rands
     */

    public ExchangeRate(String countryCode, String countryName, char currencySymbol, double toRandExchangeRate) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencySymbol = currencySymbol;
        this.toRandExchangeRate = toRandExchangeRate;
    }

    /**
     * Calculates the rand value of the amount to exchange
     * @param amountToExchange The amount of the currency that the user wants to exchange into South African rands
     * @return Returns the amount of rands that would be received for said amount
     */

    public double calculateRandAmount(double amountToExchange) {
        return (double)Math.round(getToRandExchangeRate()*amountToExchange *100)/100;
    }
}
