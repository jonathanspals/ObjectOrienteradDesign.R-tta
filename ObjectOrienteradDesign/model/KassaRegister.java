package ObjectOrienteradDesign.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ObjectOrienteradDesign.integration.ArtikelRegister;
import ObjectOrienteradDesign.integration.Kassa;
import ObjectOrienteradDesign.integration.LagerData;
import ObjectOrienteradDesign.integration.Printer;
import ObjectOrienteradDesign.model.DTO.ArtikelDTO;
import ObjectOrienteradDesign.model.DTO.SkanningsDTO;
import ObjectOrienteradDesign.integration.RabattSystem;
import ObjectOrienteradDesign.model.Kvitto;

public class KassaRegister {
    
    private LocalTime tidförsäljning;
    private List<ArtikelDTO> artiklellista = new ArrayList<>();
    private ArtikelRegister artikelRegister = new ArtikelRegister();
    private RabattSystem rabattSystem = new RabattSystem();
    private Kassa kassa;
    private Printer printer;

    public KassaRegister(Printer printer, Kassa kassa){
        this.printer = printer;
        this.kassa = kassa;
    }
    

    public LocalTime setTimeOfSale() {
        tidförsäljning = LocalTime.now();
        return tidförsäljning;
    }

    public SkanningsDTO artikelIDOchAntal(String artikelID, int antalAvArtikel) {
            ArtikelDTO artikelInfo = artikelRegister.hämtaArtikelInformation(artikelID, antalAvArtikel);
        
            // Kolla om artikeln redan finns i listan
            for (ArtikelDTO artikel : artiklellista) {
                if (artikel.getartikelID().equals(artikelInfo.getartikelID())) {
                    artikel.ökaAntal(antalAvArtikel);
                    return skapaSkanningsDTO(artikel);
                }
            }
        
            // Om artikeln inte finns, lägg till ny
            artiklellista.add(artikelInfo);
            return skapaSkanningsDTO(artikelInfo);
        }

       
        private SkanningsDTO skapaSkanningsDTO(ArtikelDTO artikel) {
            return new SkanningsDTO(
                artikel.getartikelID(),
                artikel.getVAT(),
                (float) artikel.getartikelPris(),  // kasta till float
                artikel.getartikelBeskrivning(),
                artikel.getantalAvArtikel(),
                artikel.getArtikelNamn()
            );
        }
    
        public double getTotalPris(){
            float totalPris = 0;
            for(ArtikelDTO artikel : artiklellista){
            totalPris += artikel.getartikelPris() * artikel.getantalAvArtikel();
            }
            return totalPris;
        }


        public void visaArtikelInfo(SkanningsDTO artikelInfo){
            System.out.println("Artikel ID: " + artikelInfo.getArtikelID());
            System.out.println("Artikel pris: " + artikelInfo.getArtikelPris());
            System.out.println("VAT: " + artikelInfo.getVAT());
            System.out.println("Artikel beskrivning: " + artikelInfo.getArtikelBeskrivning());
            System.out.println("Artikel namn: " + artikelInfo.getArtikelNamn());
        }

        public float kundIDOchFlaggaRabatt(int kundID) {
            float totalPris = (float) getTotalPris(); // Original pris
            float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID); // Efter ev. rabatt
        
            float rabatt = totalPris - nyttPris;
            if (rabatt > 0) {
                System.out.println("Rabatt tillämpad: -" + rabatt + " kr");
            } else {
                System.out.println("Ingen rabatt tillämpad.");
            }
        
            return nyttPris;
        }

        public float beräknaVäxel(float betalatBelopp, float nyttPris) {
            return betalatBelopp - nyttPris;
        }
    
        public float processSale(float nyttPris, float betalatBelopp) {
            float växel = beräknaVäxel(betalatBelopp, nyttPris);
            System.out.println("Växel att ge tillbaka: " + växel);
            // DO NOT close scanner if you're using System.in elsewhere!
            return växel;
        }
        private float calculateTotalVAT() {
            float totalVAT = 0;
            for (ArtikelDTO artikel : artiklellista) {
                totalVAT += artikel.getartikelPris() * (artikel.getVAT() / 100.0f);  // Beräkna korrekt VAT
            }
            return totalVAT;
        }
        
        private String artiklarSomString() {
            StringBuilder sb = new StringBuilder();
            for (ArtikelDTO artikel : artiklellista) {
                sb.append(artikel.getartikelID()).append(" x").append(artikel.getantalAvArtikel()).append(", ");
            }
            return sb.toString();
        }
        

        public Kvitto betalningOchSäljInformation(float betalatBelopp, float nyttPris, float växel){
            float totalPris = (float) getTotalPris();
            LocalDate datum = LocalDate.now();
            float rabatt = totalPris - nyttPris;
            LocalTime tidförsäljning = LocalTime.now();

            Kvitto kvittoInfo = new Kvitto(tidförsäljning, totalPris, calculateTotalVAT(), betalatBelopp, artiklarSomString(), växel, datum, nyttPris, rabatt);

            printer.skapaKvitto(kvittoInfo);
            kassa.uppdateraKassaSaldo(nyttPris);
            new LagerData().uppdateraLager(kvittoInfo);
            return kvittoInfo;

        }

        public float försäljningsAvslut(float nyttPris){
            return nyttPris;
        }
        
        public void artikelInformationOchNyttAntal(Scanner scanner){
        while (true) {
            System.out.print("Ange artikel ID (eller 'sluta' för att avsluta): ");
            String artikelID = scanner.nextLine();
            if (artikelID.equalsIgnoreCase("sluta")) {
                break; // Avsluta inmatning av artiklar
            }

            System.out.print("Ange antal av artikeln: ");
            int antalAvArtikel = scanner.nextInt();
            scanner.nextLine(); // Rensa scanner

            // Lägg till artikel i kassa
            artikelIDOchAntal(artikelID, antalAvArtikel);
        }
    }

    
}