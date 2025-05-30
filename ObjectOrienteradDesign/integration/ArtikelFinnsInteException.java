package integration;

/**
 * Denna exception kastas när en artikel med ett angivet ID inte finns i lagret.
 */
public class ArtikelFinnsInteException extends Exception {
     private final int artikelID;
    
    /**
     * Creates a new instance with a message specifying 
     * the itemID that is not in the inventory.
     * @param artikelID        The itemID not found in the inventory.
     */
    public ArtikelFinnsInteException(int artikelID){
        super("Artikel med ID: " + artikelID + " hittades inte i lagret");
        this.artikelID = artikelID;
    }

    /**
     * 
     * @return      The itemID not found in the inventory.
     */
    public int hämtaFelArtikelID(){
        return this.artikelID;
    }
}
