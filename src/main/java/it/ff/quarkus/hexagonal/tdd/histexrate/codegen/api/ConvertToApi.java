package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ConvertedAmount;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ResponseError;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.time.LocalDate;

@Path("/convert-to")


@io.swagger.annotations.Api(description = "the convert-to API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class ConvertToApi  {

    @Inject ConvertToApiService service;

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Convert amount from base currency to another", notes = "", response = ConvertedAmount.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Return the amount converted from base currency to another, all the eschange rate used will be returned.", response = ConvertedAmount.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not found", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Service Unavailable", response = ResponseError.class) })
    public Response getConvertedAmount( @NotNull  @QueryParam("currency_from") String currencyFrom, @NotNull  @QueryParam("currency_to") String currencyTo, @NotNull  @QueryParam("amount") BigDecimal amount,  @QueryParam("date") LocalDate date,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getConvertedAmount(currencyFrom,currencyTo,amount,date,securityContext);
    }
}
