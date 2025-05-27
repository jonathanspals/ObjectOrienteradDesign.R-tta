package model;
import model.DTO.ArtikelDTO;

public class SåldArtikel {
    private ArtikelDTO artikelDTO;
    private int mängdSålt;
    private int STARTING_QUANTITY = 1;


    public SåldArtikel(ArtikelDTO artikelDTO){
        this.artikelDTO = artikelDTO;
        this.mängdSålt = STARTING_QUANTITY;
    }

    public ArtikelDTO getArtikelDTO(){
        return artikelDTO;
    }

    public int getMängdSålt(){
        return mängdSålt;
    }

    void ökaMängdSåltMedEn(){
        mängdSålt +=1;
    }

    public void läggTillBelopp(int belopp){
        mängdSålt = mängdSålt + belopp;

    }
}