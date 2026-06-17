package com.send.bulk_mailer.service;

import com.send.bulk_mailer.model.User;
import com.send.bulk_mailer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderService {

    private final CsvService csvService;
    private final UserRepository userRepository;

    public DataLoaderService(
            CsvService csvService,
            UserRepository userRepository) {

        this.csvService = csvService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadData() {

        if (userRepository.count() == 0) {

            List<User> users = csvService.readUsers();

            userRepository.saveAll(users);

            System.out.println(
                    users.size() +
                            " users loaded into PostgreSQL");
        }
    }
}