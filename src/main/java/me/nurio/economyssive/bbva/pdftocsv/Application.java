package me.nurio.economyssive.bbva.pdftocsv;

import java.io.File;
import java.io.IOException;
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

        List<String> convert = convert(args[0]);

        if (args.length == 2) {
            File destination = new File(args[1]);
            Files.writeString(destination.toPath(), String.join("\n", convert));
        }
    }

    private static List<String> convert(String sourcePath) throws IOException {
        File file = new File(sourcePath);
        String s = PdfToText.read(file);
        List<String> convert = TextToCsv.convert(s);
        convert.forEach(System.out::println);
        return convert;
    }

}