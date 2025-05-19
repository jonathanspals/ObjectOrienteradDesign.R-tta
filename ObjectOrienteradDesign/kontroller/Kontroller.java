package kontroller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import model.KassaRegister;
import integration.Kassa;
import model.Kvitto;
import integration.RabattSystem;
import integration.LagerData;
import model.DTO.ArtikelDTO;
import integration.ArtikelFinnsInteException;
import integration.DatabasNedException;
import observer.RevenueNotifier;
import observer.RevenueObserver;
import vy.Vy;




/**
 * Kontroller-klassen hanterar huvudflödet för kassaprocessen, inklusive inläsning av artiklar,
 * beräkning av rabatt, betalning och skapande av kvitto.
 */
public class Kontroller {
    private RabattSystem rabattSystem = new RabattSystem();
    private Kassa kassa = new Kassa();
    private Vy view = new Vy();
    private KassaRegister kassaRegister = new KassaRegister(kassa);
    private LagerData lagerData = new LagerData();
    private final RevenueNotifier revenueNotifier = new RevenueNotifier();

    /**
     * Startar kassaflödet genom att hämta in artikeldata, tillämpa rabatt, ta emot betalning,
     * skapa kvitto och uppdatera lager och kassa.
     */
    public void hanteraKassaFlöde() {
        LocalTime tid = kassaRegister.setTimeOfSale();
        view.startaFörsäljning(tid);

        List<ArtikelDTO> artikelInformation = view.artikelInformation();

        läggTillArtiklar(artikelInformation);

        int kundID = view.kundID();
        float nyttpris = flaggaRabatt(kundID);

        float betalatBelopp = view.betalatBelopp();
        float växel = processSale(nyttpris, betalatBelopp);

        Kvitto kvitto = skapaKvitto(betalatBelopp, nyttpris, växel);

        revenueNotifier.notifyObservers(nyttpris);

        kassa.uppdateraKassaSaldo(växel);
        lagerData.uppdateraLager(kvitto);

        view.skrivUtKvitto(kvitto);
        view.avslutaFörsäljning();
    }
    /**
     * Lägger till en observerare som ska notifieras vid ny försäljning.
     *
     * @param observer Observer-objekt som implementerar RevenueObserver
     */
    public void addRevenueObserver(RevenueObserver observer) {
        revenueNotifier.addObserver(observer);
    }

    /**
     * Bearbetar betalningen och beräknar växel.
     *
     * @param nyttPris Det pris som kunden ska betala efter eventuell rabatt.
     * @param betalatBelopp Det belopp kunden betalat.
     * @return Växel som ska ges tillbaka till kunden.
     */
    private float processSale(float nyttPris, float betalatBelopp) {
        float växel = kassaRegister.beräknaVäxel(betalatBelopp, nyttPris);
        view.skrivUtVäxel(växel);
        return växel;
    }

    /**
     * Kontrollerar om kunden är berättigad till rabatt och beräknar nytt pris.
     *
     * @param kundID Kundens ID.
     * @return Nytt pris efter rabatt (om tillämplig).
     */
    private float flaggaRabatt(int kundID) {
        float totalPris = (float) kassaRegister.getTotalPris();
        float nyttPris = rabattSystem.rabattKontroll(totalPris, kundID);
        float rabatt = totalPris - nyttPris;

        if (rabatt > 0) {
            view.skrivUtRabatt(rabatt);
        } else {
            view.ingenRabatt();
        }
        return nyttPris;
    }

    /**
     * Skapar ett kvitto baserat på försäljningen.
     *
     * @param betalatBelopp Det belopp kunden betalade.
     * @param nyttPris Det pris efter rabatt.
     * @param växel Det belopp som återlämnas till kunden.
     * @return Ett {@link Kvitto}-objekt som innehåller all försäljningsinformation.
     */
    private Kvitto skapaKvitto(float betalatBelopp, float nyttPris, float växel) {
        float totalPris = (float) kassaRegister.getTotalPris();
        LocalDate datum = LocalDate.now();
        float rabatt = totalPris - nyttPris;
        LocalTime tidförsäljning = LocalTime.now();

        return new Kvitto(tidförsäljning, totalPris, kassaRegister.calculateTotalVAT(),
                betalatBelopp, kassaRegister.artiklarSomString(), växel,
                datum, nyttPris, rabatt);
    }

    /**
     * Lägger till artiklar i försäljningen baserat på en lista av {@link ArtikelDTO}.
     * Hanterar eventuella fel som uppstår vid felaktigt artikel-ID eller databaskommunikation.
     *
     * @param artikelLista Lista med artiklar och antal som ska registreras i försäljningen.
     */
    private void läggTillArtiklar(List<ArtikelDTO> artikelLista) {
        for (ArtikelDTO artikelDTO : artikelLista) {
                try {
                kassaRegister.artikelIDOchAntal(artikelDTO.getartikelID(), artikelDTO.getantalAvArtikel());
                } catch (DatabasNedException e) {
                view.skrivFelmeddelandeTillAnvändare("Databasen kunde inte kontaktas. Försök igen senare.", e);
                } catch (ArtikelFinnsInteException e) {
                view.skrivFelmeddelandeTillAnvändare("Artikeln med ID " + artikelDTO.getartikelID() + " finns inte i systemet.", e);
            }
        }
    }
}
