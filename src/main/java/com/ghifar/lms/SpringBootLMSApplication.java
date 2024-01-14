package com.ghifar.lms;

import com.ghifar.lms.models.ERole;
import com.ghifar.lms.models.Role;
import com.ghifar.lms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootLMSApplication implements CommandLineRunner {
	@Autowired
	RoleRepository roleRepository;
	public static void main(String[] args) {

		SpringApplication.run(SpringBootLMSApplication.class, args);
	}

	@Override
	public void run(String... args)throws Exception {
		Role roleAdmin = new Role();
		roleAdmin.setName(ERole.ROLE_ADMIN);
		roleRepository.save(roleAdmin);
		Role roleUser = new Role();
		roleUser.setName(ERole.ROLE_USER);
		roleRepository.save(roleUser);
		Role rolePatron = new Role();
		rolePatron.setName(ERole.ROLE_PATRON);
		roleRepository.save(rolePatron);
	}
}
