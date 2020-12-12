package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.time.LocalDate;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public interface ConvertToApiService {
      Response getConvertedAmount(String currencyFrom,String currencyTo,BigDecimal amount,LocalDate date,SecurityContext securityContext)
      throws NotFoundException;
}
