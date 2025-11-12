// // package com.example.demo.service;

// // import com.example.demo.entity.SuperAdmin;
// // import com.example.demo.repository.SuperAdminRepository;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;

// // import java.util.Optional;

// // @Service
// // public class SuperAdminService {

// //     @Autowired
// //     private SuperAdminRepository repository;

// //     public SuperAdmin createSuperAdmin(String username, String password) {
// //         Optional<SuperAdmin> existing = repository.findByUsername(username);
// //         if (existing.isPresent()) {
// //             throw new RuntimeException("Super Admin username already exists");
// //         }
// //         // In production, store encoded password, e.g., BCrypt
// //         SuperAdmin superAdmin = new SuperAdmin(username, password);
// //         return repository.save(superAdmin);
// //     }
// // }


// package com.example.demo.service;

// import com.example.demo.entity.SuperAdmin;
// import com.example.demo.repository.SuperAdminRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class SuperAdminService {

//     @Autowired
//     private SuperAdminRepository repository;

//     private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//     public SuperAdmin createSuperAdmin(String username, String password) {
//         if (repository.findByUsername(username).isPresent()) {
//             throw new RuntimeException("Super Admin username already exists");
//         }
//         String encodedPassword = passwordEncoder.encode(password);
//         SuperAdmin superAdmin = new SuperAdmin(username, encodedPassword);
//         return repository.save(superAdmin);
//     }

//     public SuperAdmin login(String username, String password) {
//         Optional<SuperAdmin> adminOpt = repository.findByUsername(username);
//         if (adminOpt.isEmpty()) {
//             throw new RuntimeException("Invalid username or password");
//         }
//         SuperAdmin admin = adminOpt.get();
//         if (!passwordEncoder.matches(password, admin.getPassword())) {
//             throw new RuntimeException("Invalid username or password");
//         }
//         return admin;
//     }

//     public void changePassword(Long adminId, String oldPassword, String newPassword) {
//         SuperAdmin admin = repository.findById(adminId).orElseThrow(() -> new RuntimeException("Super Admin not found"));
//         if (!passwordEncoder.matches(oldPassword, admin.getPassword())) {
//             throw new RuntimeException("Old password is incorrect");
//         }
//         admin.setPassword(passwordEncoder.encode(newPassword));
//         repository.save(admin);
//     }
// }


package com.example.demo.service;

import com.example.demo.entity.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperAdminService {

    @Autowired
    private SuperAdminRepository repository;

    public SuperAdmin createSuperAdmin(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Super Admin username already exists");
        }
        // Store password as plain text
        SuperAdmin superAdmin = new SuperAdmin(username, password);
        return repository.save(superAdmin);
    }

    public SuperAdmin login(String username, String password) {
        Optional<SuperAdmin> adminOpt = repository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }
        SuperAdmin admin = adminOpt.get();
        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return admin;
    }

    // public void changePassword(Long adminId, String oldPassword, String newPassword) {
    //     SuperAdmin admin = repository.findById(adminId).orElseThrow(() -> new RuntimeException("Super Admin not found"));
    //     if (!admin.getPassword().equals(oldPassword)) {
    //         throw new RuntimeException("Old password is incorrect");
    //     }
    //     admin.setPassword(newPassword);
    //     repository.save(admin);
    // }


    public void changePassword(String username, String oldPassword, String newPassword) {
    SuperAdmin admin = repository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Super Admin not found"));
    if (!admin.getPassword().equals(oldPassword)) {
        throw new RuntimeException("Old password is incorrect");
    }
    admin.setPassword(newPassword);
    repository.save(admin);
}



}
