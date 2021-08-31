package me.nurio.economyssive.bbva.pdftocsv;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

public class PdfToText {

    public static String read(InputStream pdf) throws IOException {
        PDDocument document = PDDocument.load(pdf);

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        return text;
    }

}