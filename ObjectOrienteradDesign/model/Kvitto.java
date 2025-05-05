package ObjectOrienteradDesign.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representerar ett kvitto som innehåller fullständig information om en försäljning.
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
    public LocalTime gettidFörsäljning(){
        return tidsförsäljning;
    }
    public float gettotalPris(){
        return totalpris;
    }
    public float gettotalVat(){
        return totalVat;
    }
    public float getbetalatBelopp(){
        return betalatBelopp;
    }
    public String getlistaAvArtiklar(){
        return listaAvArtiklar;
    }
    public float getväxel(){
        return växel;
    }
    public LocalDate getdatum(){
        return datum;
    }
    public float getnyttPris(){
        return nyttPris;
    }
    public float getrabatt() {
        return rabatt;
    }
    

}
