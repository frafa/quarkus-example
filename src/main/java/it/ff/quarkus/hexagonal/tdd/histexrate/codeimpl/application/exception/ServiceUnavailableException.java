package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.application.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(Exception e) {
        super(e);
    }

    public ServiceUnavailableException(String message) {
        super(message);
    }
}
