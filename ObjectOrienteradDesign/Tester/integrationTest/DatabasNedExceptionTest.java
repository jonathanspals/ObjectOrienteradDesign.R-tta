package Tester.integrationTest;

import integration.DatabasNedException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DatabasNedExceptionTest {

    @Test
    public void testFelmeddelandeInnehållerArtikelID() {
        String artikelID = "DB123";
        DatabasNedException exception = new DatabasNedException(artikelID);

        String förväntatMeddelande = "Kunde inte hämta artikel med ID \"" + artikelID + "\" på grund av ett databasfel.";
        assertEquals(förväntatMeddelande, exception.getMessage());
    }

    @Test
    public void testÄrRuntimeException() {
        DatabasNedException exception = new DatabasNedException("XYZ");
        assertTrue(exception instanceof RuntimeException);
    }
}
