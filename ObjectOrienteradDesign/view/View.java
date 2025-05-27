package view;

import java.io.IOException;
import util.FileLogger;
import util.TotalRevenueFileOutput;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;
import model.SåldArtikel;
import model.DTO.ArtikelDTO;
import controller.Kontroller;
import controller.SystemOperationFailureException;
import integration.ArtikelFinnsInteException;

/**
 * View-klassen ansvarar för att hantera all interaktion med användaren i konsolen.
 * Den visar information till användaren och tar emot inmatning som rör försäljningsflödet.
 */
public class View {
    
    private Kontroller kontroller;
    private FileLogger fileLogger;
    private FelMeddelandeHanterare felMeddelandeHanterare;

    private static final int ARTIKELID_FINNS_INTE_I_LAGRET = 999;
    private static final int BIG_WHEEL_OATMEAL_ID = 123;
    private static final int YOUGOGO_BLUEBERRY_ID = 456;


    /**
     * Skapar ett nytt View-objekt för användarinteraktion.
     */
    public View(Kontroller kontroller) throws IOException {
        this.kontroller = kontroller;
        this.felMeddelandeHanterare = new FelMeddelandeHanterare();
        this.fileLogger = new FileLogger();
        kontroller.addRevenueObserver(TotalRevenueView.getTotalRevenueView());
        kontroller.addRevenueObserver(new TotalRevenueFileOutput());
    }

    public void körFörsäljning(){
        startaFörsäljning();
        skannaArtikel(ARTIKELID_FINNS_INTE_I_LAGRET);
        skannaArtikel(BIG_WHEEL_OATMEAL_ID);
        skannaArtikel(YOUGOGO_BLUEBERRY_ID);
        angeMängd(2);
        avslutaFörsäljning();
        betala(300);
    }

    public void startaFörsäljning(){
        kontroller.startaFörsäljning();
        System.out.println("Ny försäljning startad!\n");
    }

    private void skannaArtikel(int artikelID){
        System.out.println("Skannad vara");
        try{
            SkanningsDTO säljInformation = kontroller.skannaArtikel(artikelID);
            visaArtikelInfo(säljInformation);
            visaTotalPris(säljInformation);
        }
        catch(ArtikelFinnsInteException exc){
            felMeddelandeHanterare.visaFelMeddelande("Artikel ID: " + exc.hämtaFelArtikelID() + "finns inte i lagret ");
        }
        catch(SystemOperationFailureException exc){
            felMeddelandeHanterare.visaFelMeddelande("Databasen kan inte nås");
            fileLogger.logException(exc);
        }
        catch(Exception exc){
            felMeddelandeHanterare.visaFelMeddelande("Operationen misslyckades");
            fileLogger.logException(exc);
        }
    }

    private void angeMängd(int mängd){
        SkanningsDTO säljInformation = kontroller.angeMängd(mängd);
        String senasteArtikel = säljInformation.getSenasteSåldaArtikel().getArtikelDTO().getnamn();
        System.out.println("Nya mängden av varan: " + senasteArtikel + "= " + mängd);
        visaArtikelInfo(säljInformation);
        visaTotalPris(säljInformation);
    }

    private void avslutaFörsäljning(){
        SkanningsDTO nySäljInformation = kontroller.avslutaFörsäljning();
        System.out.println("Avslutad Försäljning!");
        System.out.println("Totalpris och VAT för köpet");
        visaTotalPris(nySäljInformation);
    }

    private void visaArtikelInfo(SkanningsDTO säljInformation){
        SåldArtikel såldArtikel = säljInformation.getSenasteSåldaArtikel();
        ArtikelDTO artikel = såldArtikel.getArtikelDTO();

        System.out.println("Artikel ID: " + artikel.getartikelID());
        System.out.println("Namn: " + artikel.getnamn());
        System.out.printf("Kostnad för varan: %.2f SEK%n", artikel.getartikelPris());
        System.out.printf("VAT: %.2f SEK%n", artikel.getVAT());
        System.out.println("Mängd av vara: " + såldArtikel.getMängdSålt());
    }

    private void visaTotalPris(SkanningsDTO säljinformation){
        System.out.println();
        System.out.printf("Totala kostnaden: %.2f SEK%n", säljinformation.getTotalPris());
        System.out.printf("Totala VAT: %.2f SEK%n", säljinformation.getVAT());
    }

    private void betala(float belopp){
        Kvitto kvitto = kontroller.betala(belopp);
        visaKvitto(kvitto);
    }

    private void visaKvitto(Kvitto kvitto){
        System.out.println("-----KVITTO------\n");

        for(SåldArtikel artikel : kvitto.getSåldaArtiklar()){
            String namn = artikel.getArtikelDTO().getnamn();
            float pris = artikel.getArtikelDTO().getartikelPris();
            int mängd = artikel.getMängdSålt();
            float vat = artikel.getArtikelDTO().getVAT();
            float totalPris = pris * mängd;

            System.out.printf("%s x%d  %.2f kr (VAT %.0f%%)%n", namn, mängd, totalPris, vat);

        }

        System.out.println("-------------------");
        System.out.printf("Total VAT: %.2f kr%n", kvitto.getTotalVAT());
        System.out.printf("Total kostnad: %.2f kr%n", kvitto.getTotalPris());
        System.out.println("-------------------\n");
    }
}