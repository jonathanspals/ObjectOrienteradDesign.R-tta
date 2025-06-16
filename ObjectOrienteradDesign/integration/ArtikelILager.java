package integration;

import model.DTO.ArtikelDTO;

/**
 * Representerar en artikel och dess tillgängliga mängd i lagret.
 */
public class ArtikelILager {
    private ArtikelDTO artikel;
    private int mängd;

    /**
     * Skapar en ny instans av ArtikelILager.
     * 
     * @param artikel Artikeln som lagras.
     * @param mängd Antalet enheter av artikeln i lagret.
     */
    public ArtikelILager(ArtikelDTO artikel, int mängd) {
        this.artikel = artikel;
        this.mängd = mängd;
    }

    /**
     * Hämtar artikelinformationen.
     * 
     * @return ArtikelDTO-objektet som beskriver artikeln.
     */
    public ArtikelDTO getArtikel() {
        return artikel;
    }

    /**
     * Hämtar mängden av artikeln som finns i lager.
     * 
     * @return Antalet enheter i lagret.
     */
    public int getMängd() {
        return mängd;
    }

    /**
     * Sätter en ny mängd för artikeln i lagret.
     * 
     * @param mängd Den nya mängden som ska registreras.
     */
    public void setMängd(int mängd) {
        this.mängd = mängd;
    } 
}
