package integration;

/**
 * Kastas när databasanslutningen misslyckas (simulerat).
 */
public class LagerDatabasException extends RuntimeException {
    public LagerDatabasException(String msg){
        super(msg);
    }
    }