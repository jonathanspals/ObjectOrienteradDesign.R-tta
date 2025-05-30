package model.DTO;

import java.time.LocalDateTime;
import java.util.List;

import model.SåldArtikel;
import model.Betalning;
/**
 * Kvitto representerar ett kvitto för en genomförd försäljning. 
 * Det innehåller detaljer såsom tid och datum för försäljningen, 
 * totalpris före och efter rabatt, total moms, betalat belopp, 
 * lista av sålda artiklar, växel samt eventuell rabatt.
 * 
 * Används som en Data Transfer Object (DTO) mellan modell och presentation.
 */

public class Kvitto {
    private LocalDateTime tidFörsäljning;         
    private float totalPris; 
    private float totalVAT;
    private List<SåldArtikel> listaMedArtiklar;
    private float betalatBelopp;
    private float växel;    
    
        /**
     * Skapar ett Kvitto-objekt med all nödvändig information om en försäljning.
     *
     * @param tidsförsäljning Tiden då försäljningen ägde rum
     * @param totalpris Det totala priset före eventuell rabatt
     * @param totalVat Den totala momsen för försäljningen
     * @param betalatBelopp Det belopp kunden betalade
     * @param listaAvArtiklar En textrepresentation av alla artiklar i försäljningen
     * @param växel Den växel som ska ges tillbaka till kunden
     * @param datum Datum för försäljningen
     * @param nyttPris Priset efter att rabatt har tillämpats
     * @param rabatt Den rabatt som har dragits från totalpriset
     */


    public Kvitto (SkanningsDTO skanningsDTO, Betalning betalning){
        this.totalPris = skanningsDTO.getTotalPris();
        this.betalatBelopp = betalning.getBelopp();
        this.totalVAT = skanningsDTO.getVAT();
        this.listaMedArtiklar = skanningsDTO.getSåldaArtiklar();
        this.tidFörsäljning = skanningsDTO.getTid();
        beräknaVäxel();


    }

    private void beräknaVäxel(){
        float växel = totalPris - betalatBelopp;
        this.växel = växel;
    }

    /**
     * Hämtar tiden för försäljningen.
     * @return Tiden för försäljningen
     */
    public LocalDateTime getTidFörsäljning() {
        return tidFörsäljning;
    }

    /**
     * Hämtar det totala priset för försäljningen.
     * @return Det totala priset
     */
    public float getTotalPris() {
        return totalPris;
    }

    /**
     * Hämtar den totala momsen för försäljningen.
     * @return Den totala momsen
     */
    public float getTotalVAT() {
        return totalVAT;
    }

    /**
     * Hämtar det belopp som kunden har betalat.
     * @return Det betalda beloppet
     */
    public float getBetalatBelopp() {
        return betalatBelopp;
    }

    /**
     * Hämtar listan av artiklar som köpts under försäljningen.
     * @return Listan av artiklar
     */
    public List<SåldArtikel> getSåldaArtiklar() {
        return listaMedArtiklar;
    }

    /**
     * Hämtar växeln som ska ges tillbaka till kunden.
     * @return Växeln
     */
    public float getVäxel() {
        return växel;
    }
}
