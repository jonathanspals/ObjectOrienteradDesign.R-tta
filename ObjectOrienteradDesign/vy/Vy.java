package ObjectOrienteradDesign.vy;

import ObjectOrienteradDesign.kontroller.Kontroller;
/**
 * Representerar användargränssnittet (View) i MVC-modellen.
 * Skapar och håller referens till kontrollerobjektet.
 */
public class Vy {
    private Kontroller controller;

    /**
     * Privat konstruktor för att initiera vy med en kontroller.
     *
     * @param controller En instans av {@code Kontroller}.
     */
    private Vy(Kontroller controller) {
        this.controller = controller;
    }

    /**
     * smetod för att skapa en ny {Vy}-instans.
     *
     * @param controller En {Kontroller}-instans.
     * @return En ny {@code Vy}.
     * 
     */
    public static Vy Create(Kontroller controller) {
        return new Vy(controller);
    }
}