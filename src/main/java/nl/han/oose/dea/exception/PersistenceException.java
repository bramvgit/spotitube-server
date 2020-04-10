package nl.han.oose.dea.exception;

public class PersistenceException extends RuntimeException {
    public PersistenceException(Exception cause) {
        System.out.println(cause.getMessage());
    }
}