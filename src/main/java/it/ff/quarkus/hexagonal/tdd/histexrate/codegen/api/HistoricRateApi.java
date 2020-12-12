package it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api;

import io.swagger.annotations.ApiParam;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.HistoricRatesList;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ResponseError;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;

@Path("/historic-rate")


@io.swagger.annotations.Api(description = "the historic-rate API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class HistoricRateApi  {

    @Inject HistoricRateApiService service;

    @DELETE
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete an historical rate record", notes = "", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Record delete.", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not found", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Service Unavailable", response = ResponseError.class) })
    public Response deleteExchangeRate(  @QueryParam("currency") String currency,  @QueryParam("date") LocalDate date,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.deleteExchangeRate(currency,date,securityContext);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve historic exchange rates.", notes = "Retrieve a list oh historic rate according to parameters. The number of elements returned depends on page and size parameter.", response = HistoricRatesList.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Return a list of historical exchange rates.", response = HistoricRatesList.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Service Unavailable", response = ResponseError.class) })
    public Response getHistoricRates(  @QueryParam("currency") String currency,  @QueryParam("date") LocalDate date,  @QueryParam("page") Integer page,  @QueryParam("size") Integer size,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.getHistoricRates(currency,date,page,size,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Insert or update an historical rate.", notes = "Insert new historical rate record, if already exists a record with the same currency and date it will be update. ", response = ExchangeRate.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response.", response = ExchangeRate.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Service Unavailable", response = ResponseError.class) })
    public Response insertExchangeRate(@ApiParam(value = "Il nuovo elemento dello storico cambi da creare" ,required=true) @NotNull @Valid ExchangeRate exchangeRate,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.insertExchangeRate(exchangeRate,securityContext);
    }
    @PUT
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an historical exchange rate", notes = "Update historical exchange rate record.", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Update successful.", response = Void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Not found", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal Server Error", response = ResponseError.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Service Unavailable", response = ResponseError.class) })
    public Response updateExchangeRate(@ApiParam(value = "Informazioni utili ad aggiornare il record" ,required=true) @NotNull @Valid ExchangeRate exchangeRate,@Context SecurityContext securityContext)
    throws NotFoundException {
        return service.updateExchangeRate(exchangeRate,securityContext);
    }
}
