
package view;

import java.io.IOException;
import util.FileLogger;
import util.TotalRevenueFileOutput;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;
import model.DTO.SåldArtikelDTO;
import controller.Kontroller;
import controller.SystemOperationFailureException;
import integration.ArtikelFinnsInteException;

public class View {

    private Kontroller kontroller;
    private FileLogger fileLogger;
    private FelMeddelandeHanterare felMeddelandeHanterare;

    private static final int ARTIKELID_FINNS_INTE_I_LAGRET = 999;
    private static final int BIG_WHEEL_OATMEAL_ID = 123;
    private static final int YOUGOGO_BLUEBERRY_ID = 456;

    public View(Kontroller kontroller) throws IOException {
        this.kontroller = kontroller;
        this.felMeddelandeHanterare = new FelMeddelandeHanterare();
        this.fileLogger = new FileLogger();
        kontroller.addRevenueObserver(TotalRevenueView.getTotalRevenueView());
        kontroller.addRevenueObserver(new TotalRevenueFileOutput());
    }

    public void körFörsäljning() {
        startaFörsäljning();
        skannaArtikel(ARTIKELID_FINNS_INTE_I_LAGRET);
        skannaArtikel(BIG_WHEEL_OATMEAL_ID);
        skannaArtikel(YOUGOGO_BLUEBERRY_ID);
        angeMängd(2);
        avslutaFörsäljning();
        betala(300);
    }

    public void startaFörsäljning() {
        kontroller.startaFörsäljning();
        System.out.println("Ny försäljning startad!\n");
    }

    private void skannaArtikel(int artikelID) {
        System.out.println("Skannad vara");
        try {
            SkanningsDTO säljInformation = kontroller.skannaArtikel(artikelID);
            visaArtikelInfo(säljInformation);
            visaTotalPris(säljInformation);
        } catch (ArtikelFinnsInteException exc) {
            felMeddelandeHanterare.visaFelMeddelande("Artikel ID: " + exc.hämtaFelArtikelID() + " finns inte i lagret ");
        } catch (SystemOperationFailureException exc) {
            felMeddelandeHanterare.visaFelMeddelande("Databasen kan inte nås");
            fileLogger.logException(exc);
        } catch (Exception exc) {
            felMeddelandeHanterare.visaFelMeddelande("Operationen misslyckades");
            fileLogger.logException(exc);
        }
    }

    private void angeMängd(int mängd) {
        SkanningsDTO säljInformation = kontroller.angeMängd(mängd);
        SåldArtikelDTO senasteArtikel = säljInformation.getSenasteSåldaArtikelDTO();
        System.out.println("Nya mängden av varan: " + senasteArtikel.getNamn() + " = " + mängd);
        visaArtikelInfo(säljInformation);
        visaTotalPris(säljInformation);
    }

    private void avslutaFörsäljning() {
        SkanningsDTO nySäljInformation = kontroller.avslutaFörsäljning();
        System.out.println("Avslutad Försäljning!");
        System.out.println("Totalpris och VAT för köpet");
        visaTotalPris(nySäljInformation);
    }

    private void visaArtikelInfo(SkanningsDTO säljInformation) {
        SåldArtikelDTO artikel = säljInformation.getSenasteSåldaArtikelDTO();
        if (artikel == null) return;

        System.out.println("Artikel ID: " + artikel.getArtikelID());
        System.out.println("Namn: " + artikel.getNamn());
        System.out.printf("Kostnad för varan: %.2f SEK%n", artikel.getPris());
        System.out.printf("VAT: %.2f SEK%n", artikel.getVAT());
        System.out.println("Mängd av vara: " + artikel.getMängd());
    }

    private void visaTotalPris(SkanningsDTO säljinformation) {
        System.out.println();
        System.out.printf("Totala kostnaden: %.2f SEK%n", säljinformation.getTotalPris());
        System.out.printf("Totala VAT: %.2f SEK%n", säljinformation.getVAT());
    }

    private void betala(float belopp) {
        Kvitto kvitto = kontroller.betala(belopp);
        visaKvitto(kvitto);
    }

    private void visaKvitto(Kvitto kvitto) {
        System.out.println("-----KVITTO------\n");

        for (SåldArtikelDTO artikel : kvitto.getSåldaArtikelDTOs()) {
            String namn = artikel.getNamn();
            float pris = artikel.getPris();
            int mängd = artikel.getMängd();
            float vat = artikel.getVAT();
            float totalPris = pris * mängd;

            System.out.printf("%s x%d  %.2f kr (VAT %.0f%%)%n", namn, mängd, totalPris, vat);
        }
        System.out.println("-------------------");
        System.out.printf("Total VAT: %.2f kr%n", kvitto.getTotalVAT());
        System.out.printf("Total kostnad: %.2f kr%n", kvitto.getTotalPris());
        System.out.println("-------------------\n");
    }
}