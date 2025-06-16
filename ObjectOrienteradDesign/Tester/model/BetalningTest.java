package Tester.model;

import model.Betalning;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BetalningTest {

    @Test
    public void testGetBelopp() {
        float testBelopp = 150.75f;
        Betalning betalning = new Betalning(testBelopp);
        
        assertEquals(testBelopp, betalning.getBelopp(), "getBelopp ska returnera det korrekta beloppet");
    }
}