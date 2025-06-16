package test.controller;
import controller.Kontroller;
import controller.SystemOperationFailureException;
import integration.*;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KontrollerTest {

    Kontroller kontroller;

    @BeforeEach
    void init() {
        ArtikelRegister artikelRegister = ArtikelRegister.getArtikelRegister(); 
        Printer printer = new Printer();
        BokföringsRegister bokföring = BokföringsRegister.getBokföringsRegister();
        kontroller = new Kontroller(bokföring, artikelRegister, printer);
        kontroller.startaFörsäljning();
    }

    @Test
    void skannaArtikel_returnerarDTO() throws Exception {
        SkanningsDTO dto = kontroller.skannaArtikel(123); 
        assertFalse(dto.getSåldaArtikelDTOs().isEmpty());
    }

    @Test
    void skannaArtikel_medDatabasFel_kastarSystemOperationFailure() {
        assertThrows(SystemOperationFailureException.class, () -> kontroller.skannaArtikel(999));
    }

    @Test
    void angeMängd_uppdaterarMängd() throws Exception {
        kontroller.skannaArtikel(123);
        assertEquals(3, kontroller.angeMängd(3).getSåldaArtikelDTOs().get(0));
    }

    @Test
    void avslutaFörsäljning_returnerarDTO() throws Exception {
        kontroller.skannaArtikel(123);
        assertNotNull(kontroller.avslutaFörsäljning());
    }

    @Test
    void betala_returnerarKvitto() throws Exception {
        kontroller.skannaArtikel(123);
        kontroller.angeMängd(2);
        Kvitto kvitto = kontroller.betala(100f);
        assertNotNull(kvitto);
    }

    @Test
    void startaFörsäljning_utanFel() {
        assertDoesNotThrow(() -> kontroller.startaFörsäljning());
    }
}