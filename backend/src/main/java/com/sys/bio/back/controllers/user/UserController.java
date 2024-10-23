package com.sys.bio.back.controllers.user;

import com.sys.bio.back.models.user.Role;
import com.sys.bio.back.models.user.User;
import com.sys.bio.back.models.user.UserRole;
import com.sys.bio.back.repositories.user.UserRepository;
import com.sys.bio.back.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;



    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception {
        /*user.setProfilePicture(user.getProfilePicture());*/
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(2L);
        role.setName("USER");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoles.add(userRole);
        return userService.saveUser(user, userRoles);
    }



    @PostMapping("/save-admin")
    public User saveAdmin(@RequestBody User user) throws Exception {
/*
        user.setProfile("default.png");*/
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> userRoles = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setName("ADMIN");
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoles.add(userRole);

        return userService.saveUser(user, userRoles);
    }


    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUser(username);
    }


    @GetMapping("/id/{userId}")
    @RequestMapping(method = RequestMethod.GET, path = "/id/{userId}")
    public User listUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/")
    public ResponseEntity<?> listUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/current-user")
    public User getCurrentUser() {
        return new User();
    }


    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,
                                           @Valid @RequestBody User user) {
        try {
            user.setUserId(userId);
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            log.error("Error al actualizar el usuario con ID: " + userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/toggle-status/{userId}")
    public ResponseEntity<Map<String, String>> toggleUserStatus(@PathVariable("userId") Long userId,
                                                                @RequestBody Map<String, Boolean> statusMap) {
        boolean newStatus = statusMap.get("enabled");
        userService.toggleUserStatus(userId, newStatus);
        Map<String, String> response = new HashMap<>();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/count-users")
    public long getTotalUsers() {
        return userService.getTotalUsers();
    }

    /*
    @GetMapping("/validate-username")
    public ResponseEntity<?> validateUsername(@RequestBody String username) {
        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ya está en uso.");
        }
        return ResponseEntity.ok().body("El nombre de usuario es único");
    }


    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long userId) {
        byte[] photo = userService.getProfilePicture(userId);
        if (photo != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
     */

    @PostMapping("/{userId}/photo")
    public ResponseEntity<?> uploadPhoto(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            userService.uploadPhoto(userId, file);
            return ResponseEntity.ok().body("Photo uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading photo");
        }
    }

}


