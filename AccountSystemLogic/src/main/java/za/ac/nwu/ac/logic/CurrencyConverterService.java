

package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;

import java.util.List;

public interface CurrencyConverterService {

    double convertToRand(double amountToConvert, ExchangeRate exchangeRate);

    void createExchangeRate(ExchangeRate exchangeRate);

    ExchangeRate findExchangeRateByCountryCode(String countryCode);

    ExchangeRate updateExchangeRate(ExchangeRate exchangeRate, double toRandExchangeRate);

    List<ExchangeRate> findAllExchangeRates();

    double convertCurrencyToRand(double amountToConvert, String countryCode);

    void throwExceptionAndRollback(ExchangeRate exchangeRate);

}