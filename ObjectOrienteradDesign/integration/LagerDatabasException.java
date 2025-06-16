package integration;

/**
 * Kastas när databasanslutningen till lagret misslyckas (simulerat undantag).
 * Används för att indikera att lagersystemet inte är tillgängligt.
 */
public class LagerDatabasException extends RuntimeException {

    /**
     * Skapar ett nytt LagerDatabasException med ett angivet felmeddelande.
     * 
     * @param msg Felmeddelandet som beskriver orsaken till undantaget.
     */
    public LagerDatabasException(String msg){
        super(msg);
    }
}