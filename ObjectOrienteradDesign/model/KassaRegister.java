package model;

/**
 * KassaRegister hanterar försäljningslogik för ett kassasystem.
 * 
 * Denna klass ansvarar för att:
 * - Uppdatera kassasaldot vid betalning
 * - Tillhandahålla aktuell kassastatus
 * 
 * Klassen fungerar som en central nod i modellen (MVC).
 */
public class KassaRegister {
    
    private float kassaSaldo;

    /**
     * Skapar ett nytt KassaRegister med ett initialt kassasaldo.
     *
     * @param belopp Startbeloppet i kassan.
     */
    public KassaRegister(float belopp) {
        this.kassaSaldo = belopp;
    }

    /**
     * Uppdaterar kassasaldot med det angivna beloppet.
     * Kan användas för att lägga till betalningar eller dra av växel.
     *
     * @param belopp Det belopp som ska läggas till i kassan.
     */
    public void uppdateraKassaSaldo(float belopp) {
        this.kassaSaldo += belopp;
    }

    /**
     * Returnerar det aktuella kassasaldot.
     *
     * @return Nuvarande saldo i kassan.
     */
    public float getSaldo() {
        return kassaSaldo;
    }
}