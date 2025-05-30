package model;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.DTO.Kvitto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Försäljning {
    private List<SåldArtikel> listaMedSåldaArtiklar;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private LocalDateTime tidFörsäljning;
    private float nuvarandeVAT;
    private float nuvarandePris;
    private final double CONVERT_TO_PERCENT = 0.01;

    public Försäljning(){
        tidFörFörsäljning();
        this.listaMedSåldaArtiklar = new ArrayList<>();
    }

    private void tidFörFörsäljning(){
        this.tidFörsäljning = LocalDateTime.now();
    }

    private void uppdateraNuvarandePris(ArtikelDTO artikelDTO){
        nuvarandePris += artikelDTO.getartikelPris();
    }

    private void uppdateraNuvarandeVAT(ArtikelDTO artikelDTO){
        nuvarandeVAT += artikelDTO.getartikelPris () * (artikelDTO.getVAT() * CONVERT_TO_PERCENT);
    }

    public void läggTillArtiklar(ArtikelDTO artikelDTO){
        for(SåldArtikel artikel : listaMedSåldaArtiklar){
            if(artikel.getArtikelDTO().getartikelID() == artikelDTO.getartikelID()){
                artikel.ökaMängdSåltMedEn();
                uppdateraNuvarandePris(artikelDTO);
                uppdateraNuvarandeVAT(artikelDTO);
                return;
            }
        }
        listaMedSåldaArtiklar.add(new SåldArtikel(artikelDTO));
        uppdateraNuvarandePris(artikelDTO);
        uppdateraNuvarandeVAT(artikelDTO);
    }

    public void justeraMängdAvSenasteArtikel(int mängd){
        SåldArtikel senasteVara = getSenasteSåldaArtikel();
        int redanSkannad = 1;
        int mängdAttLäggaTill = mängd-redanSkannad;
        senasteVara.läggTillBelopp(mängdAttLäggaTill);

        justeraVAT(mängdAttLäggaTill, senasteVara.getArtikelDTO());
        justeraPris(mängdAttLäggaTill, senasteVara.getArtikelDTO());

    }

    private void justeraPris(int mängdAttLäggaTill, ArtikelDTO artikelDTO){
        nuvarandePris += artikelDTO.getartikelPris()*mängdAttLäggaTill;
    }

    private void justeraVAT(int mängdAttLäggaTill, ArtikelDTO artikelDTO){
        double artikelVAT = artikelDTO.getVAT()*CONVERT_TO_PERCENT;
        nuvarandeVAT += artikelDTO.getartikelPris()* artikelVAT * mängdAttLäggaTill;
    }

    public SkanningsDTO getSkanningsDTO(){
        SkanningsDTO skanningsDTO = new SkanningsDTO(listaMedSåldaArtiklar, tidFörsäljning, nuvarandeVAT, nuvarandePris);
        return skanningsDTO;
    }

    private SåldArtikel getSenasteSåldaArtikel(){
        if(listaMedSåldaArtiklar.isEmpty()){
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size()-1);
    }
    public Kvitto betala(float belopp){
        Betalning betalning = new Betalning(belopp);
        SkanningsDTO skanningsDTO = getSkanningsDTO();
        Kvitto kvitto = new Kvitto(skanningsDTO, betalning);
        notifyRevenueObservers();

        return kvitto;

    }

    private void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }

    public void addRevenueObservers(List<RevenueObserver> observers){
        for(RevenueObserver observer : observers){
            addRevenueObserver(observer);
        }
    }

    private void notifyRevenueObservers(){
        for(RevenueObserver observer : revenueObservers){
            observer.newRevenue(nuvarandePris);
        }
    }

    
}