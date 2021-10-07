package za.ac.nwu.ac.web.sb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;
import za.ac.nwu.ac.domain.exception.FailedToCreateExchangeRate;
import za.ac.nwu.ac.logic.CurrencyConverterService;

import java.util.List;

@RestController
public class TransactionController {

    private CurrencyConverterService currencyConverterService;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @RequestMapping(value = "/create-exchange-rate", method = RequestMethod.POST)
    public void addExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        try {
            currencyConverterService.createExchangeRate(exchangeRate);
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate(e.getMessage());
        }
    }

    @RequestMapping(value = "/view-all-exchange-rate", method = RequestMethod.POST)
    public List<ExchangeRate> getAllExchangeRates() {
        try {
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate();
        }
    }

    @RequestMapping(value = "/find-exchange-rate-by-country-code", method = RequestMethod.POST)
    public ExchangeRate findExchangeRateByCountryCode(@RequestParam String countryCode) {
        try {
            return currencyConverterService.findExchangeRateByCountryCode(countryCode);
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate();
        }
    }

    @RequestMapping(value = "/throw-exception-and-rollback" , method = RequestMethod.POST)
    public List<ExchangeRate> throwExceptionAndRollback(@RequestBody ExchangeRate exchangeRate) {
        try{
            currencyConverterService.throwExceptionAndRollback(exchangeRate);
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e){
            logger.info("Error thrown and rollback occurred");
            return currencyConverterService.findAllExchangeRates();
        }
    }

    @RequestMapping(value = "/update-exchange-rate", method = RequestMethod.POST)
    public List<ExchangeRate> updateExchangeRate(@RequestParam String countryCode, @RequestParam double toRandExchangeRate) {
        try {
            currencyConverterService.updateExchangeRate(currencyConverterService.findExchangeRateByCountryCode(countryCode), toRandExchangeRate);
            LoggingController.logInfo("Updated exchange rate successfully");
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e) {
            LoggingController.logError("Could not update exchange rate");
            throw new FailedToCreateExchangeRate();
        }
    }

    @RequestMapping(value = "/convert-to-rand", method = RequestMethod.POST)
    public double convertToRand(@RequestParam String countryCode, @RequestParam double amountToConvert){
        try{
            try {
                currencyConverterService.findExchangeRateByCountryCode(countryCode);
            } catch (RuntimeException e){
                LoggingController.logError("Could not find exchange rate");
                return -1;
            }
            return currencyConverterService.convertToRand(amountToConvert, currencyConverterService.findExchangeRateByCountryCode(countryCode));
        } catch (RuntimeException e){
            LoggingController.logError("Could not convert to rand");
            return -1;
        }
    }
}
