package Tester.model.DTO;

import model.DTO.ArtikelDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtikelDTOTest {

    @Test
    void konstruktorOchGetters_returnerarKorrektData() {
        ArtikelDTO artikel = new ArtikelDTO("Skruvmejsel", 101, 49.9f, 25f);

        assertEquals("Skruvmejsel", artikel.getnamn());
        assertEquals(101, artikel.getartikelID());
        assertEquals(49.9f, artikel.getartikelPris());
        assertEquals(25f, artikel.getVAT());
    }

    @Test
    void matcharArtikelID_returnerarTrueOmIDMatchar() {
        ArtikelDTO artikel = new ArtikelDTO("Hammare", 200, 79.0f, 25f);
        assertTrue(artikel.matcharArtikelID(200));
    }

    @Test
    void matcharArtikelID_returnerarFalseOmIDInteMatchar() {
        ArtikelDTO artikel = new ArtikelDTO("Borrmaskin", 300, 299.0f, 25f);
        assertFalse(artikel.matcharArtikelID(999));
    }
}
