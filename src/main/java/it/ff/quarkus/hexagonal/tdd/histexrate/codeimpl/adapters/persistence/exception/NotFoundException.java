package it.ff.quarkus.hexagonal.tdd.histexrate.codeimpl.adapters.persistence.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {super();}

    public NotFoundException(Exception e) {
        super(e);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
