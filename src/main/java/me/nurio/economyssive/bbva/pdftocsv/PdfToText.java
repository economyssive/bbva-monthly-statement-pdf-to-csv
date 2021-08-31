package me.nurio.economyssive.bbva.pdftocsv;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfToText {

    public static String read(File pdfFile) throws IOException {
        PDDocument document = PDDocument.load(pdfFile);

        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        return text;
    }

}