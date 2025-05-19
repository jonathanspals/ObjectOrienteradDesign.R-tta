package Tester.modelTest;

import model.Kvitto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class KvittoTest {

    private Kvitto kvitto;
    private LocalTime testTid;
    private LocalDate testDatum;

    @BeforeEach
    public void setUp() {
        testTid = LocalTime.of(14, 30);
        testDatum = LocalDate.of(2025, 5, 16);

        kvitto = new Kvitto(
                testTid,
                100.0f,    // totalpris
                12.0f,     // totalVAT
                120.0f,    // betalat belopp
                "abc123 x2, def456 x1",  // artiklar
                20.0f,     // växel
                testDatum,
                90.0f,     // nytt pris efter rabatt
                10.0f      // rabatt
        );
    }

    @Test
    public void testGetTidFörsäljning() {
        assertEquals(testTid, kvitto.gettidFörsäljning());
    }

    @Test
    public void testGetTotalPris() {
        assertEquals(100.0f, kvitto.gettotalPris(), 0.001f);
    }

    @Test
    public void testGetTotalVAT() {
        assertEquals(12.0f, kvitto.gettotalVat(), 0.001f);
    }

    @Test
    public void testGetBetalatBelopp() {
        assertEquals(120.0f, kvitto.getbetalatBelopp(), 0.001f);
    }

    @Test
    public void testGetListaAvArtiklar() {
        assertEquals("abc123 x2, def456 x1", kvitto.getlistaAvArtiklar());
    }

    @Test
    public void testGetVäxel() {
        assertEquals(20.0f, kvitto.getväxel(), 0.001f);
    }

    @Test
    public void testGetDatum() {
        assertEquals(testDatum, kvitto.getdatum());
    }

    @Test
    public void testGetNyttPris() {
        assertEquals(90.0f, kvitto.getnyttPris(), 0.001f);
    }

    @Test
    public void testGetRabatt() {
        assertEquals(10.0f, kvitto.getrabatt(), 0.001f);
    }
}
