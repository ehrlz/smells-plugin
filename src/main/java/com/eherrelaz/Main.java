package com.eherrelaz;

import com.eherrelaz.testsmell.AbstractSmell;
import com.eherrelaz.testsmell.ResultsWriter;
import com.eherrelaz.testsmell.TestFile;
import com.eherrelaz.testsmell.TestSmellDetector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Project name
        File readme = new File("README.md");
        byte[] buff = new byte[28];

        FileInputStream fileInputStream = new FileInputStream(readme);
        fileInputStream.read(buff);
        fileInputStream.close();

        TestSmellDetector testSmellDetector = new TestSmellDetector();

        // Hardcoded. Recursive alg. pending
        String name = new String(buff).substring(3);
        String str = name
                + ",src/test/java/es/upm/grise/prof/curso2023/ejercicio3/SUTTest.java,src/main/java/es/upm/grise/prof/curso2023/ejercicio3/SUT.java";

        String[] lineItem;
        TestFile testFile;
        List<TestFile> testFiles = new ArrayList<>();
        // use comma as separator
        lineItem = str.split(",");

        // check if the test file has an associated production file
        if (lineItem.length == 2) {
            testFile = new TestFile(lineItem[0], lineItem[1], "");
        } else {
            testFile = new TestFile(lineItem[0], lineItem[1], lineItem[2]);
        }

        testFiles.add(testFile);

        /*
         * Initialize the output file - Create the output file and add the column names
         */
        ResultsWriter resultsWriter = ResultsWriter.createResultsWriter();
        List<String> columnNames;
        List<String> columnValues;

        columnNames = testSmellDetector.getTestSmellNames();
        columnNames.add(0, "App");
        columnNames.add(1, "TestClass");
        columnNames.add(2, "TestFilePath");
        columnNames.add(3, "ProductionFilePath");
        columnNames.add(4, "RelativeTestFilePath");
        columnNames.add(5, "RelativeProductionFilePath");

        resultsWriter.writeColumnName(columnNames);

        /*
         * Iterate through all test files to detect smells and then write the output
         */
        TestFile tempFile;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date;
        for (TestFile file : testFiles) {
            date = new Date();
            System.out.println(dateFormat.format(date) + " Processing: " + file.getTestFilePath());
            System.out.println("Processing: " + file.getTestFilePath());

            // detect smells
            tempFile = testSmellDetector.detectSmells(file);

            // write output
            columnValues = new ArrayList<>();
            columnValues.add(file.getApp());
            columnValues.add(file.getTestFileName());
            columnValues.add(file.getTestFilePath());
            columnValues.add(file.getProductionFilePath());
            columnValues.add(file.getRelativeTestFilePath());
            columnValues.add(file.getRelativeProductionFilePath());
            for (AbstractSmell smell : tempFile.getTestSmells()) {
                try {
                    columnValues.add(String.valueOf(smell.getHasSmell()));
                } catch (NullPointerException e) {
                    columnValues.add("");
                }
            }
            resultsWriter.writeLine(columnValues);
        }

        System.out.println("end");
    }

}
