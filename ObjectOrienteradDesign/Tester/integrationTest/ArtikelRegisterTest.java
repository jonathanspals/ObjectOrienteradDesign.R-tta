package Tester.integrationTest;

import integration.ArtikelRegister;
import integration.ArtikelFinnsInteException;
import integration.DatabasNedException;
import model.DTO.ArtikelDTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtikelRegisterTest {

    private ArtikelRegister artikelRegister;

    @BeforeEach
    public void setUp() {
        artikelRegister = new ArtikelRegister();
    }

    @Test
    public void testHämtaArtikelInformation_abc123() throws Exception {
        ArtikelDTO artikel = artikelRegister.hämtaArtikelInformation("abc123", 2);

        assertEquals("abc123", artikel.getartikelID());
        assertEquals("BigWheel Oatmeal", artikel.getArtikelNamn());
        assertEquals(29.90, artikel.getPris(), 0.001);
        assertEquals(6, artikel.getVAT());
        assertEquals("BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free", artikel.getBeskrivning());
        assertEquals(2, artikel.getantalAvArtikel());
    }

    @Test
    public void testHämtaArtikelInformation_def456() throws Exception {
        ArtikelDTO artikel = artikelRegister.hämtaArtikelInformation("def456", 1);

        assertEquals("def456", artikel.getartikelID());
        assertEquals("YouGoGo Blueberry", artikel.getArtikelNamn());
        assertEquals(14.90, artikel.getPris(), 0.001);
        assertEquals(6, artikel.getVAT());
        assertEquals("YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour", artikel.getBeskrivning());
        assertEquals(1, artikel.getantalAvArtikel());
    }

    @Test
    public void testHämtaArtikelInformation_KastarArtikelFinnsInteException() {
        assertThrows(ArtikelFinnsInteException.class, () -> {
            artikelRegister.hämtaArtikelInformation("ogiltigtID", 1);
        });
    }

    @Test
    public void testHämtaArtikelInformation_KastarDatabasNedException() {
        assertThrows(DatabasNedException.class, () -> {
            artikelRegister.hämtaArtikelInformation("DBERROR", 1);
        });
    }
}