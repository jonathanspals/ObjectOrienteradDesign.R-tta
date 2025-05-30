package model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import model.SåldArtikel;

/**
 * SkanningsDTO representerar data som genereras vid skanning av en artikel i en försäljningsprocess.
 * Denna klass fungerar som en Data Transfer Object (DTO) och innehåller information om 
 * artikelns ID, namn, pris, moms, beskrivning och antal.
 * 
 * Används främst för att föra data mellan modell och vy utan att exponera underliggande logik.
 */

public class SkanningsDTO {
    private final LocalDateTime tid;
    private final float VAT;
    private final float totalPris;
    private final List<SåldArtikel> listaMedSåldaArtiklar;

    /**
     * Creates a new instance representing a sale.
     * @param soldItems         Contains sold items and their quantity
     * @param time              Time of sale
     * @param vat               Total VAT for the sale
     * @param totalPrice        Total cost/price for the sale
     * @param discountAmount    Discount deducted from sale
     */
    public SkanningsDTO(List<SåldArtikel> såldaArtiklar, LocalDateTime tid, float VAT, float totalPris) {
        this.listaMedSåldaArtiklar = såldaArtiklar;
        this.tid = tid;
        this.VAT = VAT;
        this.totalPris = totalPris;
    }
    
    public float getTotalPris(){
        return totalPris;
    }
    
    public float getVAT(){
        return VAT;
    }
    
    public List<SåldArtikel> getSåldaArtiklar(){
        return listaMedSåldaArtiklar;
    }
    
    public SåldArtikel getSenasteSåldaArtikel(){
        if(listaMedSåldaArtiklar == null || listaMedSåldaArtiklar.isEmpty()){
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size()-1);
    }
    
    public LocalDateTime getTid(){
        return tid;
    }    
}