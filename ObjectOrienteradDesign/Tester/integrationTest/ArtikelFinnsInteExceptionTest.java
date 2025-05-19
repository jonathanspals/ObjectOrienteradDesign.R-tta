package Tester.integrationTest;

import integration.ArtikelFinnsInteException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArtikelFinnsInteExceptionTest {

    @Test
    public void testFelmeddelandeOchArtikelID() {
        String artikelID = "TEST123";
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);

        String förväntatMeddelande = "Artikeln med ID \"" + artikelID + "\" kunde inte hittas i lagret.";

        // Kontrollera att felmeddelandet stämmer
        assertEquals(förväntatMeddelande, exception.getMessage());

        // Kontrollera att artikel-ID lagrats korrekt
        assertEquals(artikelID, exception.getArtikelID());
    }

    @Test
    public void testÄrKontrolleradException() {
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException("ABC");
        assertTrue(exception instanceof Exception);
    }
}