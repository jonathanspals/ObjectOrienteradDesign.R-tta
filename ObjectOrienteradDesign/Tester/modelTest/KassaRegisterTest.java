import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.KassaRegister;

public class KassaRegisterTest {
    
    private KassaRegister kassaRegister;
    
    @BeforeEach
    void setUp() {
        kassaRegister = new KassaRegister(1000.0f);
    }
    
    @Test
    void testInitialSaldo() {
        assertEquals(1000.0f, kassaRegister.getSaldo(), 0.001, 
            "Initialt saldo ska vara 1000.0");
    }
    
    @Test
    void testUppdateraKassaSaldoPositivt() {
        kassaRegister.uppdateraKassaSaldo(500.0f);
        assertEquals(1500.0f, kassaRegister.getSaldo(), 0.001,
            "Saldo ska öka med 500.0");
    }
    
    @Test
    void testUppdateraKassaSaldoNegativt() {
        kassaRegister.uppdateraKassaSaldo(-300.0f);
        assertEquals(700.0f, kassaRegister.getSaldo(), 0.001,
            "Saldo ska minska med 300.0");
    }
    
    @Test
    void testFleraUppdateringar() {
        kassaRegister.uppdateraKassaSaldo(200.0f);
        kassaRegister.uppdateraKassaSaldo(-50.0f);
        kassaRegister.uppdateraKassaSaldo(100.0f);
        assertEquals(1250.0f, kassaRegister.getSaldo(), 0.001,
            "Saldo ska vara korrekt efter flera operationer");
    }
    
    @Test
    void testNollBelopp() {
        kassaRegister.uppdateraKassaSaldo(0.0f);
        assertEquals(1000.0f, kassaRegister.getSaldo(), 0.001,
            "Saldo ska förbli oförändrat vid nollbelopp");
    }
}