package com.ghifar.lms.controllers;

import com.ghifar.lms.exceptions.NotFoundException;

import com.ghifar.lms.models.Books;
import com.ghifar.lms.models.ERole;
import com.ghifar.lms.models.Role;
import com.ghifar.lms.models.User;
import com.ghifar.lms.payload.response.MessageResponse;
import com.ghifar.lms.repository.RoleRepository;
import com.ghifar.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PatronController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/patrons")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPatronByAdmin(@RequestBody User patron) {
        if (userRepository.existsByUsername(patron.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(patron.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(patron.getUsername(),
                patron.getEmail(),
                encoder.encode(patron.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role patronRole = roleRepository.findByName(ERole.ROLE_PATRON)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(patronRole);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Patron Created successfully!"));

    }

    @GetMapping("/patrons")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllPatrons() {
        Optional<Role> role = roleRepository.findByName(ERole.ROLE_PATRON);
        return userRepository.findByRoles(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/patrons/{id}")
    public ResponseEntity<User> getPatronById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/patrons/{id}")
    public ResponseEntity<User> updatePatron(@PathVariable Integer id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));

        user.setUsername(userDetails.getUsername());
        Set<Role> roles = new HashSet<>();

        Role patronRole = roleRepository.findByName(ERole.ROLE_PATRON)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(patronRole);
        user.setRoles(roles);
        user.setUsername(userDetails.getUsername());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatron(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Patron with id "+ id +" does not exist."));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
