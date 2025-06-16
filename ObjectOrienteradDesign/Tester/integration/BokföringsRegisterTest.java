package Tester.integration;

import integration.BokföringsRegister;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.DTO.Kvitto;

public class BokföringsRegisterTest {

    @Test
    public void testSingletonInstance() {
        BokföringsRegister instance1 = BokföringsRegister.getBokföringsRegister();
        BokföringsRegister instance2 = BokföringsRegister.getBokföringsRegister();
        assertSame(instance1, instance2, "BokföringsRegister ska vara en singleton (samma instans)");
    }

    @Test
    public void testBokföraLäggerTillKvitto() {
        BokföringsRegister register = BokföringsRegister.getBokföringsRegister();

        
        Kvitto dummyKvitto = createDummyKvitto();

      
        register.bokföra(dummyKvitto);

        assertTrue(true, "bokföra-metoden ska kunna köras utan undantag");
    }

    private Kvitto createDummyKvitto() {
        return new Kvitto(null, null); 
    }
}
