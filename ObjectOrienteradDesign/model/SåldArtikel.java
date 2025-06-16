package model;

import model.DTO.ArtikelDTO;
import model.DTO.SåldArtikelDTO;

/**
 * Representerar en artikel som sålts under en försäljning.
 */
public class SåldArtikel {
    private ArtikelDTO artikelDTO;
    private int mängdSålt;
    private int STARTING_QUANTITY = 1;

    /**
     * Skapar en såld artikel med startmängd.
     * 
     * @param artikelDTO Artikelbeskrivningen kopplad till denna sålda artikel
     */
    public SåldArtikel(ArtikelDTO artikelDTO) {
        this.artikelDTO = artikelDTO;
        this.mängdSålt = STARTING_QUANTITY;
    }

    public ArtikelDTO getArtikelDTO() {
        return artikelDTO;
    }

    public int getMängdSålt() {
        return mängdSålt;
    }

    void ökaMängdSåltMedEn() {
        mängdSålt += 1;
    }

    public void läggTillBelopp(int belopp) {
        mängdSålt = mängdSålt + belopp;
    }

    /**
     * Konverterar denna modell till en DTO för visningsändamål.
     * 
     * @return DTO med kopierad artikeldata
     */
    public SåldArtikelDTO toDTO() {
        return new SåldArtikelDTO(
            artikelDTO.getnamn(),
            artikelDTO.getartikelID(),
            artikelDTO.getartikelPris(),
            artikelDTO.getVAT(),
            mängdSålt
        );
    }
}