package integration;

import java.util.ArrayList;
import java.util.List;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.SåldArtikel;

/**
 * ArtikelRegister ansvarar för att tillhandahålla information om artiklar 
 * baserat på deras ID. Det fungerar som ett hårdkodat register över artiklar 
 * i systemet.
 */
public class ArtikelRegister {
    private final List<ArtikelILager> lager = new ArrayList<>();
    private static final ArtikelRegister ARTIKEL_REGISTER = new ArtikelRegister();

    /**
     * Skapar ett privat ArtikelRegister och fyller det med hårdkodade artiklar.
     */
    private ArtikelRegister(){
        läggTillArtiklar();
    }

    /**
     * Hämtar den enda instansen av ArtikelRegister (singleton).
     * 
     * @return Den globala instansen av ArtikelRegister.
     */
    public static ArtikelRegister getArtikelRegister(){
        return ARTIKEL_REGISTER;
    }

    /**
     * Lägger till hårdkodade artiklar i lagret.
     */
    private void läggTillArtiklar(){
        lager.add(new ArtikelILager(new ArtikelDTO("BigWheel Oatmeal", 123, (float) 29.90, 6), 50 ));
        lager.add(new ArtikelILager(new ArtikelDTO("YouGoGo BlueBerry", 456, (float) 14.9, 6), 50));
    }

    /**
     * Hämtar artikelbeskrivning baserat på artikel-ID.
     * 
     * @param artikelID ID:t för artikeln som söks.
     * @return ArtikelDTO med beskrivningen av artikeln.
     * @throws ArtikelFinnsInteException Om artikeln inte finns.
     */
    public ArtikelDTO hämtaArtikelBeskrivning(int artikelID) throws ArtikelFinnsInteException {
        if(artikelID == 999){
            throw new LagerDatabasException("Databasen kan inte nås");
        }
        for(ArtikelILager artikelILager : lager){
            if(artikelILager.getArtikel().matcharArtikelID(artikelID)){
                return artikelILager.getArtikel();
            }
        }
        throw new ArtikelFinnsInteException(artikelID);
    }

    /**
     * Uppdaterar lagret baserat på såld information.
     * 
     * @param säljInformation SkanningsDTO som innehåller sålda artiklar.
     */
    public void uppdateraLager(SkanningsDTO säljInformation){
        if(säljInformation == null){
            return;
        }
        for(SåldArtikel såldArtikel: säljInformation.getSåldaArtiklar()){
            uppdateraLagerMedArtikel(såldArtikel);
        }
    }

    /**
     * Uppdaterar lagret med en specifik såld artikel.
     * 
     * @param såldArtikel Den sålda artikeln som ska minska lagret.
     */
    private void uppdateraLagerMedArtikel(SåldArtikel såldArtikel){
        ArtikelDTO såldArtikelDTO = såldArtikel.getArtikelDTO();
        int mängdSålt = såldArtikel.getMängdSålt();

        ArtikelILager artikelILager = hämtaArtikelMedID(såldArtikelDTO.getartikelID());
        if(artikelILager != null){
            minskaLagerMängd(artikelILager, mängdSålt);
        }
    }

    /**
     * Hämtar ArtikelILager-objekt baserat på artikel-ID.
     * 
     * @param artikelID Det ID som ska matchas.
     * @return ArtikelILager-objektet eller null om inget matchar.
     */
    public ArtikelILager hämtaArtikelMedID(int artikelID){
        for(ArtikelILager artikel : lager){
            if(artikel.getArtikel().matcharArtikelID(artikelID)){
                return artikel;
            }
        }
        return null;
    }

    /**
     * Minskar lagermängden för en artikel med angiven såld mängd.
     * 
     * @param artikel Artikel i lagret som ska uppdateras.
     * @param mängdSålt Hur många enheter som sålts.
     */
    private void minskaLagerMängd(ArtikelILager artikel, int mängdSålt){
        int nyMängd = artikel.getMängd() - mängdSålt;
        artikel.setMängd(nyMängd);
    }
}