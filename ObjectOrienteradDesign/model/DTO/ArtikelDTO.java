package model.DTO;

/**
 * Data Transfer Object (DTO) för att representera en artikel i systemet.
 * 
 * Används för att skicka data om en artikel mellan olika lager (t.ex. integration och modell)
 * utan att exponera intern logik.
 * 
 * Innehåller data som:
 * - Artikel-ID
 * - Artikelnamn
 * - Pris
 * - Moms (VAT)
 * - Beskrivning
 * - Antal
 */
public class ArtikelDTO {
    private String artikelID;
    private double artikelPris;
    private int VAT;
    private String artikelBeskrivning;
    private int antalAvArtikel;
    private String artikelNamn;

     /**
     * Tom standardkonstruktor.
     */
    public ArtikelDTO(){
        
    }
  /**
     * Konstruktor som direkt sätter artikel-ID och antal.
     *
     * @param artikelID ID för artikeln.
     * @param antalAvArtikel Antalet av denna artikel.
     */
    public ArtikelDTO( String artikelID, int antalAvArtikel){
        this.artikelID = artikelID;
        this.antalAvArtikel = antalAvArtikel;
}
    /**
         * Hämtar artikelns ID.
         * @return Artikel-ID
         */
    public String  getartikelID(){
        return artikelID;
    }
    /**
         * Hämtar artikelns pris per enhet.
         * @return Artikelpris 
         */
    public double getartikelPris(){
        return artikelPris;
    }
    /**
         * Hämtar artikelns momssats i procent.
         * @return Moms i procent 
         */
    public int getVAT(){
        return VAT;
    }
    /**
         * Hämtar artikelns beskrivning.
         * @return Artikelbeskrivning (text)
         */
    public String getartikelBeskrivning(){
        return artikelBeskrivning;
    }
    /**
         * Hämtar hur många enheter av artikeln som är aktuella.
         * @return Antal av artikeln
         */
    public int getantalAvArtikel(){
        return antalAvArtikel;
    }

    /**
         * Sätter artikelns visningsnamn.
         * @param artikelNamn Namn på artikeln
         */
    public String getArtikelNamn(){
        return artikelNamn;
    }
     /**
         * Sätter artikelns ID.
         * @param artikelID Artikel-ID
         */
        public void setArtikelID(String artikelID) {
            this.artikelID = artikelID;
        }
    /**
         * Sätter artikelns visningsnamn.
         * @param artikelNamn Namn på artikeln
         */
    public void setArtikelNamn(String artikelNamn) {
        this.artikelNamn = artikelNamn;
    }
    /**
         * Sätter artikelns pris per enhet.
         * @param artikelPris Pris 
         */
    public void setPris(double artikelPris) {
        this.artikelPris = artikelPris;
    }
    /**
         * Sätter artikelns momssats i procent.
         * @param VAT Moms 
         */
    public void setVAT(int VAT) {
        this.VAT = VAT;
    }
    /**
         * Sätter artikelns beskrivning.
         * @param artikelBeskrivning Beskrivningstext
         */
    public void setBeskrivning(String artikelBeskrivning) {
        this.artikelBeskrivning = artikelBeskrivning;
    }
    /**
         * Sätter antal enheter av artikeln.
         * @param antalAvArtikel Antal som ska registreras
         */
    public void setAntal(int antalAvArtikel) {
        this.antalAvArtikel = antalAvArtikel;
    }
    /**
         * Ökar antalet enheter av artikeln med ett visst antal.
         * Om antalet är negativt eller noll, ges ett felmeddelande.
         *
         * @param extraAntal Antal som ska läggas till
         */
    public void ökaAntal(int extraAntal) {
            if (extraAntal > 0) {
                this.antalAvArtikel += extraAntal;
            } else {
                System.out.println("Antalet som ska ökas kan inte vara negativt eller noll.");
            }
        }
}