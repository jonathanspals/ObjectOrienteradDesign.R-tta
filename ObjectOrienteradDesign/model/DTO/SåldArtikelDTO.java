package model.DTO;

/**
 * Data Transfer Object som representerar en såld artikel utan affärslogik.
 * Används i gränssnittet mot View för att förhindra direkt åtkomst till modellklasser.
 */
public class SåldArtikelDTO {
    private final String namn;
    private final int artikelID;
    private final float pris;
    private final float vat;
    private final int mängd;

    /**
     * Skapar en ny instans av SåldArtikelDTO.
     * 
     * @param namn Namnet på artikeln
     * @param artikelID Artikelns unika ID
     * @param pris Artikelns styckpris
     * @param vat Momssats för artikeln
     * @param mängd Antal sålda enheter
     */
    public SåldArtikelDTO(String namn, int artikelID, float pris, float vat, int mängd) {
        this.namn = namn;
        this.artikelID = artikelID;
        this.pris = pris;
        this.vat = vat;
        this.mängd = mängd;
    }

    public String getNamn() { return namn; }
    public int getArtikelID() { return artikelID; }
    public float getPris() { return pris; }
    public float getVAT() { return vat; }
    public int getMängd() { return mängd; }
}
