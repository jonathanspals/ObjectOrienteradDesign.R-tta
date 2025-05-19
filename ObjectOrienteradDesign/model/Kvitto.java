package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representerar ett kvitto som innehåller fullständig information om en försäljning.
 * 
 * Ett kvitto sammanfattar data som:
 * - Datum och tid för försäljningen
 * - Köpta artiklar
 * - Totalpris och moms
 * - Betalt belopp, växel och rabatt
 */
public class Kvitto {
    private LocalTime tidsförsäljning;         
    private float totalpris; 
    private float totalVat;
    private float betalatBelopp;
    private String listaAvArtiklar;
    private float växel;
    private LocalDate datum; 
    private float nyttPris;  
    private float rabatt;     
            
/**
     * Skapar ett nytt kvitto-objekt med all nödvändig försäljningsdata.
     *
     * @param tidsförsäljning Tidpunkt då försäljningen genomfördes
     * @param totalpris Ordinarie totalpris (före rabatt)
     * @param totalVat Total moms för försäljningen
     * @param betalatBelopp Det belopp som kunden betalade
     * @param listaAvArtiklar Lista med köpta artiklar i textform
     * @param växel Växel som ska ges tillbaka till kunden
     * @param datum Datum för försäljningen
     * @param nyttPris Slutgiltigt pris efter rabatt
     * @param rabatt Rabatten som tillämpades på köpet
     */
        public Kvitto(  LocalTime tidsförsäljning,          
                        float totalpris,
                        float totalVat,
                        float betalatBelopp,
                        String listaAvArtiklar,
                        float växel,
                        LocalDate datum,
                        float nyttPris,
                        float rabatt
                        ){

        this.tidsförsäljning = tidsförsäljning;
        this.totalpris = totalpris;
        this.totalVat = totalVat;
        this.betalatBelopp = betalatBelopp;
        this.listaAvArtiklar = listaAvArtiklar;
        this.växel = växel;
        this.datum = datum;
        this.nyttPris = nyttPris;
        this.rabatt = rabatt;
    }

     /**
     * Hämtar försäljningstidpunkten.
     * @return Tidpunkt för försäljningen.
     */
    public LocalTime gettidFörsäljning(){
        return tidsförsäljning;
    }
    /**
     * Hämtar det ordinarie totalpriset.
     * @return Totalpris före rabatt.
     */
    public float gettotalPris(){
        return totalpris;
    }
    /**
     * Hämtar den totala momsen.
     * @return Total momsbelopp.
     */
    public float gettotalVat(){
        return totalVat;
    }
    /**
     * Hämtar det belopp som kunden betalade.
     * @return Betalat belopp.
     */
    public float getbetalatBelopp(){
        return betalatBelopp;
    }
    /**
     * Hämtar listan över köpta artiklar.
     * @return Sträng med artikelinformation.
     */
    public String getlistaAvArtiklar(){
        return listaAvArtiklar;
    }
     /**
     * Hämtar växeln som ska ges till kunden.
     * @return Växelbelopp.
     */
    public float getväxel(){
        return växel;
    }
    /**
     * Hämtar försäljningsdatumet.
     * @return Datum för försäljningen.
     */
    public LocalDate getdatum(){
        return datum;
    }
    /**
     * Hämtar det nya totalpriset efter rabatt.
     * @return Totalpris efter rabatt.
     */
    public float getnyttPris(){
        return nyttPris;
    }
     /**
     * Hämtar rabatten som tillämpades på försäljningen.
     * @return Rabatten i kronor.
     */
    public float getrabatt() {
        return rabatt;
    }
}
