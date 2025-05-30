package Tester.modelTest.DTO;

import model.DTO.*;
import model.Betalning;
import model.SåldArtikel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KvittoTest {
   

    @Test
    void kvittoInitierarFältKorrekt() {
        // Arrange
        ArtikelDTO artikel = new ArtikelDTO("Choklad", 1, 20f, 6f);
        SåldArtikel såldArtikel = new SåldArtikel(artikel);
        såldArtikel.läggTillBelopp(1); // totalt 2

        LocalDateTime tid = LocalDateTime.now();
        SkanningsDTO skanning = new SkanningsDTO(List.of(såldArtikel), tid, 3f, 40f);
        Betalning betalning = new Betalning(50f);

        // Act
        Kvitto kvitto = new Kvitto(skanning, betalning);

        // Assert
        assertEquals(40f, kvitto.getTotalPris());
        assertEquals(3f, kvitto.getTotalVAT());
        assertEquals(50f, kvitto.getBetalatBelopp());
        assertEquals(10f, kvitto.getVäxel()); // 50 - 40 = 10
        assertEquals(tid, kvitto.getTidFörsäljning());
        assertEquals(1, kvitto.getSåldaArtiklar().size());
    }
}
