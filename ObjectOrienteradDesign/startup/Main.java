package startup;

import java.io.IOException;
import controller.Kontroller;
import view.View;
import integration.ArtikelRegister;
import integration.BokföringsRegister;
import integration.Printer;

/**
 * Main-klassen representerar startpunkten för POS-applikationen.
 */
public class Main {
    public static void main(String[] args) {
        ArtikelRegister artikelRegister = ArtikelRegister.getArtikelRegister();
        Printer printer = new Printer();
        BokföringsRegister bokföringsRegister = BokföringsRegister.getBokföringsRegister();
        
        Kontroller kontroller = new Kontroller(bokföringsRegister, artikelRegister, printer);

        try {
            View view = new View(kontroller);  // Detta ska fungera nu
            view.körFörsäljning();
        } catch(IOException exc) {
            System.out.println("Kan inte starta programmet ");
            exc.printStackTrace();
        }
    }
}