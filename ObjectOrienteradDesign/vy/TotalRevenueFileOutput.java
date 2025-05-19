package vy;

import observer.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String REVENUE_FILE = "total.reveneue.txt";
    private double newRevenue = 0;
    private PrintWriter writer;

    public TotalRevenueFileOutput(){
        try {
            this.writer = new PrintWriter(new FileWriter(REVENUE_FILE, true));
            writer.println();
            writer.println("-----------Nytt program startas---------");
        } catch (IOException exc) {
            System.out.println("Kan inte öppna intäktsfilen: " + exc.getMessage());
        }

    }

   @Override
    public void newRevenue(double totalRevenue){
        newRevenue += totalRevenue;
        writer.println(buildString(newRevenue));
        writer.flush();
    }

     private String createTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }

    private String buildString(double totalRevenue){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createTime());
        stringBuilder.append("-Nya totala intäkter: ");
        stringBuilder.append(String.format("%.2f", totalRevenue));
        stringBuilder.append(" SEK");
        return stringBuilder.toString();
    }
}

