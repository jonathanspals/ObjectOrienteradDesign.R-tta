package ObjectOrienteradDesign.Tester;
import ObjectOrienteradDesign.integration.RabattSystem;

public class RabattKontroll {
    
    public static void main(String[] args) {
        RabattSystem rabattSystem = new RabattSystem();

        // Test 1: Kund med rabatt (kundID 123)
        float pris1 = rabattSystem.rabattKontroll(200.0f, 123);
        if (pris1 == 180.0f) {
            System.out.println("Test lyckades: Rabatt tillämpad korrekt, nytt pris 180.0 kr");
        } else {
            System.out.println(" Test misslyckades: Förväntat 180.0 kr, men fick " + pris1 + " kr");
        }
    }
}
