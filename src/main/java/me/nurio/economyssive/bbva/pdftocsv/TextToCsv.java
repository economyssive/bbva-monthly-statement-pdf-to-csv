package me.nurio.economyssive.bbva.pdftocsv;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToCsv {

    static final String regex = "(\\d{2}[\\/]\\d{2})[ ](\\d{2}[\\/]\\d{2})[ ](.*)\r?\n(.*)\r?\n([\\-0-9,]+)[ ]([0-9,]+)";
    static final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    public static List<String> convert(String source) {
        List<String> lines = new ArrayList<>();

        // Add the CSV headers
        lines.add("OPERATION DATE|VALUE DATE|OPERATION TYPE|DESCRIPTION|AMOUNT|BALANCE");

        // Find report year at the page headers
        String year = findYear(source);

        // Add all the transactions line per line
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            String operationDate = applyYear(matcher.group(1), year);
            String valueDate = applyYear(matcher.group(2), year);
            String operationType = matcher.group(3);
            String description = matcher.group(4).toUpperCase(Locale.ROOT);
            String amount = matcher.group(5).replaceAll(",", ".");
            String balance = matcher.group(6).replaceAll(",", ".");

            String line = String.format(
                "%s|%s|%s|%s|%s|%s",
                operationDate,
                valueDate,
                operationType,
                description,
                amount,
                balance
            );
            lines.add(line);
        }

        return lines;
    }

    private static String findYear(String source) {
        final String regex = "EXTRACTO DE \\w+ (\\d{4})";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(source);

        if (!matcher.find()) {
            return "1971"; // Unknown date
        }

        return matcher.group(1);
    }

    private static String applyYear(String date, String year) {
        return date + "/" + year;
    }

}