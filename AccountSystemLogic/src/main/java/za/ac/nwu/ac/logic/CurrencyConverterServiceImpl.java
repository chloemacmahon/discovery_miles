package za.ac.nwu.ac.logic;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;
import za.ac.nwu.ac.repository.ExchangeRateRepository;

public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencyConverterServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public double convertToRand(double amountToConvert, ExchangeRate exchangeRate){
        return exchangeRate.calculateRandAmount(amountToConvert);
    }

    /*public void createExchangeRate(ExchangeRate exchangeRate){
        exchangeRateRepository.save(exchangeRate);
    }*/

}
