package com.send.bulk_mailer.repository;

import com.send.bulk_mailer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findBySendMailIgnoreCaseOrderByIdAsc(String sendMail);

}