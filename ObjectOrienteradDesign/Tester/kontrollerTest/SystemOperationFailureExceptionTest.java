package test.controller;

import controller.SystemOperationFailureException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SystemOperationFailureExceptionTest {

    @Test
    public void testExceptionMessageAndCause() {
        String message = "Testfel";
        Exception cause = new Exception("Orsak");

        SystemOperationFailureException exception = new SystemOperationFailureException(message, cause);

        assertEquals(message, exception.getMessage(), "Felmeddelandet ska sparas korrekt");
        assertEquals(cause, exception.getCause(), "Orsaken ska sparas korrekt");
    }
}