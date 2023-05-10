package com.doctoranywhere.Assignment.repository;

import com.doctoranywhere.Assignment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
}

