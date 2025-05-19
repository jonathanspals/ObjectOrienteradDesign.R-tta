package model.DTO;

/**
 * DTO (Data Transfer Object) som representerar information om en skannad artikel.
 * 
 * Används för att transportera data efter att en artikel skannats in i systemet,
 * inklusive information som ID, namn, pris, moms, beskrivning och antal.
 */
public class SkanningsDTO {
    private String artikelID;
    private float artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;

      /**
     * Skapar ett SkanningsDTO-objekt med information om en artikel.
     *
     * @param artikelID ID för artikeln
     * @param VAT Momssats i procent
     * @param artikelPris Pris per enhet (exkl. moms)
     * @param artikelBeskrivning Kort beskrivning av artikeln
     * @param antalAvArtikel Antalet av artikeln som skannats
     * @param artikelNamn Artikelns namn
     */
    public SkanningsDTO(String artikelID,
                      int VAT,
                      float artikelPris,
                      String artikelBeskrivning,
                      int antalAvArtikel,
                      String artikelNamn) {

        this.artikelID = artikelID;
        this.VAT = VAT;
        this.artikelPris = artikelPris;
        this.artikelBeskrivning = artikelBeskrivning;
        this.antalAvArtikel = antalAvArtikel;
        this.artikelNamn = artikelNamn;
    }
    /**
     * Hämtar artikelns ID.
     * @return Artikel-ID
     */
    public String getArtikelID() {
        return artikelID;
    }
     /**
     * Hämtar artikelns pris per enhet.
     * @return Artikelpris (exklusive moms)
     */
    public float getArtikelPris() {
        return artikelPris;
    }
     /**
     * Hämtar artikelns momssats i procent.
     * @return Moms (t.ex. 6, 12, 25)
     */
    public int getVAT() {
        return VAT;
    }
    /**
     * Hämtar artikelns beskrivning.
     * @return Artikelbeskrivning
     */
    public String getArtikelBeskrivning() {
        return artikelBeskrivning;
    }
    /**
     * Hämtar antalet enheter av artikeln som skannats.
     * @return Antal av artikeln
     */
    public int getAntalAvArtikel() {
        return antalAvArtikel;
    }
    /**
     * Hämtar artikelns visningsnamn.
     * @return Artikelns namn
     */
    public String getArtikelNamn(){
        return artikelNamn;
    }

}
