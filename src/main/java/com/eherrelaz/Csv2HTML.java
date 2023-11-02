package com.eherrelaz;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Csv2HTML {
    public static void main() throws IOException {
        try (FileReader reader = new FileReader("Output_TestSmellDetection.csv");
                FileWriter writer = new FileWriter("output.html")) {

            // Create an HTML document
            Document doc = new Document("");
            Element table = doc.appendElement("table");

            // Read CSV file and create HTML table
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Element row = table.appendElement("tr");
                for (String field : record) {
                    row.appendElement("td").text(field);
                }
            }

            // Write the HTML to the output file
            writer.write(doc.outerHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().exec("rm -f Output_TestSmellDetection.csv");
    }
}
