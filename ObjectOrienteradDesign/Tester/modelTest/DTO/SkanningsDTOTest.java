package Tester.modelTest.DTO;

import model.DTO.*;
import model.SåldArtikel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkanningsDTOTest {

    @Test
    void getterMetoder_returnerarKorrektData() {
        LocalDateTime tid = LocalDateTime.now();
        SåldArtikel artikel = new SåldArtikel(new ArtikelDTO("Test", 1, 10f, 25f));
        artikel.läggTillBelopp(1); // totalt 2 sålda

        SkanningsDTO dto = new SkanningsDTO(List.of(artikel), tid, 5f, 20f);

        assertEquals(20f, dto.getTotalPris());
        assertEquals(5f, dto.getVAT());
        assertEquals(1, dto.getSåldaArtiklar().size());
        assertEquals(artikel, dto.getSenasteSåldaArtikel());
        assertEquals(tid, dto.getTid());
    }

    @Test
    void getSenasteSåldaArtikel_returnerarNullOmListaTom() {
        SkanningsDTO dto = new SkanningsDTO(List.of(), LocalDateTime.now(), 0f, 0f);
        assertNull(dto.getSenasteSåldaArtikel());
    }

    @Test
    void getSenasteSåldaArtikel_returnerarNullOmListaÄrNull() {
        SkanningsDTO dto = new SkanningsDTO(null, LocalDateTime.now(), 0f, 0f);
        assertNull(dto.getSenasteSåldaArtikel());
    }
}