package Tester.model.DTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.DTO.SåldArtikelDTO;

class SåldArtikelDTOTest {

    @Test
    void testKonstruktorOchGetters() {
        int artikelID = 101;
        String namn = "Testprodukt";
        float pris = 59.99f;
        float vat = 25.0f;
        int mängd = 3;

        SåldArtikelDTO artikel = new SåldArtikelDTO(artikelID, namn, pris, vat, mängd);

        assertEquals(artikelID, artikel.getArtikelID());
        assertEquals(namn, artikel.getNamn());
        assertEquals(pris, artikel.getPris(), 0.001f);
        assertEquals(vat, artikel.getVAT(), 0.001f);
        assertEquals(mängd, artikel.getMängd());
    }
}
