package com.ghifar.lms.repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ghifar.lms.models.ERole;
import com.ghifar.lms.models.Role;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
