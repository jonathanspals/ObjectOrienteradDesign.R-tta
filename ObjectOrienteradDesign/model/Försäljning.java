package model;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.DTO.Kvitto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representerar en pågående försäljning. Hanterar artiklar som skannas,
 * uppdaterar pris och moms, samt genererar kvitto vid betalning.
 */
public class Försäljning {
    private List<SåldArtikel> listaMedSåldaArtiklar;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private LocalDateTime tidFörsäljning;
    private float nuvarandeVAT;
    private float nuvarandePris;
    private final double CONVERT_TO_PERCENT = 0.01;

    /**
     * Skapar en ny försäljning och sätter aktuell tid.
     */
    public Försäljning() {
        tidFörFörsäljning();
        this.listaMedSåldaArtiklar = new ArrayList<>();
    }

    /**
     * Sätter aktuell tidpunkt för försäljningen.
     */
    private void tidFörFörsäljning() {
        this.tidFörsäljning = LocalDateTime.now();
    }

    /**
     * Uppdaterar det nuvarande totalpriset med en ny artikel.
     *
     * @param artikelDTO Artikeln som ska läggas till.
     */
    private void uppdateraNuvarandePris(ArtikelDTO artikelDTO) {
        nuvarandePris += artikelDTO.getartikelPris();
    }

    /**
     * Uppdaterar den totala momsen med en ny artikel.
     *
     * @param artikelDTO Artikeln som ska läggas till.
     */
    private void uppdateraNuvarandeVAT(ArtikelDTO artikelDTO) {
        nuvarandeVAT += artikelDTO.getartikelPris() * (artikelDTO.getVAT() * CONVERT_TO_PERCENT);
    }

    /**
     * Lägger till en artikel i försäljningen. Om artikeln redan finns ökas mängden.
     *
     * @param artikelDTO Artikeln som ska läggas till.
     */
    public void läggTillArtiklar(ArtikelDTO artikelDTO) {
        for (SåldArtikel artikel : listaMedSåldaArtiklar) {
            if (artikel.getArtikelDTO().getartikelID() == artikelDTO.getartikelID()) {
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

    /**
     * Justerar mängden för den senast tillagda artikeln.
     *
     * @param mängd Den nya mängden.
     */
    public void justeraMängdAvSenasteArtikel(int mängd) {
        SåldArtikel senasteVara = getSenasteSåldaArtikel();
        int redanSkannad = 1;
        int mängdAttLäggaTill = mängd - redanSkannad;
        senasteVara.läggTillBelopp(mängdAttLäggaTill);

        justeraVAT(mängdAttLäggaTill, senasteVara.getArtikelDTO());
        justeraPris(mängdAttLäggaTill, senasteVara.getArtikelDTO());
    }

    /**
     * Justerar totalpriset baserat på mängden artiklar.
     *
     * @param mängdAttLäggaTill Antal artiklar att lägga till.
     * @param artikelDTO Artikeln vars pris ska användas.
     */
    private void justeraPris(int mängdAttLäggaTill, ArtikelDTO artikelDTO) {
        nuvarandePris += artikelDTO.getartikelPris() * mängdAttLäggaTill;
    }

    /**
     * Justerar total moms baserat på mängden artiklar.
     *
     * @param mängdAttLäggaTill Antal artiklar att lägga till.
     * @param artikelDTO Artikeln vars moms ska användas.
     */
    private void justeraVAT(int mängdAttLäggaTill, ArtikelDTO artikelDTO) {
        double artikelVAT = artikelDTO.getVAT() * CONVERT_TO_PERCENT;
        nuvarandeVAT += artikelDTO.getartikelPris() * artikelVAT * mängdAttLäggaTill;
    }

    /**
     * Hämtar ett SkanningsDTO som innehåller aktuell försäljningsinformation.
     *
     * @return SkanningsDTO med sålda artiklar, tid, moms och pris.
     */
    public SkanningsDTO getSkanningsDTO() {
        SkanningsDTO skanningsDTO = new SkanningsDTO(listaMedSåldaArtiklar, tidFörsäljning, nuvarandeVAT, nuvarandePris);
        return skanningsDTO;
    }

    /**
     * Hämtar den senast tillagda artikeln i försäljningen.
     *
     * @return Det senaste SåldArtikel-objektet eller null om listan är tom.
     */
    private SåldArtikel getSenasteSåldaArtikel() {
        if (listaMedSåldaArtiklar.isEmpty()) {
            return null;
        }
        return listaMedSåldaArtiklar.get(listaMedSåldaArtiklar.size() - 1);
    }

    /**
     * Genomför betalning, skapar kvitto och notifierar alla observerare.
     *
     * @param belopp Det belopp som kunden har betalat.
     * @return Ett kvitto för försäljningen.
     */
    public Kvitto betala(float belopp) {
        Betalning betalning = new Betalning(belopp);
        SkanningsDTO skanningsDTO = getSkanningsDTO();
        Kvitto kvitto = new Kvitto(skanningsDTO, betalning);
        notifyRevenueObservers();
        return kvitto;
    }

    /**
     * Lägger till en observerare som ska notifieras vid ny försäljning.
     *
     * @param observer En implementation av RevenueObserver.
     */
    private void addRevenueObserver(RevenueObserver observer) {
        revenueObservers.add(observer);
    }

    /**
     * Lägger till flera observerare till försäljningen.
     *
     * @param observers Lista med observerare.
     */
    public void addRevenueObservers(List<RevenueObserver> observers) {
        for (RevenueObserver observer : observers) {
            addRevenueObserver(observer);
        }
    }

    /**
     * Notifierar alla registrerade observerare om ny intäkt.
     */
    private void notifyRevenueObservers() {
        for (RevenueObserver observer : revenueObservers) {
            observer.newRevenue(nuvarandePris);
        }
    }
}