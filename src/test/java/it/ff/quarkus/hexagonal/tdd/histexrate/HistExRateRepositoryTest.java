package it.ff.quarkus.hexagonal.tdd.histexrate;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.HistExRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.HistExRateIdentity;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.HistExRateRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestTransaction
@QuarkusTest
class HistExRateRepositoryTest {

    @Inject
    HistExRateRepository repository;

    @Test
    public void should_find_all_HistExRate() {
        List<HistExRate> l = new ArrayList<>();
        repository.findAll().stream().forEach(l::add);
        assertEquals(4, l.size());
    }

    @Test
    public void should_find_all_currency_HistExRate() {
        List<HistExRate> l = new ArrayList<>();
        repository.findAllByIdentity_idCurrencyIgnoreCase("CHF", null).forEach(l::add);
        assertEquals(2, l.size());
        assertTrue(l.stream().allMatch(s -> s.getIdentity().getIdCurrency().equalsIgnoreCase("CHF")));
    }

    @Test
    public void should_find_none_currency_HistExRate() {
        List<HistExRate> l = new ArrayList<>();
        repository.findAllByIdentity_idCurrencyIgnoreCase("TRY", null).forEach(l::add);
        assertEquals(0, l.size());
    }

    @Test
    public void should_find_currency_and_date_HistExRate() {
        HistExRate h = repository.findByIdentity_idCurrencyAndIdentity_idDate("CHF", LocalDate.parse("2020-01-01"));
        assertNotNull(h);
        assertTrue(h.getIdentity().getIdCurrency().equals("CHF") && h.getIdentity().getIdDate().equals(LocalDate.parse("2020-01-01")));
    }

    @Test
    public void should_throw_exception_on_delete_non_existent_HistExRate() {
        HistExRateIdentity s = new HistExRateIdentity("TRY", LocalDate.parse("2020-01-01"));
        assertFalse(repository.deleteById(s));
    }

    @Test
    public void should_insert_new_HistExRate() {
        HistExRate s = new HistExRate(new HistExRateIdentity("TRY", LocalDate.parse("2020-01-01")), BigDecimal.valueOf(0.56));
        repository.persist(s);
        assertTrue(repository.isPersistent(s));
    }
}