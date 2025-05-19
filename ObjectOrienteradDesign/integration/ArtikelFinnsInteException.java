package integration;

/**
 * Ett kontrollerat undantag som kastas när ett artikel-ID inte finns i lagret.
 * Denna exception används för att signalera till programmet att en sökning i ArtikelRegister
 * misslyckades eftersom artikel-ID:t inte kunde hittas.
 */
public class ArtikelFinnsInteException extends Exception {
    private final String artikelID;

     /**
     * Skapar en ny ArtikelFinnsInteException med information om vilket ID som inte fanns.
     *
     * @param artikelID Det artikel-ID som inte hittades i lagret.
     */
    public ArtikelFinnsInteException(String artikelID) {
        super("Artikeln med ID \"" + artikelID + "\" kunde inte hittas i lagret.");
        this.artikelID = artikelID;
    }

    /**
     * Hämtar det artikel-ID som inte hittades.
     * @return Artikel-ID:t som saknas
     */
    public String getArtikelID() {
        return artikelID;
    }
}
