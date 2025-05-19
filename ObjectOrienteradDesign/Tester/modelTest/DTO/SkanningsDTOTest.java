package Tester.modelTest.DTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DTO.SkanningsDTO;

public class SkanningsDTOTest {

    private SkanningsDTO skanning;

    @BeforeEach
    public void setUp() {
        skanning = new SkanningsDTO(
                "abc123",
                6,
                29.90f,
                "Havregryn 500g, glutenfri",
                3,
                "BigWheel Oatmeal");
    }

    @Test
    public void testGetArtikelID() {
        assertEquals("abc123", skanning.getArtikelID());
    }

    @Test
    public void testGetArtikelPris() {
        assertEquals(29.90f, skanning.getArtikelPris(), 0.001f);
    }

    @Test
    public void testGetVAT() {
        assertEquals(6, skanning.getVAT());
    }

    @Test
    public void testGetArtikelBeskrivning() {
        assertEquals("Havregryn 500g, glutenfri", skanning.getArtikelBeskrivning());
    }

    @Test
    public void testGetAntalAvArtikel() {
        assertEquals(3, skanning.getAntalAvArtikel());
    }

    @Test
    public void testGetArtikelNamn() {
        assertEquals("BigWheel Oatmeal", skanning.getArtikelNamn());
    }
}
