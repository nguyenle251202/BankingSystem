package infor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class export_log {

    private static final String File_Name = "export_log.txt";
    public static Object logTransaction;

    public static void logTransaction (String message) throws IOException {
        log("Transaction", message);
    }

    public static void logException (Exception e) throws IOException {
        log("Transaction", e.toString());
    }

    private static void log (String type, String message) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(File_Name, true))) {
            String timeStamp = new SimpleDateFormat("HH:mm:ss_dd/MM/yyyy").format(new Date());
            writer.write("[" + timeStamp + "]" + type + ": " + message );
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
