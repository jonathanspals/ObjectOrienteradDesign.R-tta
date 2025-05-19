package Tester.integrationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.LagerData;
import model.Kvitto;

import java.time.LocalDate;
import java.time.LocalTime;

public class LagerDataTest {

    private LagerData lagerData;

    @BeforeEach
    public void setUp() {
        lagerData = new LagerData();
    }

    @Test
    public void testUppdateraLagerMedKorrektKvitto() {
        // Skapar ett kvitto-objekt med testdata
        Kvitto kvitto = new Kvitto(
                LocalTime.now(),
                100.0f,
                6.0f,
                120.0f,
                "abc123 x2, def456 x1",
                20.0f,
                LocalDate.now(),
                100.0f,
                0.0f);

        assertDoesNotThrow(() -> lagerData.uppdateraLager(kvitto));
    }

    @Test
    public void testUppdateraLagerMedNullKvitto() {
        assertThrows(IllegalArgumentException.class, () -> lagerData.uppdateraLager(null));
    }
}
