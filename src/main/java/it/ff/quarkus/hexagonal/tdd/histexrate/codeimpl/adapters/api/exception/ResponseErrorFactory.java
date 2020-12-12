package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.api.exception;

import it.ff.quarkus.hexagonal.tdd.histexrate.codegen.model.ResponseError;

/**
 * Build the error object used in response object
 */
public class ResponseErrorFactory {
    private ResponseErrorFactory() {
    }

    public static ResponseError create(String code, String description) {
        ResponseError responseError = new ResponseError();
        responseError.setCode(code);
        responseError.setDescription(description);

        return responseError;
    }
}
