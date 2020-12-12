package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.api;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.api.HistoricRateApiService;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ExchangeRate;
import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.HistoricRatesList;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.exception.NotFoundException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.BadRequestException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.GenericServiceException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.ServiceUnavailableException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.port.HistoricalExchangeRatesUseCase;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.time.LocalDate;
import java.util.List;

@RequestScoped
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen", date = "2020-10-19T20:58:13.138012+02:00[Europe/Rome]")
public class HistoricRateApiServiceImpl implements HistoricRateApiService {

    @Inject
    HistoricalExchangeRatesUseCase historicalExchangeRatesUseCase;

    public Response deleteExchangeRate(String currency, LocalDate date, SecurityContext securityContext)
            throws NotFoundException {
        if (StringUtils.isEmpty(currency) || date == null) {
            throw new BadRequestException("Currency and date must not be null.");
        }

        try {
            historicalExchangeRatesUseCase.deleteExchangeRate(currency, date);
        } catch (ServiceUnavailableException | NotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new GenericServiceException(e);
        }
        return Response.noContent()
                .build();
    }

    public Response getHistoricRates(String currency, LocalDate date, Integer page, Integer size, SecurityContext securityContext)
            throws NotFoundException {
        HistoricRatesList historicRatesList = new HistoricRatesList();

        try {
            List l = historicalExchangeRatesUseCase.getHistoricExchangeRate(page, size, currency, date);
            historicRatesList.setHistoricRates(l);
        } catch (ServiceUnavailableException ex) {
            throw ex;
        } catch (Exception e) {
            throw new GenericServiceException(e);
        }

        return Response.ok(historicRatesList, MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public Response insertExchangeRate(ExchangeRate exchangeRate, SecurityContext securityContext)
            throws NotFoundException {

        ExchangeRate response;
        try {
            response = historicalExchangeRatesUseCase.insertExchangeRate(exchangeRate);
        } catch (ServiceUnavailableException ex) {
            throw ex;
        } catch (Exception e) {
            throw new GenericServiceException(e);
        }
        return Response.status(Response.Status.CREATED)
                .entity(response)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public Response updateExchangeRate(ExchangeRate exchangeRate, SecurityContext securityContext)
            throws NotFoundException {
        try {
            historicalExchangeRatesUseCase.updateExchangeRate(exchangeRate);
        } catch (ServiceUnavailableException | NotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new GenericServiceException(e);
        }
        return Response.noContent()
                .build();
    }
}
