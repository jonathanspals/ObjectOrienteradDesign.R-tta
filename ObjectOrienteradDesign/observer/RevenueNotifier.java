package observer;

import java.util.ArrayList;
import java.util.List;

import model.RevenueObserver;

/**
 * Ansvarar för att hålla koll på observers och notifiera dem vid nya intäkter.
 */
public class RevenueNotifier {
    private final List<RevenueObserver> observers = new ArrayList<>();
    private double totalRevenue = 0;
    
    /**
     * Registrerar en observer.
     */
    public void addObserver(RevenueObserver observer) {
        observers.add(observer);
    }
    
    /**
     * Notifierar alla observerare om ny totalintäkt.
     * @param revenueFromSale Intäkten från en enskild försäljning
     */
    public void notifyObservers(double revenueFromSale) {
        totalRevenue += revenueFromSale;
        for (RevenueObserver observer : observers) {
            observer.newRevenue(totalRevenue);
        }
    }
}