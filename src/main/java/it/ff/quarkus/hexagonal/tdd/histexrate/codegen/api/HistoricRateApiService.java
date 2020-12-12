package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public interface HistoricRateApiService {
      Response deleteExchangeRate(String currency,LocalDate date,SecurityContext securityContext)
      throws NotFoundException;
      Response getHistoricRates(String currency,LocalDate date,Integer page,Integer size,SecurityContext securityContext)
      throws NotFoundException;
      Response insertExchangeRate(ExchangeRate exchangeRate,SecurityContext securityContext)
      throws NotFoundException;
      Response updateExchangeRate(ExchangeRate exchangeRate,SecurityContext securityContext)
      throws NotFoundException;
}
