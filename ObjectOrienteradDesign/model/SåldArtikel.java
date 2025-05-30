package model;

import model.DTO.ArtikelDTO;

/**
 * Representerar en såld artikel under en försäljning, inklusive artikelinformation och såld mängd.
 */
public class SåldArtikel {
    private ArtikelDTO artikelDTO;
    private int mängdSålt;
    private int STARTING_QUANTITY = 1;

    /**
     * Skapar en instans av SåldArtikel med en initial mängd på 1.
     *
     * @param artikelDTO Artikelinformation för den sålda artikeln.
     */
    public SåldArtikel(ArtikelDTO artikelDTO) {
        this.artikelDTO = artikelDTO;
        this.mängdSålt = STARTING_QUANTITY;
    }

    /**
     * Hämtar artikelinformationen för den sålda artikeln.
     *
     * @return ArtikelDTO-objektet.
     */
    public ArtikelDTO getArtikelDTO() {
        return artikelDTO;
    }

    /**
     * Hämtar hur många enheter av artikeln som sålts.
     *
     * @return Antal sålda enheter.
     */
    public int getMängdSålt() {
        return mängdSålt;
    }

    /**
     * Ökar antalet sålda enheter med 1.
     */
    void ökaMängdSåltMedEn() {
        mängdSålt += 1;
    }

    /**
     * Lägger till ett visst antal till den sålda mängden.
     *
     * @param belopp Antalet enheter att lägga till.
     */
    public void läggTillBelopp(int belopp) {
        mängdSålt = mängdSålt + belopp;
    }
}