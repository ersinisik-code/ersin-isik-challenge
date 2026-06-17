package com.camunda.challenge.util;

import com.camunda.challenge.model.User;
import org.apache.commons.csv.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {

    public void export(
            List<User> users,
            String fileName
    ) {

        try (
                FileWriter writer =
                        new FileWriter(fileName);

                CSVPrinter csvPrinter =
                        new CSVPrinter(
                                writer,
                                CSVFormat.DEFAULT
                                        .builder()
                                        .setHeader(
                                                "id",
                                                "email",
                                                "firstName",
                                                "lastName"
                                        )
                                        .build()
                        )
        ) {

            for (User user : users) {

                csvPrinter.printRecord(
                        user.id(),
                        user.email(),
                        user.firstName(),
                        user.lastName()
                );
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "CSV export failed",
                    e
            );
        }
    }
}