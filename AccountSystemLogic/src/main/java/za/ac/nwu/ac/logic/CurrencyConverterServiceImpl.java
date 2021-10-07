
package za.ac.nwu.ac.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;
import za.ac.nwu.ac.domain.exception.FailedToCreateExchangeRate;
import za.ac.nwu.ac.domain.exception.FailedToFindExchangeRate;
import za.ac.nwu.ac.domain.exception.PurposefulException;
import za.ac.nwu.ac.repository.ExchangeRateRepository;

import javax.transaction.Transactional;
import java.util.List;

@EntityScan("za.ac.nwu.ac.domain.dto")
@Component
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencyConverterServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public double convertToRand(double amountToConvert, ExchangeRate exchangeRate){
        return exchangeRate.calculateRandAmount(amountToConvert);
    }

    public void createExchangeRate(ExchangeRate exchangeRate){
        try {
            exchangeRateRepository.save(exchangeRate);
        } catch (RuntimeException e){
            throw new FailedToCreateExchangeRate();
        }
    }

    public ExchangeRate findExchangeRateByCountryCode(String countryCode){
        try {
            return exchangeRateRepository.findByCountryCode(countryCode);
        } catch (RuntimeException e){
            throw new FailedToFindExchangeRate(e.getCause());
        }
    }

    public ExchangeRate updateExchangeRate(ExchangeRate exchangeRate, double toRandExchangeRate) {
        exchangeRate.setToRandExchangeRate(toRandExchangeRate);
        exchangeRateRepository.save(exchangeRate);
        return exchangeRate;
    }

    public List<ExchangeRate> findAllExchangeRates(){
        return exchangeRateRepository.findAll();
    }

    public double convertCurrencyToRand(double amountToConvert, String countryCode){
        try {
            ExchangeRate exchangeRate = findExchangeRateByCountryCode(countryCode);
            return exchangeRate.calculateRandAmount(amountToConvert);
        } catch (RuntimeException e) {
            throw new FailedToFindExchangeRate("Could not find exchange rate");
        }
    }

    @Transactional(rollbackOn = PurposefulException.class)
    public void throwExceptionAndRollback(ExchangeRate exchangeRate) throws PurposefulException{
        exchangeRateRepository.save(exchangeRate);
        /*logger.info("Item added to database");
        logger.info("Exception being thrown");*/
        throw new PurposefulException();
    }


}
