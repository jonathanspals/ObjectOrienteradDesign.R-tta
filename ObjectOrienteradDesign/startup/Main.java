package startup;

import kontroller.Kontroller;
import vy.TotalRevenueFileOutput;
import vy.TotalRevenueView;


/**
 * Klassen representerar startpunkten för POS-applikationen.
 * Den ansvarar för att skapa nödvändiga objekt och starta försäljningsflödet.
 */
public class Main {
    public static void main(String[] args) {
        Kontroller controller = new Kontroller();

        // Registrera observerare som loggar(visar) total försäljning
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());
      

        controller.hanteraKassaFlöde();
    }
}
  
