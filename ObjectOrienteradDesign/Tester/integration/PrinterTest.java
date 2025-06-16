package Tester.integration;

import integration.Printer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.DTO.Kvitto;

public class PrinterTest {

    @Test
    public void testKonstruktor() {
        Printer printer = new Printer();
        assertNotNull(printer, "Printer-objekt ska inte vara null efter konstruktion");
    }

    @Test
    public void testSkrivUtKvittoIngenException() {
        Printer printer = new Printer();
        Kvitto dummyKvitto = createDummyKvitto();
        
        assertDoesNotThrow(() -> printer.skrivUtKvitto(dummyKvitto),
            "skrivUtKvitto ska inte kasta något undantag även om metoden är tom");
    }

    private Kvitto createDummyKvitto() {
        return new Kvitto(null, null);
    }
}