package com.eherrelaz;

import java.io.IOException;
import java.util.Calendar;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


@Mojo(name = "smells", defaultPhase = LifecyclePhase.VALIDATE)
public class Plugin extends AbstractMojo
{

    @Parameter(property = "smells.csv_path", defaultValue = "file.csv")
    String csvPath = "";
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

            String[] args = new String[1];
            args[0] = "file.csv";

            Main.main(args);
            
            String time =  String.valueOf(Calendar.getInstance().getTimeInMillis());

            try (FileReader reader = new FileReader("Output_TestSmellDetection.csv");
   
                FileWriter writer = new FileWriter("output" + time + ".html")) {

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
