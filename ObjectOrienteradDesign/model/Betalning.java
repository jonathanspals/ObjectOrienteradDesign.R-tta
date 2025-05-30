package model;

public class Betalning{
    private float belopp;

    /**
     * Creates an instance of a payment.
     * @param belopp      The amount paid.
     */
    public Betalning (float belopp) {
        this.belopp = belopp;
    }
    
    public float getBelopp(){
        return belopp;
    }
 
    
}