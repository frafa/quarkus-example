package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.api.exception;

import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.BadRequestException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.exception.NotFoundException;
import it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception.ServiceUnavailableException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {

        if (e instanceof BadRequestException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ResponseErrorFactory.create(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()), e.getMessage()))
                    .build();
        } else if (e instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ResponseErrorFactory.create(String.valueOf(Response.Status.NOT_FOUND.getStatusCode()), e.getMessage()))
                    .build();
        } else if (e instanceof ServiceUnavailableException) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(ResponseErrorFactory.create(String.valueOf(Response.Status.SERVICE_UNAVAILABLE.getStatusCode()), e.getMessage()))
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ResponseErrorFactory.create(String.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()), e.getMessage()))
                    .build();
        }
    }
}
