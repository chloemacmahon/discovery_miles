
package za.ac.nwu.ac.logic;

import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;

public interface CurrencyConverterService {

    double convertToRand(double amountToConvert, ExchangeRate exchangeRate);

    /*void createExchangeRate(ExchangeRate exchangeRate);*/
}

