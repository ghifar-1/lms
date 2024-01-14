package com.ghifar.lms.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ghifar.lms.models.ERole;
import com.ghifar.lms.models.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


import com.ghifar.lms.models.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  List<User> findByRoles(Optional<Role> role);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
