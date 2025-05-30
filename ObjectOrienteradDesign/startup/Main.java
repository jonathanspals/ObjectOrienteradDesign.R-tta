package startup;

import java.io.IOException;
import controller.Kontroller;
import view.View;
import integration.ArtikelRegister;
import integration.BokföringsRegister;
import integration.Printer;


/**
 * Main-klassen representerar startpunkten för POS-applikationen.
 * 
 * Den ansvarar för att skapa nödvändiga objekt och starta försäljningsflödet
 * genom att anropa relevant metod i kontrollern.
 * 
 * Denna klass innehåller endast main-metoden och används för att initiera programkörningen.
 */

public class Main {
    /**
     * Huvudmetoden som startar applikationen.
     * Skapar nödvändiga objekt och anropar metoden för att hantera kassa-flödet.
     * @param args Kommando-radsargument (ej använda i denna implementation)
     */
    public static void main(String[] args) {

        ArtikelRegister artikelRegister = ArtikelRegister.getArtikelRegister();
        Printer printer = new Printer();
        BokföringsRegister bokföringsRegister = BokföringsRegister.getBokföringsRegister();
        
        Kontroller kontroller = new Kontroller(bokföringsRegister, artikelRegister, printer);

        try{
            View view = new View(kontroller);
            view.körFörsäljning();
        }
        catch(IOException exc){
            System.out.println("Kan inte starta programmet ");
            exc.printStackTrace();
        }
    }
}