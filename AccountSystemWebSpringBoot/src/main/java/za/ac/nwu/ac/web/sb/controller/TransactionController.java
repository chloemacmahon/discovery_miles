package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;
import za.ac.nwu.ac.domain.exception.FailedToCreateExchangeRate;
import za.ac.nwu.ac.logic.CurrencyConverterService;

import java.util.List;

@RestController
public class TransactionController {

    private CurrencyConverterService currencyConverterService;

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

    @RequestMapping(value = "/view-all-exchange-rate", method = RequestMethod.GET)
    public List<ExchangeRate> getAllExchangeRates() {
        try {
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate();
        }
    }

    @RequestMapping(value = "/find-exchange-rate-by-country-code", method = RequestMethod.GET)
    public ExchangeRate findExchangeRateByCountryCode(@RequestBody String countryCode) {
        try {
            return currencyConverterService.findExchangeRateByCountryCode(countryCode);
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate();
        }
    }

    @RequestMapping(value = "/thow-exception-and-rollback" , method = RequestMethod.GET)
    public List<ExchangeRate> throwExceptionAndRollback(@RequestBody ExchangeRate exchangeRate) {
        try{
            currencyConverterService.throwExceptionAndRollback(exchangeRate);
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e){
            return currencyConverterService.findAllExchangeRates();
        }
    }

    /*@RequestMapping(value = "/update-exchange-rate", method = RequestMethod.GET)
    public List<ExchangeRate> updateExchangeRate(@RequestBody ) {
        try {
            return currencyConverterService.findAllExchangeRates();
        } catch (RuntimeException e) {
            throw new FailedToCreateExchangeRate();
        }
    }*/

}
