package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;

import java.time.LocalDate;
import java.util.List;

public interface HistoricalExchangeRatesPort {
    List<ExchangeRate> findAll(Integer offset, Integer limit);

    List<ExchangeRate> findAllByIdentity_idCurrency(String currency, Integer offset, Integer limit);

    List<ExchangeRate> findAllByIdentity_idDate(LocalDate dateRate, Integer offset, Integer limit);

    ExchangeRate findByIdentity_idCurrencyAndIdentity_idDate(String currency, LocalDate dateRate);

    ExchangeRate save(ExchangeRate ExchangeRate);

    void updateHistExRate(ExchangeRate ExchangeRate);

    void deleteHistExRate(String currency, LocalDate data);
}
