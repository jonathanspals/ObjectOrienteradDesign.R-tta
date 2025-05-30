package test.integration;

import integration.ArtikelFinnsInteException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtikelFinnsInteExceptionTest {

    @Test
    void testExceptionMessageShouldContainArtikelID() {
        int artikelID = 42;
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);

        String expectedMessage = "Artikel med ID: " + artikelID + " hittades inte i lagret";
        assertEquals(expectedMessage, exception.getMessage(),
                "Felmeddelandet ska innehålla korrekt artikelID");
    }

    @Test
    void testHämtaFelArtikelIDShouldReturnCorrectID() {
        int artikelID = 99;
        ArtikelFinnsInteException exception = new ArtikelFinnsInteException(artikelID);

        assertEquals(artikelID, exception.hämtaFelArtikelID(),
                "Metoden hämtaFelArtikelID() ska returnera rätt artikelID");
    }
}