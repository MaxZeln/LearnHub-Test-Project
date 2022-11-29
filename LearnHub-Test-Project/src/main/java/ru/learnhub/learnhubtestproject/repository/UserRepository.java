package ru.learnhub.learnhubtestproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnhub.learnhubtestproject.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);

    Optional<User> getUserByLogin(String login);

}
