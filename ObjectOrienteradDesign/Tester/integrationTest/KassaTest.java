package Tester.integrationTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import integration.Kassa;

public class KassaTest {
    
    private Kassa kassa;

    @BeforeEach
    public void setUp() {
        kassa = new Kassa();
    }

    @Test
    public void testUppdateraKassaSaldoPositivtPris() {
        kassa.uppdateraKassaSaldo(100.0f);
        assertEquals(100.0f, kassa.getSaldo(), 0.001f);
    }

    @Test
    public void testUppdateraKassaSaldoFleraGånger() {
        kassa.uppdateraKassaSaldo(50.0f);  
        kassa.uppdateraKassaSaldo(30.0f);  
        kassa.uppdateraKassaSaldo(20.0f);  
        assertEquals(100.0f, kassa.getSaldo(), 0.001f);
    }

    @Test
    public void testUppdateraKassaSaldoMedNegativtPris() {
   
        assertThrows(IllegalArgumentException.class, () -> kassa.uppdateraKassaSaldo(-50.0f));
    }

    @Test
    public void testÅterställSaldo() {
        kassa.uppdateraKassaSaldo(150.0f);
        kassa.återställSaldo();
        assertEquals(0.0f, kassa.getSaldo(), 0.001f);
    }

    @Test
    public void testGetSaldoUtanUppdatering() {
        assertEquals(0.0f, kassa.getSaldo(), 0.001f);
    }
}
