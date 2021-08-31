package me.nurio.economyssive.bbva.pdftocsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        if (args.length <= 0 || args.length > 2) {
            System.err.println("Usage: java -jar application.jar filetoconvert.pdf [destination.csv]");
            return;
        }

        if (args.length == 2) {
            File destination = new File(args[1]);
            if (destination.exists()) {
                System.err.printf("Unable to use '%s' as a destination. File already exists.%n", args[1]);
                return;
            }
        }

        // Prepare stream and call the BBVA PDF to CSV processor.
        File pdfFile = new File(args[0]);
        InputStream inputStream = new FileInputStream(pdfFile);
        List<String> csvLines = BbvaPdfToCsv.process(inputStream);

        // Print lines in terminal
        csvLines.forEach(System.out::println);

        if (args.length == 2) {
            File destination = new File(args[1]);
            Files.writeString(destination.toPath(), String.join("\n", csvLines));
        }
    }

}