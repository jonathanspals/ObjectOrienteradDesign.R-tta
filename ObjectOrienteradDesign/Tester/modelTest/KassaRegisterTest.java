package Tester.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.Kassa;
import integration.ArtikelFinnsInteException;
import model.KassaRegister;
import model.DTO.SkanningsDTO;

public class KassaRegisterTest {

    private KassaRegister kassaRegister;

    @BeforeEach
    public void setUp() {
        kassaRegister = new KassaRegister(new Kassa());
    }

    @Test
    public void testSetTimeOfSaleReturnsCurrentTime() {
        LocalTime beforeCall = LocalTime.now();
        LocalTime saleTime = kassaRegister.setTimeOfSale();
        LocalTime afterCall = LocalTime.now();

        // saleTime ska ligga mellan beforeCall och afterCall (eller vara samma sekunder)
        assertTrue((!saleTime.isBefore(beforeCall) && !saleTime.isAfter(afterCall)));
    }

    @Test
    public void testArtikelIDOchAntalLäggerTillArtikel() throws ArtikelFinnsInteException {
        SkanningsDTO dto = kassaRegister.artikelIDOchAntal("abc123", 2);

        assertEquals("abc123", dto.getArtikelID());
        assertEquals(2, dto.getAntalAvArtikel());
    }

    @Test
    public void testArtikelIDOchAntalÖkarAntalVidDubbelInläggning() throws ArtikelFinnsInteException {
        kassaRegister.artikelIDOchAntal("abc123", 1);
        SkanningsDTO dto = kassaRegister.artikelIDOchAntal("abc123", 3);

        assertEquals(4, dto.getAntalAvArtikel());
    }

    @Test
    public void testGetTotalPrisRäknarKorrekt() throws ArtikelFinnsInteException {
        kassaRegister.artikelIDOchAntal("abc123", 2); // 2 x 29.90
        kassaRegister.artikelIDOchAntal("def456", 1); // 1 x 14.90

        double expectedTotal = (2 * 29.90) + (1 * 14.90);

        assertEquals(expectedTotal, kassaRegister.getTotalPris(), 0.001);
    }

    //  FEL HÄR 
    @Test
    public void testCalculateTotalVAT() throws ArtikelFinnsInteException {
        // Arrange: Lägg till två artiklar
        kassaRegister.artikelIDOchAntal("abc123", 2); // 2 x 29.90, VAT 6%
        kassaRegister.artikelIDOchAntal("def456", 1); // 1 x 14.90, VAT 6%
    
        // Förväntad moms: (2 x 29.90 x 0.06) + (1 x 14.90 x 0.06)
        double expectedVAT = (2 * 29.90 * 0.06) + (1 * 14.90 * 0.06);
    
        // Act & Assert
        assertEquals(expectedVAT, kassaRegister.calculateTotalVAT(), 0.001);
    }

    @Test
    public void testBeräknaVäxelReturnerarKorrekt() {
        float växel = kassaRegister.beräknaVäxel(200.0f, 150.0f);

        assertEquals(50.0f, växel, 0.001f);
    }

    @Test
    public void testArtiklarSomStringReturnerarKorrektFormat() throws ArtikelFinnsInteException {
        kassaRegister.artikelIDOchAntal("abc123", 2);
        kassaRegister.artikelIDOchAntal("def456", 1);

        String result = kassaRegister.artiklarSomString();

        assertTrue(result.contains("abc123 x2"));
        assertTrue(result.contains("def456 x1"));
    }

    @Test
    public void testFörsäljningsAvslutReturnerarKorrektBelopp() {
        float result = kassaRegister.försäljningsAvslut(180.0f);

        assertEquals(180.0f, result, 0.001f);
    }
}
