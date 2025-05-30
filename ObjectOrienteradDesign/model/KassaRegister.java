package model;

/**
 * KassaRegister hanterar försäljningslogik för ett kassasystem.
 * 
 * Denna klass ansvarar för att:
 * - Skanna och registrera artiklar
 * - Hålla reda på alla artiklar som är del av en försäljning
 * - Beräkna totalpris, moms och växel
 * - Sammanställa artikellista till kvittot
 * - Hantera tidsstämpling av försäljningen
 * 
 * Klassen fungerar som en central nod i modellen (MVC) och använder både ArtikelRegister och Kassa.
 */

public class KassaRegister {
    
    private float kassaSaldo;

    public KassaRegister (float belopp){
        this.kassaSaldo = belopp;
    }

    public void uppdateraKassaSaldo(float belopp){
        this.kassaSaldo += belopp;
    }

    public float getSaldo(){
        return kassaSaldo;
    }
    
    

}