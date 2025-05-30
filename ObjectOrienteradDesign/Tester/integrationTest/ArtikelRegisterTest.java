package test.integration;

import integration.ArtikelFinnsInteException;
import integration.LagerDatabasException;
import integration.ArtikelRegister;

import model.DTO.SkanningsDTO;
import model.SåldArtikel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtikelRegisterTest {

    ArtikelRegister register = ArtikelRegister.getArtikelRegister();

    @Test
    void hämtaArtikelBeskrivning_returnerarArtikel() throws ArtikelFinnsInteException {
        var artikel = register.hämtaArtikelBeskrivning(123);
        assertEquals("BigWheel Oatmeal", artikel.getnamn());
    }

    @Test
    void hämtaArtikelBeskrivning_kastarExceptionOmIDInteFinns() {
        assertThrows(ArtikelFinnsInteException.class, () -> register.hämtaArtikelBeskrivning(9999));
    }

    @Test
    void hämtaArtikelBeskrivning_kastarDatabasException() {
        assertThrows(LagerDatabasException.class, () -> register.hämtaArtikelBeskrivning(999));
    }

    @Test
    void uppdateraLager_minskarMängd() throws ArtikelFinnsInteException {
        var artikel = register.hämtaArtikelBeskrivning(123);
        int före = register.hämtaArtikelMedID(123).getMängd();

        var såld = new SåldArtikel(artikel);
        såld.läggTillBelopp(4);

        var skanning = new SkanningsDTO(
        List.of(såld),
        java.time.LocalDateTime.now(),
        artikel.getVAT() * 5 / 100,     
        artikel.getartikelPris() * 5   
    );

    register.uppdateraLager(skanning);

    int efter = register.hämtaArtikelMedID(123).getMängd();
    assertEquals(före - 5, efter);
}


    @Test
    void uppdateraLager_medNull_krascharInte() {
        assertDoesNotThrow(() -> register.uppdateraLager(null));
    }

    @Test
    void hämtaArtikelMedID_returnerarNullOmEjFinns() {
        assertNull(register.hämtaArtikelMedID(7777));
    }
}