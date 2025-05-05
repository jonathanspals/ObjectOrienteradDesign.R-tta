package ObjectOrienteradDesign.startup;

import ObjectOrienteradDesign.kontroller.Kontroller;
import ObjectOrienteradDesign.vy.Vy;
import ObjectOrienteradDesign.integration.Kassa;
import ObjectOrienteradDesign.integration.Printer;

/**
 * Programstart. Initierar systemets kärnkomponenter och startar försäljningsflödet.
 */

public class Main {
     /**
     * Huvudmetod som körs vid programstart.
     * Skapar instanser av kontroller, printer och kassa, och startar flödet.
     *
     * @param args Kommandoradsargument (används ej).
     */ 
    public static void main(String[] args) {
        Kontroller controller = new Kontroller();
        Vy vy = Vy.Create(controller);
        Kassa kassa = new Kassa();
        Printer printer = new Printer();
        
        controller.hanteraKassaFlöde(printer, kassa);
    }
}
  
