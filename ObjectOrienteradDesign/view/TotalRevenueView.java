package view;

import model.RevenueObserver;

/**
 * En visningsklass som implementerar {@code RevenueObserver}.
 * <p>
 * Observerar förändringar i den totala intäkten och skriver ut den ackumulerade intäkten
 * till konsolen varje gång en ny försäljning registreras. Används som en del av observer-mönstret
 * för att separera logik (modell) från presentation (vy).
 */
public class TotalRevenueView implements RevenueObserver {

    private double totalRevenue = 0;
    private static final TotalRevenueView TOTAL_REVENUE_VIEW = new TotalRevenueView();

    /**
     * Privat konstruktor för singleton-instans.
     */
    private TotalRevenueView() {
    }

    /**
     * Hämtar den enda instansen av {@code TotalRevenueView}.
     *
     * @return Singleton-instansen.
     */
    public static TotalRevenueView getTotalRevenueView() {
        return TOTAL_REVENUE_VIEW;
    }

    /**
     * Anropas när en ny intäkt registrerats.
     * Summerar och skriver ut den totala intäkten till standardutmatning.
     *
     * @param revenue Den senaste intäkten att lägga till.
     */
    @Override
    public void newRevenue(double revenue) {
        totalRevenue += revenue;
        System.out.println("-----------TOTALA INTÄKTER------------");
        System.out.printf("Totala intäkten sen programmet startades: %.2f SEK %n", totalRevenue);
        System.out.println("-----------------------------\n");
    }
}