package vy;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import model.Kvitto;
import model.DTO.ArtikelDTO;
import util.FileLogger;

/**
 * View-klassen ansvarar för att hantera all interaktion med användaren i konsolen.
 * Den visar information till användaren och tar emot inmatning som rör försäljningsflödet.
 */
public class Vy {
    
    private FileLogger logger = new FileLogger();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Skapar ett nytt View-objekt för användarinteraktion.
     */
    public Vy() {
    }
    

    /**
     * Skriver ut kvittoinformation på konsolen.
     * Den här metoden kan modifieras för att skriva ut till andra enheter, 
     * exempelvis en fysisk skrivare om det behövs i framtiden.
     * 
     * @param kvitto Kvittoobjekt som innehåller försäljningsinformation.
     * @throws IllegalArgumentException Om kvitto är null.
     */
    public void skrivUtKvitto(Kvitto kvitto) {
        if (kvitto == null) {
            throw new IllegalArgumentException("Kvitto kan inte vara null.");
        }

        System.out.println("----- KVITTO -----");
        System.out.println("Tid: " + kvitto.gettidFörsäljning());  
        System.out.println("Datum: " + kvitto.getdatum());           
        System.out.println("Artiklar: " + kvitto.getlistaAvArtiklar()); 
        System.out.println("Totalpris: " + kvitto.gettotalPris() + " SEK"); 
        System.out.println("Rabatt: -" + kvitto.getrabatt() + " kr");  
        System.out.println("Moms: " + kvitto.gettotalVat() + " SEK");   
        System.out.println("Betalat: " + kvitto.getbetalatBelopp() + " SEK"); 
        System.out.println("Växel: " + kvitto.getväxel() + " SEK");     
        System.out.println("------------------");
    }

    /**
     * Skriver ut att försäljningen har startat och visar aktuell tid.
     * @param tid Tiden då försäljningen påbörjades
     */
    public void startaFörsäljning(LocalTime tid){
        System.out.println("Försäljning Startad");
        System.out.println("Kontroller: tidFörsäljning = " + tid);
    }

    /**
     * Tar emot användarens inmatning av artikel-ID och antal via konsolen.
     * Fortsätter tills användaren skriver "sluta".
     * 
     * @return En lista med ArtikelDTO-objekt baserat på användarens inmatning
     */
    public List<ArtikelDTO> artikelInformation(){
        int antalAvArtikel = 0;
        String artikelID;
        List<ArtikelDTO> artikelLista = new ArrayList<>();

        while (true) {
            System.out.print("Ange artikel ID (eller 'sluta' för att avsluta): ");
            artikelID = scanner.nextLine();
            if (artikelID.equalsIgnoreCase("sluta")) {
                break; 
            }

            System.out.print("Ange antal av artikeln: ");
            antalAvArtikel = scanner.nextInt();
            scanner.nextLine(); 
            artikelLista.add(new ArtikelDTO(artikelID, antalAvArtikel));
        }
        return artikelLista;
    }

    /**
     * Ber användaren ange ett kund-ID för eventuell rabatt.
     * 
     * @return Angivet kund-ID, eller 0 om ingen rabatt önskas
     */
    public int kundID(){
        System.out.print("Ange kund ID för rabatt (eller 0 om ingen): ");
        int kundID = scanner.nextInt();
        return kundID;
    }

    /**
     * Ber användaren ange det betalda beloppet.
     * 
     * @return Det belopp som kunden har betalat
     */
    public float betalatBelopp(){
        System.out.print("Ange betalat belopp: ");
        float betalatBelopp = scanner.nextFloat();
        return betalatBelopp;
    }

    /**
     * Skriver ut växel som ska ges tillbaka till kunden.
     * 
     * @param växel Växelbelopp att visas
     */
    public void skrivUtVäxel(float växel){
        System.out.println("Växel att ge tillbaka: " + växel);
    }

    /**
     * Meddelar att försäljningen har avslutats.
     */
    public void avslutaFörsäljning(){
        System.out.println("Försäljning avslutad.");
    }

    /**
     * Meddelar att ingen rabatt tillämpades på köpet.
     */
    public void ingenRabatt(){
        System.out.println("Ingen rabatt tillämpad.");  
    }

    /**
     * Skriver ut vilken rabatt som tillämpades på köpet.
     * 
     * @param rabatt Rabatten i kronor
     */
    public void skrivUtRabatt(float rabatt){
        System.out.println("Rabatt tillämpad: -" + rabatt + " kr");
    }
    public void skrivFelmeddelandeTillAnvändare(String meddelande, Exception e) {
        System.out.println("FEL: " + meddelande);
        logger.log(meddelande);
        logger.logException(e);
    }
}