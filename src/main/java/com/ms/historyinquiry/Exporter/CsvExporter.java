package com.ms.historyinquiry.Exporter;

import com.ms.historyinquiry.Model.Vaccine;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
public class CsvExporter {

    public byte[] exportToCsv(List<Vaccine> vaccinationHistory) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {

            // Write header
            writer.write("Vacuna,Fabricante,Fecha de vacunación\n");

            // Write data
            for (Vaccine vaccine : vaccinationHistory) {
                writer.write(vaccine.getName() + ",");
                writer.write(vaccine.getManufacturer() + ",");
            }

            writer.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
