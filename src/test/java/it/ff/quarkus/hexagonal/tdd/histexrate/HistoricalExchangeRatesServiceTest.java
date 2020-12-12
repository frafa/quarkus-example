package it.ff.quarkus.hexagonal.tdd.histexrate;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.ServiceUnavailableException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesPort;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.service.HistoricalExchangeRatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@QuarkusTest
public class HistoricalExchangeRatesServiceTest {

    @Inject
    private HistoricalExchangeRatesService historicalExchangeRatesService;

    @InjectMock
    private HistoricalExchangeRatesPort historicalExchangeRatesPort;

    @Test
    public void getHistoricExchangeRate_returnListExchangeRate() {
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.82));
        c.setCurrency("CHF");
        c.setDate(LocalDate.parse("2020-10-01"));
        ExchangeRate c2 = new ExchangeRate();
        c2.setRate(BigDecimal.valueOf(0.78));
        c2.setCurrency("CHF");
        c2.setDate(LocalDate.parse("2020-10-03"));
        when(historicalExchangeRatesPort.findAllByIdentity_idCurrency(anyString(), any(Integer.class), any(Integer.class))).thenReturn(Arrays.asList(c, c2));

        List<ExchangeRate> l = historicalExchangeRatesService.getHistoricExchangeRate(null, null, "CHF", null);

        Assertions.assertEquals(l.size(), 2);
        Assertions.assertEquals(l.get(0).getCurrency(), "CHF");
    }

    @Test
    public void getHistoricExchangeRate_returnOnlyOneExchangeRate() {
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.82));
        c.setCurrency("CHF");
        c.setDate(LocalDate.parse("2020-10-01"));
        when(historicalExchangeRatesPort.findAllByIdentity_idCurrency("CHF", 0, 1)).thenReturn(Collections.singletonList(c));

        List<ExchangeRate> l = historicalExchangeRatesService.getHistoricExchangeRate(0, 1, "CHF", null);

        Assertions.assertEquals(l.size(), 1);
        Assertions.assertEquals(l.get(0).getCurrency(), "CHF");
        Assertions.assertEquals(l.get(0).getDate(), LocalDate.parse("2020-10-01"));
    }

    @Test
    public void insertHistoricExchangeRate_returnExchangeRate() {
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.82));
        c.setCurrency("CHF");
        c.setDate(LocalDate.parse("2020-10-01"));
        when(historicalExchangeRatesPort.save(c)).thenReturn(c);

        ExchangeRate c1 = historicalExchangeRatesService.insertExchangeRate(c);
        Assertions.assertEquals(c1,c);
    }

    @Test
    public void insertHistoricExchangeRate_throwException() {
        ExchangeRate c = new ExchangeRate();
        c.setRate(BigDecimal.valueOf(0.82));
        c.setCurrency("CHF");
        c.setDate(LocalDate.parse("2020-10-01"));
        when(historicalExchangeRatesPort.save(c)).thenThrow(new RuntimeException());

        assertThrows(ServiceUnavailableException.class, () -> historicalExchangeRatesService.insertExchangeRate(c));
    }

//    @Test
//    public void deleteHistoricExchangeRate_throwEmptyResultDataAccessException() {
//        doThrow(EmptyResultDataAccessException.class).when(HistoricalExchangeRatesPort).deleteHistExRate("CHF", LocalDate.parse("2020-01-01"));
//
//        assertThrows(EmptyResultDataAccessException.class, () -> HistoricalExchangeRatesService.deleteExchangeRate("CHF", LocalDate.parse("2020-01-01")));
//    }

    @Test
    public void deleteHistoricExchangeRate_throwException() {
        doThrow(RuntimeException.class).when(historicalExchangeRatesPort).deleteHistExRate("CHF", LocalDate.parse("2020-01-01"));

        assertThrows(ServiceUnavailableException.class, () -> historicalExchangeRatesService.deleteExchangeRate("CHF", LocalDate.parse("2020-01-01")));
    }

//    @Test
//    public void updateHistoricExchangeRate_returnUpdatedElement() {
//        ExchangeRate c = new ExchangeRate();
//        c.setRate(BigDecimal.valueOf(0.82));
//        c.setCurrency("CHF");
//        c.setDate(LocalDate.parse("2020-10-01"));
//        when(historicalExchangeRatesPort.updateHistExRate(c)).thenReturn(1);
//
//        int i = HistoricalExchangeRatesService.updateExchangeRate(c);
//
//        assertThat(i).isEqualTo(1);
//    }

//    @Test
//    public void updateHistoricExchangeRate_throwException() {
//        ExchangeRate c = new ExchangeRate().rate(BigDecimal.valueOf(0.82)).currency("CHF").date(LocalDate.parse("2020-10-01"));
//        when(HistoricalExchangeRatesPort.updateHistExRate(c)).thenThrow(new RuntimeException());
//
//        assertThrows(ServiceUnavailableException.class, () -> HistoricalExchangeRatesService.updateExchangeRate(c));
//    }

}
