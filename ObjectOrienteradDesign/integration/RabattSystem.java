package ObjectOrienteradDesign.integration;
/**
 * Hanterar logik för att avgöra om kund är berättigad till rabatt.
 */
public class RabattSystem {
    /**
     * Returnerar priset efter att eventuell rabatt tillämpats.
     *
     * @param totalPris Ordinarie totalpris innan rabatt.
     * @param kundID ID för kunden som gör köpet.
     * @return Det nya priset efter rabatt (om tillämpad).
     */
    public float rabattKontroll(float totalPris, int kundID) {
        float nyttPris = 0;
        if (kundID == 123 || kundID == 456) {
            nyttPris = totalPris * 0.9f; // 10% rabatt
            return nyttPris;
        }
        nyttPris = totalPris;
        return nyttPris;
    }
}
