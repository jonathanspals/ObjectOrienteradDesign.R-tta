package Tester.model;

import model.SåldArtikel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.DTO.ArtikelDTO;

public class SåldArtikelTest {

    @Test
    public void testKonstruktorOchGetters() {
        ArtikelDTO artikel = new ArtikelDTO("Testartikel", 1, 100.0f, 25.0f);
        SåldArtikel såldArtikel = new SåldArtikel(artikel);
        
        assertEquals(artikel, såldArtikel.getArtikelDTO(), "getArtikelDTO ska returnera rätt ArtikelDTO");
        assertEquals(1, såldArtikel.getMängdSålt(), "Initial mängdSålt ska vara 1");
    }

    @Test
    public void testÖkaMängdSåltMedEn() {
        ArtikelDTO artikel = new ArtikelDTO("Testartikel", 1, 100.0f, 25.0f);
        SåldArtikel såldArtikel = new SåldArtikel(artikel);
        
        såldArtikel.ökaMängdSåltMedEn();
        assertEquals(2, såldArtikel.getMängdSålt(), "MängdSålt ska öka med 1 efter ökaMängdSåltMedEn()");
    }

    @Test
    public void testLäggTillBelopp() {
        ArtikelDTO artikel = new ArtikelDTO("Testartikel", 1, 100.0f, 25.0f);
        SåldArtikel såldArtikel = new SåldArtikel(artikel);
        
        såldArtikel.läggTillBelopp(5);
        assertEquals(6, såldArtikel.getMängdSålt(), "MängdSålt ska öka med det angivna beloppet");
    }
}