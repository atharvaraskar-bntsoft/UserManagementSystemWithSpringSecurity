// package com.bnt.UserManagementSystem.configuration;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
// import com.bnt.UserManagementSystem.model.Role;
// import com.bnt.UserManagementSystem.model.User;
// import com.bnt.UserManagementSystem.repository.UserRepository;
// import com.bnt.UserManagementSystem.repository.RoleRespository;

// @Component
// public class AdminInitializer implements CommandLineRunner {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private RoleRespository roleRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

//     @Override
//     public void run(String... args) throws Exception {
//         // Fetch or create the ADMIN role
//         Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
//             Role role = new Role();
//             role.setName("ADMIN");
//             return roleRepository.save(role);  // Ensure the role is saved and check for errors
//         });

//         // Improved logging for role creation or fetching
//         if (adminRole != null) {
//             logger.info("Admin Role fetched/created: {}", adminRole.getName());
//         } else {
//             logger.error("Error: Admin Role could not be created or fetched.");
//         }

//         // Check if admin user exists, create it if not
//         if (userRepository.findByUserName("admin").isEmpty()) {
//             User admin = new User();
//             admin.setUserName("admin");
//             admin.setPassword(passwordEncoder.encode("admin123"));
//             admin.setEmail("admin@example.com");

//             // Add role directly since it's already initialized
//             admin.getRoles().add(adminRole);

//             userRepository.save(admin);  // Save user after role assignment
//             logger.info("Admin user created with username: admin and password: admin123");
//         } else {
//             logger.info("Admin user already exists.");
//         }
//     }
// }
