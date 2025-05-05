package ObjectOrienteradDesign.integration;
import ObjectOrienteradDesign.model.Kvitto;
import ObjectOrienteradDesign.model.DTO.SkanningsDTO;

public class Printer {

    /**
     * Skriver ut kvittoinformation på konsolen.
     * Den här metoden kan modifieras för att skriva ut till andra enheter, 
     * exempelvis en fysisk skrivare om det behövs i framtiden.
     * 
     * @param kvitto Kvittoobjekt som innehåller försäljningsinformation.
     */
    public void skapaKvitto(Kvitto kvitto) {
        if (kvitto == null) {
            throw new IllegalArgumentException("Kvitto kan inte vara null.");
        }

        // Skriver ut kvittoinformation
        System.out.println("----- KVITTO -----");
        System.out.println("Tid: " + kvitto.gettidFörsäljning());
        System.out.println("Datum: " + kvitto.getdatum());
        System.out.println("Artiklar: " + kvitto.getlistaAvArtiklar());
        System.out.println("Totalpris: " + kvitto.gettotalPris() + " SEK");
        System.out.println("Rabatt: -" + kvitto.getrabatt() + " kr");
        System.out.println("Moms: " + kvitto.gettotalVat() + " SEK");
        System.out.println("Betalat: " + kvitto.getbetalatBelopp() + " SEK");
        System.out.println("Växel: " + kvitto.getväxel() + " SEK");
        // System.out.println("Artikelnamn " + kvitto.getArtikelBeskrivning());
        System.out.println("------------------");
    }
}
