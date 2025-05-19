package integration;

/**
 * Ett okontrollerat undantag som kastas när systemet inte kan kontakta lagerdatabasen.
 * 
 * Denna exception används för att simulera att ett databasfel inträffar vid hämtning
 * av artikelinformation från ArtikelRegister.
 */
public class DatabasNedException extends RuntimeException {

    /**
     * Skapar en ny DatabasNedException med information om vilket artikel-ID
     * som försökte hämtas från databasen.
     *
     * @param artikelID Det artikel-ID som systemet försökte hämta vid databasfelet.
     */
    public DatabasNedException(String artikelID) {
        super("Kunde inte hämta artikel med ID \"" + artikelID + "\" på grund av ett databasfel.");
    }
}