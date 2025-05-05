package ObjectOrienteradDesign.integration;
import ObjectOrienteradDesign.model.Kvitto;
/**
 * Hanterar uppdatering av lagret baserat på försäljning.
 */
public class LagerData {
    /**
     * Uppdaterar lagret baserat på ett kvitto (artiklar som sålts).
     *
     * @param kvitto Kvitto med information om vad som sålts.
     */
    public void uppdateraLager(Kvitto kvitto){
        if(kvitto == null){
            throw new IllegalArgumentException("Kvitto kan inte vara null.");
        }
        System.out.println("Lager uppdaterat baserat på: " + kvitto.getlistaAvArtiklar());
        }
    
}
