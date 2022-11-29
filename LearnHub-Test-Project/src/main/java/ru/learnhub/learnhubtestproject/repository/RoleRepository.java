package ru.learnhub.learnhubtestproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.learnhub.learnhubtestproject.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);

}
