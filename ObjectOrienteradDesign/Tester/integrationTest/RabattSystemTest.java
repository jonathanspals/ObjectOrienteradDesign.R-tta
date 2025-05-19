package Tester.integrationTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.RabattSystem;

public class RabattSystemTest {

    private RabattSystem rabattSystem;

    @BeforeEach
    public void setUp() {
        rabattSystem = new RabattSystem();
    }

    @Test
    public void testRabattGesTillKund123() {
        float totalPris = 100.0f;
        int kundID = 123;

        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID);

        assertEquals(90.0f, nyttPris, 0.001f);
    }

    @Test
    public void testRabattGesTillKund456() {
        float totalPris = 200.0f;
        int kundID = 456;

        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID);

        assertEquals(180.0f, nyttPris, 0.001f);
    }

    @Test
    public void testIngenRabattTillAnnatKundID() {
        float totalPris = 150.0f;
        int kundID = 789; 

        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID);

        assertEquals(150.0f, nyttPris, 0.001f);
    }

    @Test
    public void testIngenRabattVidKundID0() {
        float totalPris = 250.0f;
        int kundID = 0;

        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID);

        assertEquals(250.0f, nyttPris, 0.001f);
    }
}
