package model.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.SåldArtikel;
/**
 * DTO som används för att förmedla information om pågående försäljning.
 * Innehåller artiklar, totalpris och moms – i form av affärslogikfria objekt.
 */
public class SkanningsDTO {
    private final LocalDateTime tid;
    private final float VAT;
    private final float totalPris;
    private final List<SåldArtikel> listaMedSåldaArtiklar;

    /**
     * Skapar ett nytt SkanningsDTO-objekt.
     *
     * @param såldaArtiklar Artiklar som har skannats
     * @param tid           Tidsstämpel för försäljningen
     * @param VAT           Moms för försäljningen
     * @param totalPris     Total summa för försäljningen
     */
    public SkanningsDTO(List<SåldArtikel> såldaArtiklar, LocalDateTime tid, float VAT, float totalPris) {
        this.listaMedSåldaArtiklar = såldaArtiklar;
        this.tid = tid;
        this.VAT = VAT;
        this.totalPris = totalPris;
    }

    /**
     * Hämtar försäljningens totalpris.
     *
     * @return Totalpris
     */
    public float getTotalPris() {
        return totalPris;
    }

    /**
     * Hämtar försäljningens totala moms.
     *
     * @return Moms i kronor
     */
    public float getVAT() {
        return VAT;
    }

    /**
     * Hämtar tidsstämpel för försäljningen.
     *
     * @return Tid som LocalDateTime
     */
    public LocalDateTime getTid() {
        return tid;
    }

    /**
     * Hämtar en lista med DTO:er för alla sålda artiklar.
     *
     * @return Lista med SåldArtikelDTO
     */
    public List<SåldArtikelDTO> getSåldaArtikelDTOs() {
        List<SåldArtikelDTO> dtoList = new ArrayList<>();
        for (SåldArtikel artikel : listaMedSåldaArtiklar) {
            dtoList.add(artikel.toDTO());
        }
        return dtoList;
    }

    /**
     * Hämtar den senast skannade artikeln som DTO.
     *
     * @return SåldArtikelDTO eller null
     */
    public SåldArtikelDTO getSenasteSåldaArtikelDTO() {
        if (listaMedSåldaArtiklar == null || listaMedSåldaArtiklar.isEmpty()) {
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size() - 1).toDTO();
    }
}
