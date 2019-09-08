package com.elephant.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elephant.domain.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String string);
}