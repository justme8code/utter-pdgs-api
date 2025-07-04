package com.justme8code.utterfresh_production_gathering_sys.utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriteData {
    public void csvWriteData(String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] header = {"ID", "Name", "Email"};
            String[] person1 = {"1", "Thompson", "thommy@code.com"};
            String[] person2 = {"2", "Bolaji", "bolaji@devmail.com"};

            writer.writeNext(header);
            writer.writeNext(person1);
            writer.writeNext(person2);
            System.out.println("CSV written successfully ✨");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
