package model;

/**
 * Observerinterface som används för att notifiera när en försäljning avslutas
 */
public interface RevenueObserver {
    /**
     * Anropas när en ny försäljning har skett och intäkten uppdateras
     * @param revenue Den uppdaterade totala intäkten
     */
    void newRevenue(double revenue);
}