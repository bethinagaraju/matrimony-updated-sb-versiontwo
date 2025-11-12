// package com.example.demo.controller;

// import com.example.demo.entity.SuperAdmin;
// import com.example.demo.service.SuperAdminService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/superadmin")
// public class SuperAdminController {

//     @Autowired
//     private SuperAdminService superAdminService;

//     // DTO for receive input
//     public static class SuperAdminRequest {
//         public String username;
//         public String password;
//     }

//     @PostMapping("/create")
//     public ResponseEntity<?> createSuperAdmin(@RequestBody SuperAdminRequest request) {
//         try {
//             SuperAdmin created = superAdminService.createSuperAdmin(request.username, request.password);
//             return ResponseEntity.ok("Super Admin created with ID: " + created.getId());
//         } catch (RuntimeException ex) {
//             return ResponseEntity.badRequest().body(ex.getMessage());
//         }
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.SuperAdmin;
import com.example.demo.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    public static class SuperAdminRequest {
        public String username;
        public String password;
    }

    public static class LoginRequest {
        public String username;
        public String password;
    }

    // public static class ChangePasswordRequest {
    //     public Long adminId;
    //     public String oldPassword;
    //     public String newPassword;
    // }
    

    public static class ChangePasswordRequest {
    public String username;
    public String oldPassword;
    public String newPassword;
}


    @PostMapping("/create")
    public ResponseEntity<?> createSuperAdmin(@RequestBody SuperAdminRequest request) {
        try {
            SuperAdmin created = superAdminService.createSuperAdmin(request.username, request.password);
            return ResponseEntity.ok("Super Admin created with ID: " + created.getId());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            SuperAdmin admin = superAdminService.login(request.username, request.password);
            return ResponseEntity.ok("Login successful for user: " + admin.getUsername());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }

    // @PostMapping("/change-password")
    // public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
    //     try {
    //         superAdminService.changePassword(request.adminId, request.oldPassword, request.newPassword);
    //         return ResponseEntity.ok("Password changed successfully");
    //     } catch (RuntimeException ex) {
    //         return ResponseEntity.badRequest().body(ex.getMessage());
    //     }
    // }

    @PostMapping("/change-password")
public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
    try {
        superAdminService.changePassword(request.username, request.oldPassword, request.newPassword);
        return ResponseEntity.ok("Password changed successfully");
    } catch (RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}


}
