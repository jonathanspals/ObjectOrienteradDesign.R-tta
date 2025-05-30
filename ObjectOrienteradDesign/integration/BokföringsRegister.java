package integration;

import java.util.ArrayList;
import java.util.List;
import model.DTO.Kvitto;

public class BokföringsRegister {
    private List<Kvitto> kvitton;
    private static final BokföringsRegister BOKFÖRINGS_REGISTER = new BokföringsRegister();
    
    /**
     * Creates a new instance of the accounting system.
     */
    private BokföringsRegister(){
        this.kvitton = new ArrayList<>();
    }
    
    /**
     * @return      The only instance of this singleton.
     */
    public static BokföringsRegister getBokföringsRegister(){
        return BOKFÖRINGS_REGISTER;
    }
    
    /**
     * Records a receipt in the accounting system
     * @param receipt       The receipt to be saved.
     */
    public void bokföra(Kvitto kvitto){
        kvitton.add(kvitto);
    }
    
}
