package model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import model.SåldArtikel;

/**
 * SkanningsDTO representerar data som genereras vid skanning av artiklar under en försäljning.
 * Den fungerar som ett Data Transfer Object (DTO) mellan modell och vy, och kapslar in
 * information om sålda artiklar, tidpunkt, totalpris och moms.
 */
public class SkanningsDTO {
    private final LocalDateTime tid;
    private final float VAT;
    private final float totalPris;
    private final List<SåldArtikel> listaMedSåldaArtiklar;

    /**
     * Skapar en ny instans av SkanningsDTO som beskriver aktuell försäljningsdata.
     *
     * @param såldaArtiklar     Lista över sålda artiklar i försäljningen.
     * @param tid               Tidpunkten då försäljningen sker.
     * @param VAT               Totalt momsbelopp för försäljningen.
     * @param totalPris         Det totala försäljningspriset.
     */
    public SkanningsDTO(List<SåldArtikel> såldaArtiklar, LocalDateTime tid, float VAT, float totalPris) {
        this.listaMedSåldaArtiklar = såldaArtiklar;
        this.tid = tid;
        this.VAT = VAT;
        this.totalPris = totalPris;
    }

    /**
     * Hämtar totalpriset för försäljningen.
     *
     * @return Totalt pris.
     */
    public float getTotalPris() {
        return totalPris;
    }

    /**
     * Hämtar totalt momsbelopp för försäljningen.
     *
     * @return VAT i kronor.
     */
    public float getVAT() {
        return VAT;
    }

    /**
     * Hämtar listan med alla sålda artiklar.
     *
     * @return Lista av SåldArtikel-objekt.
     */
    public List<SåldArtikel> getSåldaArtiklar() {
        return listaMedSåldaArtiklar;
    }

    /**
     * Hämtar den senast tillagda artikeln i försäljningen.
     *
     * @return Senaste sålda artikeln eller null om listan är tom.
     */
    public SåldArtikel getSenasteSåldaArtikel() {
        if (listaMedSåldaArtiklar == null || listaMedSåldaArtiklar.isEmpty()) {
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size() - 1);
    }

    /**
     * Hämtar tidpunkten då försäljningen registrerades.
     *
     * @return Tid som LocalDateTime.
     */
    public LocalDateTime getTid() {
        return tid;
    }
}