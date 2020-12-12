package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Page;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.exception.NotFoundException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class HistoricalExchangeRatesRepository implements HistoricalExchangeRatesPort {

    @Inject
    HistExRateRepository repository;

    @Override
    public List<ExchangeRate> findAll(Integer offset, Integer limit) {
        Page p = Page.of(offset, limit);
        return mappertToExchangeRate(repository.findAll(p));
    }

    @Override
    public List<ExchangeRate> findAllByIdentity_idCurrency(String currency, Integer offset, Integer limit) {
        Page p = Page.of(offset, limit);
        return mappertToExchangeRate(repository.findAllByIdentity_idCurrencyIgnoreCase(currency, p));
    }

    @Override
    public List<ExchangeRate> findAllByIdentity_idDate(LocalDate date, Integer offset, Integer limit) {
        Page p = Page.of(offset, limit);
        return mappertToExchangeRate(repository.findAllByIdentity_idDate(date, p));
    }

    @Override
    public ExchangeRate findByIdentity_idCurrencyAndIdentity_idDate(String currency, LocalDate date) {
        HistExRate histExRate = repository.findByIdentity_idCurrencyAndIdentity_idDate(currency, date);
        return histExRate != null ? mapperToExchangeRate(histExRate) : null;
    }

    @Transactional
    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        repository.persist(mapperToHistExRate(exchangeRate));
        return exchangeRate;
    }

    @Transactional
    @Override
    public void
    updateHistExRate(ExchangeRate exchangeRate) {
        repository.findByIdOptional(mapperToHistExRate(exchangeRate).getIdentity()).orElseThrow(()->new NotFoundException("Record not found"));
        Panache.getEntityManager().merge(mapperToHistExRate(exchangeRate));
    }

    @Transactional
    @Override
    public void deleteHistExRate(String currency, LocalDate data) {
        repository.findByIdOptional(new HistExRateIdentity(currency, data)).orElseThrow(() -> new NotFoundException("Record not found"));
        repository.deleteById(new HistExRateIdentity(currency, data));
    }

    private ExchangeRate mapperToExchangeRate(HistExRate HistExRate) {
        ExchangeRate cs = null;
        if (HistExRate != null && HistExRate.getIdentity() != null && HistExRate.getIdentity().getIdCurrency() != null) {
            cs = new ExchangeRate();
            cs.setRate(HistExRate.getRate());
            cs.setDate(HistExRate.getIdentity().getIdDate());
            cs.setCurrency(HistExRate.getIdentity().getIdCurrency());
        }
        return cs;
    }

    private List<ExchangeRate> mappertToExchangeRate(Iterable<HistExRate> i) {
        List<ExchangeRate> res = new ArrayList<>();

        for (HistExRate HistExRate : i) {
            res.add(mapperToExchangeRate(HistExRate));
        }

        return res;
    }

    private HistExRate mapperToHistExRate(ExchangeRate cs) {
        HistExRate e = new HistExRate();
        e.setRate(cs.getRate());
        HistExRateIdentity identity = new HistExRateIdentity(cs.getCurrency(), cs.getDate());
        e.setIdentity(identity);
        return e;
    }
}
