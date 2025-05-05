package ObjectOrienteradDesign.kontroller;

import java.time.LocalTime;
import java.util.Scanner;
import ObjectOrienteradDesign.model.KassaRegister;
import ObjectOrienteradDesign.integration.Kassa;
import ObjectOrienteradDesign.integration.Printer;

/**
 * Kontrollerklass som hanterar huvudflödet i försäljningsprocessen.
 * Kommunicerar mellan vy och modellklasser.
 */
public class Kontroller{
       /**
     * Hanterar hela kassa- och försäljningsflödet:
     * inkluderar artikelinmatning, rabattkontroll, betalning och kvitto.
     *
     * @param printer En {Printer}-instans för att skriva ut kvittot.
     * @param kassa En {Kassa}-instans för att uppdatera kassans saldo.
     */
    public void hanteraKassaFlöde(Printer printer, Kassa kassa){
        KassaRegister kassaRegister = new KassaRegister(printer, kassa);
        System.out.println("Försäljning Startad");

        LocalTime tid = kassaRegister.setTimeOfSale();
        System.out.println("Kontroller: tidFörsäljning = " + tid);

        Scanner scanner = new Scanner(System.in);
        
        //Lägger till artiklar
        kassaRegister.artikelInformationOchNyttAntal(scanner);

        // Kontrollera kund-ID för rabatt
        System.out.print("Ange kund ID för rabatt (eller 0 om ingen): ");
        int kundID = scanner.nextInt();

        // Beräkna totalpris och tillämpa rabatt
        float nyttpris = kassaRegister.kundIDOchFlaggaRabatt(kundID);

        // Processa betalning
        System.out.print("Ange betalat belopp: ");
        float betalatBelopp = scanner.nextFloat();
        float växel = kassaRegister.processSale(nyttpris, betalatBelopp);

        // Skriv ut kvitto
        kassaRegister.betalningOchSäljInformation(betalatBelopp, nyttpris, växel);

        // Avsluta försäljning
        System.out.println("Försäljning avslutad.");
        
    }
    
}

