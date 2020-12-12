package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api.impl;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api.ApiResponseMessage;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api.ConvertToApiService;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api.NotFoundException;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.time.LocalDate;

@RequestScoped
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class ConvertToApiServiceImpl implements ConvertToApiService {
      public Response getConvertedAmount(String currencyFrom,String currencyTo,BigDecimal amount,LocalDate date,SecurityContext securityContext)
      throws NotFoundException {
      // do some magic!
      return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
  }
}
