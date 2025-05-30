package model.DTO;

/**
 * Innehåller information om en enskild artikel.
 * Denna klass används för att överföra artikeldata mellan systemets lager och andra delar.
 */
public class ArtikelDTO {
    private final String namn;
    private final int artikelID;
    private final float artikelPris;
    private final float VAT;

    /**
     * Skapar ett nytt ArtikelDTO-objekt med tillhörande data.
     *
     * @param namn        Namnet på artikeln.
     * @param artikelID   Artikelns unika ID.
     * @param artikelPris Priset för artikeln (exklusive moms).
     * @param VAT         Momsprocent (t.ex. 6 för 6% moms).
     */
    public ArtikelDTO(String namn, int artikelID, float artikelPris, float VAT) {
        this.namn = namn;
        this.artikelID = artikelID;
        this.artikelPris = artikelPris;
        this.VAT = VAT;
    }

    /**
     * Hämtar artikelns namn.
     *
     * @return Namnet på artikeln.
     */
    public String getnamn() {
        return namn;
    }

    /**
     * Hämtar artikelns unika ID.
     *
     * @return Artikel-ID:t.
     */
    public int getartikelID() {
        return artikelID;
    }

    /**
     * Hämtar momsprocenten för artikeln.
     *
     * @return VAT-procenten.
     */
    public float getVAT() {
        return VAT;
    }

    /**
     * Hämtar artikelns pris (exklusive moms).
     *
     * @return Artikelns pris.
     */
    public float getartikelPris() {
        return artikelPris;
    }

    /**
     * Jämför detta artikel-ID med ett sökt ID.
     *
     * @param söktArtikelID Artikel-ID som ska jämföras.
     * @return true om det matchar, annars false.
     */
    public boolean matcharArtikelID(int söktArtikelID) {
        return this.artikelID == söktArtikelID;
    }
}