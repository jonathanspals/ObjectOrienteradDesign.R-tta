package Tester.modelTest.DTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DTO.ArtikelDTO;

public class ArtikelDTOTest {

    private ArtikelDTO artikel;

    @BeforeEach
    public void setUp() {
        artikel = new ArtikelDTO("abc123", 2);
    }

    @Test
    public void testGetArtikelID() {
        assertEquals("abc123", artikel.getartikelID());
    }

    @Test
    public void testGetAntalAvArtikel() {
        assertEquals(2, artikel.getantalAvArtikel());
    }

    @Test
    public void testSetArtikelNamn() {
        artikel.setArtikelNamn("Oatmeal Deluxe");
        assertEquals("Oatmeal Deluxe", artikel.getArtikelNamn());
    }

    @Test
    public void testSetPrisOchGet() {
        artikel.setPris(29.90);
        assertEquals(29.90, artikel.getartikelPris(), 0.001);
    }

    @Test
    public void testSetVATOchGet() {
        artikel.setVAT(6);
        assertEquals(6, artikel.getVAT());
    }

    @Test
    public void testSetBeskrivningOchGet() {
        artikel.setBeskrivning("Havregryn 500g, glutenfri");
        assertEquals("Havregryn 500g, glutenfri", artikel.getartikelBeskrivning());
    }

    @Test
    public void testSetAntalOchGet() {
        artikel.setAntal(5);
        assertEquals(5, artikel.getantalAvArtikel());
    }

    @Test
    public void testÖkaAntalLäggerTillRätt() {
        artikel.ökaAntal(3);
        assertEquals(5, artikel.getantalAvArtikel());
    }

    @Test
    public void testÖkaAntalNegativtEllerNoll() {
        // Ska inte ändra antal vid negativt värde
        artikel.ökaAntal(0);
        assertEquals(2, artikel.getantalAvArtikel());

        artikel.ökaAntal(-2);
        assertEquals(2, artikel.getantalAvArtikel());
    }
}