package Tester.integrationTest;


import integration.ArtikelILager;
import model.DTO.ArtikelDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtikelILagerTest {

    private ArtikelDTO artikel;
    private ArtikelILager artikelILager;

    @BeforeEach
    void setUp() {
        artikel = new ArtikelDTO("Hammare", 1234, 149.90f, 25.0f);
        artikelILager = new ArtikelILager(artikel, 20);
    }

    @Test
    void testGetArtikel() {
        assertEquals(artikel, artikelILager.getArtikel(),
                "getArtikel() ska returnera den ArtikelDTO som instansen skapades med");
    }

    @Test
    void testGetMängd() {
        assertEquals(20, artikelILager.getMängd(),
                "getMängd() ska returnera rätt lagermängd");
    }

    @Test
    void testSetMängd() {
        artikelILager.setMängd(35);
        assertEquals(35, artikelILager.getMängd(),
                "setMängd() ska uppdatera mängden korrekt");
    }

    @Test
    void testArtikelDTOFieldsAccessible() {
        assertEquals("Hammare", artikel.getnamn());
        assertEquals(1234, artikel.getartikelID());
        assertEquals(149.90f, artikel.getartikelPris(), 0.001);
        assertEquals(25.0f, artikel.getVAT(), 0.001);
    }

    @Test
    void testMatcharArtikelIDReturnsTrueForCorrectID() {
        assertTrue(artikel.matcharArtikelID(1234),
                "matcharArtikelID() ska returnera true om ID:n matchar");
    }

    @Test
    void testMatcharArtikelIDReturnsFalseForIncorrectID() {
        assertFalse(artikel.matcharArtikelID(9999),
                "matcharArtikelID() ska returnera false om ID:n inte matchar");
    }
}