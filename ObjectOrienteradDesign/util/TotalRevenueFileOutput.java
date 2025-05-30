package util;

import model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * {@code TotalRevenueFileOutput} är en implementering av {@link RevenueObserver}
 * som loggar total intäkt till en textfil varje gång den uppdateras.
 * 
 * Klassen använder en {@link PrintWriter} för att skriva intäktsinformation
 * till en fil med tidsstämpel, vilket möjliggör spårbarhet av intäkter över tid.
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String REVENUE_FILE = "total.revenue.txt";
    private PrintWriter writer;

    /**
     * Skapar en ny {@code TotalRevenueFileOutput} och öppnar (eller skapar) 
     * filen {@code total.revenue.txt} för att kunna logga intäkter.
     * 
     * Skriver även en rad som markerar att ett nytt program har startats.
     */
    public TotalRevenueFileOutput() {
        try {
            this.writer = new PrintWriter(new FileWriter(REVENUE_FILE, true));
            writer.println();
            writer.println("-----------Nytt program startas---------");
        } catch (IOException exc) {
            System.out.println("Kan inte öppna intäktsfilen: " + exc.getMessage());
        }
    }

    /**
     * Metoden anropas när den totala intäkten uppdateras.
     * 
     * Den loggar den nya totala intäkten tillsammans med tidsstämpel i loggfilen.
     *
     * @param totalRevenue Den nya totala intäkten sedan programstart.
     */
    @Override
    public void newRevenue(double totalRevenue) {
        writer.println(buildString(totalRevenue));
        writer.flush();
    }

    /**
     * Skapar en tidsstämpel i ett format anpassat för loggning.
     *
     * @return En sträng som representerar aktuell tid och datum.
     */
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }

    /**
     * Bygger upp en formaterad sträng för loggfilen med tid och ny intäkt.
     *
     * @param totalRevenue Den nya totala intäkten.
     * @return En formaterad sträng med tidsstämpel och intäktsinformation.
     */
    private String buildString(double totalRevenue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createTime());
        stringBuilder.append(" -- Nya totala intäkter: ");
        stringBuilder.append(String.format("%.2f", totalRevenue));
        stringBuilder.append(" SEK");
        return stringBuilder.toString();
    }
}