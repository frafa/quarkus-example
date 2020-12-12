package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.service;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.exception.NotFoundException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.ServiceUnavailableException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesPort;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesUseCase;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class HistoricalExchangeRatesService implements HistoricalExchangeRatesUseCase {

    @Inject
    HistoricalExchangeRatesPort HistoricalExchangeRatesPort;

    @Override
    public List<ExchangeRate> getHistoricExchangeRate(Integer offset, Integer limit, String currency, LocalDate date) {
        int off = (offset == null || offset <= 0) ? 0 : offset - 1;
        int lim = (limit == null || limit <= 0) ? 10 : limit;

        List<ExchangeRate> l;

        try {
            if (StringUtils.isEmpty(currency) && date == null) {
                l = HistoricalExchangeRatesPort.findAll(off, lim);
            } else if (!StringUtils.isEmpty(currency) && date == null) {
                l = HistoricalExchangeRatesPort.findAllByIdentity_idCurrency(currency, off, lim);
            } else if (StringUtils.isEmpty(currency) && date != null) {
                l = HistoricalExchangeRatesPort.findAllByIdentity_idDate(date, off, lim);
            } else {
                l = Collections.singletonList(HistoricalExchangeRatesPort.findByIdentity_idCurrencyAndIdentity_idDate(currency, date));
            }

            return l;
        } catch (Exception e) {
            throw new ServiceUnavailableException(e);
        }
    }

    @Override
    public ExchangeRate insertExchangeRate(ExchangeRate ExchangeRate) {
        try {
            return HistoricalExchangeRatesPort.save(ExchangeRate);
        } catch (Exception e) {
            throw new ServiceUnavailableException(e);
        }
    }

    @Override
    public void updateExchangeRate(ExchangeRate ExchangeRate) {
        try {
            HistoricalExchangeRatesPort.updateHistExRate(ExchangeRate);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            throw new ServiceUnavailableException(e);
        }
    }

    @Override
    public void deleteExchangeRate(String currency, LocalDate date) {
        try {
            HistoricalExchangeRatesPort.deleteHistExRate(currency, date);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            throw new ServiceUnavailableException(e);
        }
    }
}
