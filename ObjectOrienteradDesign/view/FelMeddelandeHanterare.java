package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Denna klass ansvarar för att visa felmeddelanden till användaren.
 * Felmeddelanden formateras med aktuell tidsstämpel.
 */

public class FelMeddelandeHanterare {
    /**
     * Visar det angivna felmeddelandet tillsammans med en tidsstämpel.
     *
     * @param msg Det felmeddelande som ska visas.
     */
    void visaFelMeddelande(String msg) {
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(visaTid());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder);
    }

    /**
     * Hämtar nuvarande tid formaterad som datum och tid i medium-stil.
     *
     * @return En sträng med tidsstämpel.
     */
    private String visaTid() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
