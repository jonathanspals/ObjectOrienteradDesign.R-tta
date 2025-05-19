package vy;

import observer.RevenueObserver;

/**
 * Visar total försäljningsintäkt i konsolen.
 */
public class TotalRevenueView implements RevenueObserver {
    @Override
    public void newRevenue(double totalRevenue) {
        System.out.println("============================");
        System.out.printf("Totala intäkter: %.2f SEK\n", totalRevenue);
        System.out.println("============================");
    }
}
