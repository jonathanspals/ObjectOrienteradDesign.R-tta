package model.DTO;

/**
 * Contains information about a single item
 *
 */
public class ArtikelDTO {
    private final String namn;
    private final int artikelID;
    private final float artikelPris;
    private final float VAT;

    
    /**
     * Creates a new instance of the object
     * @param name      The name of the item
     * @param itemID    The items' identifier
     * @param price     The price of the item
     * @param vat       The VAT-rate of the item as a percentage
     */
    public ArtikelDTO(String namn, int artikelID, float artikelPris, float VAT) {
        this.namn = namn;
        this.artikelID = artikelID;
        this.artikelPris = artikelPris;
        this.VAT = VAT;
    }
    
    public String getnamn(){
        return namn;
    }
    
    public int getartikelID(){
        return artikelID;
    }
    
    public float getVAT(){
        return VAT;
    }
    
    public float getartikelPris(){
        return artikelPris;
    }
    
    /**
     * Checks if a searched item identifier matches the item identifier of the item.
     * @param searchedItemID    The item identifier to compare to the object       
     * @return boolean          true if they are identical, otherwise false
     */
    public boolean matcharArtikelID(int söktArtikelID){
        return this.artikelID == söktArtikelID;
    }
}