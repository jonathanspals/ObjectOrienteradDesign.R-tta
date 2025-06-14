package integration;

import java.util.ArrayList;
import java.util.List;

import model.DTO.ArtikelDTO;
import model.DTO.SkanningsDTO;
import model.DTO.SåldArtikelDTO;


/**
 * ArtikelRegister ansvarar för att hantera åtkomst och uppdatering av artiklar i lagret.
 * Det implementerar en singletonstruktur och använder DTO för extern kommunikation.
 */
public class ArtikelRegister {
    private final List<ArtikelILager> lager = new ArrayList<>();
    private static final ArtikelRegister ARTIKEL_REGISTER = new ArtikelRegister();

    /**
     * Privat konstruktor som initialiserar lagret med hårdkodade artiklar.
     */
    private ArtikelRegister() {
        läggTillArtiklar();
    }

    /**
     * Hämtar den enda instansen av ArtikelRegister.
     *
     * @return Singleton-instansen av ArtikelRegister
     */
    public static ArtikelRegister getArtikelRegister() {
        return ARTIKEL_REGISTER;
    }

    /**
     * Lägger till hårdkodade testartiklar i lagret.
     */
    private void läggTillArtiklar() {
        lager.add(new ArtikelILager(new ArtikelDTO("BigWheel Oatmeal", 123, 29.90f, 6), 50));
        lager.add(new ArtikelILager(new ArtikelDTO("YouGoGo BlueBerry", 456, 14.90f, 6), 50));
    }

    /**
     * Hämtar artikelbeskrivning från lagret baserat på ID.
     *
     * @param artikelID Artikelns ID
     * @return ArtikelDTO med artikelns information
     * @throws ArtikelFinnsInteException Om artikel-ID:t inte finns
     */
    public ArtikelDTO hämtaArtikelBeskrivning(int artikelID) throws ArtikelFinnsInteException {
        if (artikelID == 999) {
            throw new LagerDatabasException("Databasen kan inte nås");
        }
        for (ArtikelILager artikelILager : lager) {
            if (artikelILager.getArtikel().matcharArtikelID(artikelID)) {
                return artikelILager.getArtikel();
            }
        }
        throw new ArtikelFinnsInteException(artikelID);
    }

    /**
     * Uppdaterar lagersaldot baserat på sålda artiklar i DTO.
     *
     * @param säljInformation DTO som innehåller artiklar och mängder
     */
    public void uppdateraLager(SkanningsDTO säljInformation) {
        if (säljInformation == null) {
            return;
        }
        for (SåldArtikelDTO såldArtikelDTO : säljInformation.getSåldaArtikelDTOs()) {
            uppdateraLagerMedArtikelDTO(såldArtikelDTO);
        }
    }

    /**
     * Hjälpmetod som minskar lagersaldot för en specifik artikel baserat på DTO.
     *
     * @param såldArtikelDTO DTO med artikelID och såld mängd
     */
    private void uppdateraLagerMedArtikelDTO(SåldArtikelDTO såldArtikelDTO) {
        int artikelID = såldArtikelDTO.getArtikelID();
        int mängdSålt = såldArtikelDTO.getMängd();

        ArtikelILager artikelILager = hämtaArtikelMedID(artikelID);
        if (artikelILager != null) {
            minskaLagerMängd(artikelILager, mängdSålt);
        }
    }

    /**
     * Hämtar artikelobjekt från lagret baserat på ID.
     *
     * @param artikelID Artikelns ID
     * @return ArtikelILager-objekt eller null om det inte finns
     */
    public ArtikelILager hämtaArtikelMedID(int artikelID) {
        for (ArtikelILager artikel : lager) {
            if (artikel.getArtikel().matcharArtikelID(artikelID)) {
                return artikel;
            }
        }
        return null;
    }

    /**
     * Minskar lagersaldot för en artikel.
     *
     * @param artikel    Artikel i lager
     * @param mängdSålt Antal sålda enheter
     */
    private void minskaLagerMängd(ArtikelILager artikel, int mängdSålt) {
        int nyMängd = artikel.getMängd() - mängdSålt;
        artikel.setMängd(nyMängd);
    }
}
