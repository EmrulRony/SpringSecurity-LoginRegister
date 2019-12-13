package com.yas.springsecurityloginregister.dao;

import com.yas.springsecurityloginregister.Entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}