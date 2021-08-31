package me.nurio.economyssive.bbva.pdftocsv;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BbvaPdfToCsv {

    public static List<String> process(InputStream source) throws IOException {
        String contents = PdfToText.read(source);
        return TextToCsv.convert(contents);
    }

}