package com.send.bulk_mailer.service;

import com.opencsv.CSVReader;
import com.send.bulk_mailer.model.User;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public List<User> readUsers() {

        List<User> users = new ArrayList<>();

        try {

            InputStream inputStream =
                    getClass()
                            .getClassLoader()
                            .getResourceAsStream("users.csv");

            if (inputStream == null) {
                throw new RuntimeException("users.csv file not found");
            }

            CSVReader csvReader =
                    new CSVReader(
                            new InputStreamReader(inputStream)
                    );

            List<String[]> rows = csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) {

                String[] row = rows.get(i);

                User user = new User();

                user.setName(row[0]);
                user.setEmail(row[1]);
                user.setBrandName(row[2]);
                user.setSendMail(row[3]);

                users.add(user);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return users;
    }

    public List<User> getEligibleUsers() {

        List<User> allUsers = readUsers();

        List<User> eligibleUsers = new ArrayList<>();

        for (User user : allUsers) {

            if ("YES".equalsIgnoreCase(user.getSendMail())) {

                eligibleUsers.add(user);

            }
        }

        return eligibleUsers;
    }
}