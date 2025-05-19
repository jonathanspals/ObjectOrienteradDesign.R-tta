package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import integration.ArtikelRegister;
import integration.Kassa;
import integration.ArtikelFinnsInteException;
import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;

/**
 * KassaRegister hanterar affärslogik för försäljning i ett kassasystem.
 * 
 * Denna klass ansvarar för att:
 * - Skanna och registrera artiklar
 * - Hålla koll på alla artiklar i en pågående försäljning
 * - Räkna ut totalpris, moms och växel
 * - Sammanställa artikellistan till kvitto
 * - Anropa ArtikelRegister för att hämta artikeldata
 */
public class KassaRegister {

    private LocalTime tidförsäljning;
    private List<ArtikelDTO> artiklellista = new ArrayList<>();
    private ArtikelRegister artikelRegister = new ArtikelRegister();
    private Kassa kassa;

    /**
     * Skapar ett nytt KassaRegister-objekt.
     * 
     * @param kassa Kassasystemets saldo hanteras av detta objekt
     */
    public KassaRegister(Kassa kassa) {
        this.kassa = kassa;
    }

    /**
     * Sätter och returnerar aktuell tidpunkt då försäljningen startade.
     * 
     * @return LocalTime då försäljningen inleddes
     */
    public LocalTime setTimeOfSale() {
        tidförsäljning = LocalTime.now();
        return tidförsäljning;
    }

    /**
     * Lägger till eller uppdaterar en artikel i artikellistan baserat på ID och antal.
     * 
     * @param artikelID Det ID som identifierar artikeln
     * @param antalAvArtikel Hur många av artikeln som köpts
     * @return Ett SkanningsDTO-objekt med artikelns info
     * @throws ArtikelFinnsInteException Om artikel-ID inte hittas
     */
    public SkanningsDTO artikelIDOchAntal(String artikelID, int antalAvArtikel)
            throws ArtikelFinnsInteException {

        ArtikelDTO artikelInfo = artikelRegister.hämtaArtikelInformation(artikelID, antalAvArtikel);

        // Om artikeln redan finns, öka antal och returnera uppdaterad info
        for (ArtikelDTO artikel : artiklellista) {
            if (artikel.getartikelID().equals(artikelInfo.getartikelID())) {
                artikel.ökaAntal(antalAvArtikel);
                    return skapaSkanningsDTO(artikel);
            }
        }
        // Annars lägg till ny artikel
        artiklellista.add(artikelInfo);
        return skapaSkanningsDTO(artikelInfo);
    }

    /**
     * Skapar ett SkanningsDTO-objekt baserat på en artikel.
     * 
     * @param artikel Artikel att konvertera
     * @return DTO-objekt för vidare användning
     */
    private SkanningsDTO skapaSkanningsDTO(ArtikelDTO artikel) {
        return new SkanningsDTO(
                artikel.getartikelID(),
                artikel.getVAT(),
                (float) artikel.getartikelPris(),
                artikel.getartikelBeskrivning(),
                artikel.getantalAvArtikel(),
                artikel.getArtikelNamn());
    }

    /**
     * Räknar ut och returnerar totalpris för alla artiklar i artikellistan.
     * 
     * @return Totalpris (inkl. utan moms)
     */
    public double getTotalPris() {
        float totalPris = 0;
        for (ArtikelDTO artikel : artiklellista) {
            totalPris += artikel.getartikelPris() * artikel.getantalAvArtikel();
        }
        return totalPris;
    }

    /**
     * Beräknar växeln som ska ges tillbaka till kunden.
     * 
     * @param betalatBelopp Det belopp kunden betalade
     * @param nyttPris Det slutliga priset (efter eventuell rabatt)
     * @return Växel att ges tillbaka
     */
    public float beräknaVäxel(float betalatBelopp, float nyttPris) {
        return betalatBelopp - nyttPris;
    }

    /**
     * Räknar ut total moms (VAT) för försäljningen.
     * 
     * @return Total moms i kronor
     */
    public float calculateTotalVAT() {
        float totalVAT = 0;
        for (ArtikelDTO artikel : artiklellista) {
            totalVAT += artikel.getartikelPris() * (artikel.getVAT() / 100.0f);
        }
        return totalVAT;
    }

    /**
     * Returnerar en strängrepresentation av alla artiklar som köpts.
     * 
     * @return Sträng med artikelinformation
     */
    public String artiklarSomString() {
        StringBuilder sb = new StringBuilder();
        for (ArtikelDTO artikel : artiklellista) {
            sb.append(artikel.getartikelID()).append(" x").append(artikel.getantalAvArtikel()).append(", ");
        }
        return sb.toString();
    }

    /**
     * Avslutar försäljningen. Returnerar det totala priset som ska betalas (efter rabatt).
     * 
     * @param nyttPris Priset efter eventuell rabatt
     * @return nyttpris
     */
    public float försäljningsAvslut(float nyttPris) {
        return nyttPris;
    }
}