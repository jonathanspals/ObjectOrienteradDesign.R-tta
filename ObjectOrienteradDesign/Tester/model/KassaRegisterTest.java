package Tester.model;

import model.KassaRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KassaRegisterTest {

    @Test
    void konstruktor_sätterStartsaldot() {
        KassaRegister kassa = new KassaRegister(100f);
        assertEquals(100f, kassa.getSaldo());
    }

    @Test
    void uppdateraKassaSaldo_läggerTillBelopp() {
        KassaRegister kassa = new KassaRegister(50f);
        kassa.uppdateraKassaSaldo(25f);
        assertEquals(75f, kassa.getSaldo());
    }

    @Test
    void uppdateraKassaSaldo_medNegativtBelopp() {
        KassaRegister kassa = new KassaRegister(100f);
        kassa.uppdateraKassaSaldo(-30f);
        assertEquals(70f, kassa.getSaldo());
    }
}