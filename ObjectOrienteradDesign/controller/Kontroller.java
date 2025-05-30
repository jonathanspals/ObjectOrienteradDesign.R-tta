package controller;

import java.util.ArrayList;
import java.util.List;
import model.KassaRegister;
import model.RevenueObserver;
import integration.ArtikelFinnsInteException;
import model.DTO.Kvitto;
import model.DTO.SkanningsDTO;
import integration.LagerDatabasException;
import model.DTO.ArtikelDTO;
import integration.Printer;
import integration.ArtikelRegister;
import integration.BokföringsRegister;
import model.Försäljning;

/**
 * Kontroller-klassen hanterar logiken för hela kassaflödet.
 * Den kopplar samman olika delar av systemet, såsom vy, kassa, lager och rabatt.
 * Klassen är navet för att initiera och genomföra en försäljning.
 */
public class Kontroller {
    private ArtikelRegister artikelRegister;
    private Printer printer;
    private KassaRegister kassaRegister;
    private Försäljning försäljning;
    private List<RevenueObserver> revenueObservers = new ArrayList<>();
    private BokföringsRegister bokföringsRegister;

    /**
     * Skapar en ny instans av Kontroller och initierar nödvändiga beroenden.
     * 
     * @param bokföring Bokföringssystemet som används för att bokföra kvitton.
     * @param artikelRegister Register som hanterar artikeldatan.
     * @param printer Skrivaren som skriver ut kvitton.
     */
    public Kontroller(BokföringsRegister bokföring, ArtikelRegister artikelRegister, Printer printer){
        this.artikelRegister = artikelRegister;
        this.kassaRegister = new KassaRegister(0);
        this.printer = printer;
        this.bokföringsRegister = bokföring;
    }

    /**
     * Startar en ny försäljning.
     */
    public void startaFörsäljning(){
        försäljning = new Försäljning();
        försäljning.addRevenueObservers(revenueObservers);
    }

    /**
     * Skannar en artikel och lägger till den i försäljningen.
     * 
     * @param artikelID ID:t för artikeln som ska skannas.
     * @return SkanningsDTO med uppdaterad försäljningsinformation.
     * @throws ArtikelFinnsInteException Om artikeln inte finns i registret.
     * @throws SystemOperationFailureException Om ett systemfel uppstår (t.ex. databasfel).
     */
    public SkanningsDTO skannaArtikel(int artikelID) throws ArtikelFinnsInteException, SystemOperationFailureException{
        try {
            ArtikelDTO artikelDTO = artikelRegister.hämtaArtikelBeskrivning(artikelID);
            försäljning.läggTillArtiklar(artikelDTO);
            return försäljning.getSkanningsDTO();
        } catch (LagerDatabasException exc){
            throw new SystemOperationFailureException("Kan inte nå lagerdatabasen", exc);
        }
    }

    /**
     * Anger antal av senast skannade artikel.
     * 
     * @param mängd Mängden av artikeln.
     * @return Uppdaterad SkanningsDTO med den nya mängden.
     */
    public SkanningsDTO angeMängd(int mängd){
        försäljning.justeraMängdAvSenasteArtikel(mängd);
        SkanningsDTO nuvarandeSkanningsDTO = försäljning.getSkanningsDTO();
        return nuvarandeSkanningsDTO;
    }

    /**
     * Avslutar försäljningen.
     * 
     * @return Slutgiltig SkanningsDTO innan betalning.
     */
    public SkanningsDTO avslutaFörsäljning(){
        SkanningsDTO skanningsDTO = försäljning.getSkanningsDTO();
        return skanningsDTO;
    }

    /**
     * Hanterar betalning, uppdaterar lager, kassa och bokför.
     * 
     * @param belopp Det betalda beloppet.
     * @return Genererat kvitto.
     */
    public Kvitto betala(float belopp){
        Kvitto kvitto = försäljning.betala(belopp);
        SkanningsDTO skanningsDTO = försäljning.getSkanningsDTO();
        artikelRegister.uppdateraLager(skanningsDTO);
        kassaRegister.uppdateraKassaSaldo(belopp);
        bokföringsRegister.bokföra(kvitto);
        printer.skrivUtKvitto(kvitto);
        return kvitto;
    }

    /**
     * Registrerar en ny observer för intäktsuppdateringar.
     * 
     * @param observer Ett objekt som implementerar RevenueObserver.
     */
    public void addRevenueObserver(RevenueObserver observer){
        revenueObservers.add(observer);
    }
}