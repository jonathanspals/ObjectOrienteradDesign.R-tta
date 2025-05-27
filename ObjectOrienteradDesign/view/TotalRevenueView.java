package view;

import model.RevenueObserver;

/**
 * En visningsklass som implementerar RevenueObserver.
 * 
 * Observerar förändringar i den totala intäkten och skriver ut den ackumulerade intäkten
 * till konsolen varje gång en ny försäljning registreras. Används som en del av observer-mönstret
 * för att separera logik och presentation.
 */
public class TotalRevenueView implements RevenueObserver {

    private double totalRevenue = 0;
    private static final TotalRevenueView TOTAL_REVENUE_VIEW = new TotalRevenueView();
    
    private TotalRevenueView(){
        
    }
    
    public static TotalRevenueView getTotalRevenueView(){
        return TOTAL_REVENUE_VIEW;
    }
    

    /**
     * Anropas när den totala intäkten uppdateras.
     * 
     * Skriver ut den nya sammanlagda intäkten till standardutmatningen i ett tydligt format.
     *
     * @param totalRevenue Den uppdaterade totala intäkten sedan programmet startades.
     */
    @Override
    public void newRevenue(double revenue) {
        totalRevenue += revenue;
        System.out.println("-----------TOTALA INTÄKTER------------");
        System.out.printf("Totala intäkten sen programmet startades: %.2f SEK %n", totalRevenue);
        System.out.println("-----------------------------\n");
    }
}