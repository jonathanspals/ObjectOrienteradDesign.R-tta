package Tester.model.DTO;

import model.DTO.*;
import model.Betalning;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KvittoTest {

    @Test
    void testKvittoInnehållerRättData() {
        SåldArtikelDTO såld1 = new SåldArtikelDTO(1, "Bröd", 20f, 12f, 1);
        SåldArtikelDTO såld2 = new SåldArtikelDTO(2, "Mjölk", 15f, 6f, 1);
        List<SåldArtikelDTO> såldaArtiklar = List.of(såld1, såld2);
        LocalDateTime tidpunkt = LocalDateTime.now();

       LocalDateTime tid = LocalDateTime.now();
        float totalVAT = (30f * 0.12f * 2) + (20f * 0.06f * 1);  
        float totalPris = (30f * 2) + (20f * 1);

        float VAT;
        SkanningsDTO skanningsDTO = new SkanningsDTO(såldaArtiklar,tid, VAT, totalPris);

        Betalning betalning = new Betalning(100f);

        
        Kvitto kvitto = new Kvitto(skanningsDTO, betalning);

       
        assertEquals(tid, kvitto.getTidFörsäljning(), "Tid ska stämma");
        assertEquals(totalPris, kvitto.getTotalPris(), 0.001, "Totalpris ska stämma");
        assertEquals(totalVAT, kvitto.getTotalVAT(), 0.001, "Total VAT ska stämma");
        assertEquals(100f, kvitto.getBetalatBelopp(), 0.001, "Betalat belopp ska stämma");
        assertEquals(100f - totalPris, kvitto.getVäxel(), 0.001, "Växel ska vara betalat minus totalpris");

        
        List<SåldArtikelDTO> såldaDTOs = kvitto.getSåldaArtikelDTOs();
        assertEquals(2, såldaDTOs.size(), "Antal sålda artiklar ska vara 2");

        SåldArtikelDTO dto1 = såldaDTOs.get(0);
        assertEquals(1, dto1.getArtikelID());
        assertEquals("Bröd", dto1.getNamn());
        assertEquals(30f, dto1.getPris(), 0.001);
        assertEquals(12f, dto1.getVAT(), 0.001);
        assertEquals(2, dto1.getMängd()); 
        SåldArtikelDTO dto2 = såldaDTOs.get(1);
        assertEquals(2, dto2.getArtikelID());
        assertEquals("Mjölk", dto2.getNamn());
        assertEquals(20f, dto2.getPris(), 0.001);
        assertEquals(6f, dto2.getVAT(), 0.001);
        assertEquals(1, dto2.getMängd());
    }

}
