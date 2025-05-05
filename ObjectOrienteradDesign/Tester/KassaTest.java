package ObjectOrienteradDesign.Tester;

import ObjectOrienteradDesign.integration.KassaRe;

public class KassaTest {
    public static void main(String[] args) {
        Kassa kassa = new Kassa();

        double totalPris = 150.50;
        kassa.uppdateraKassaSaldo(totalPris);

        if (kassa.getSaldo() == 150.50) {
            System.out.println(" Test lyckades: Saldo är uppdaterat till 150.50 kr");
        } else {
            System.out.println(" Test misslyckades: Förväntat 150.50 kr, men fick " + kassa.getSaldo() + " kr");
        }
    }
}
