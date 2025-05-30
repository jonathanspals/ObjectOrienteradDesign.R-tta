package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import util.FileLogger;

/**
 * Loggar felmeddelanden till en fil med namn "log.txt".
 * Skriver ut både felmeddelande, tid och stack trace.
 */
public class FileLogger {
    private PrintWriter logStream;

    /**
     * Skapar en instans och loggfilen "log.txt" (lägger till ny text).
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("KAN INTE LOGGA.");
            ioe.printStackTrace();
        }
    }

    /**
     * Loggar ett generellt meddelande.
     *
     * @param message Meddelandet som ska loggas.
     */
    public void log(String message) {
        logStream.println("[" + LocalDateTime.now() + "] " + message);
        logStream.flush();
    }

    /**
     * Loggar en exception med stack trace.
     *
     * @param e Exception att logga.
     */
    public void logException(Exception e) {
    logStream.println("Exception: " + e.getClass().getName() + " - " + e.getMessage());
    e.printStackTrace(logStream);
    logStream.flush();
}

}