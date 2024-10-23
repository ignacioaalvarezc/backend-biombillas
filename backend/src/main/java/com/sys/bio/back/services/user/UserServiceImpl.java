package com.sys.bio.back.services.user;

import com.sys.bio.back.infra.exceptions.UserFoundException;
import com.sys.bio.back.infra.exceptions.UserNotFoundException;
import com.sys.bio.back.models.user.User;
import com.sys.bio.back.models.user.UserRole;
import com.sys.bio.back.repositories.user.RoleRepository;
import com.sys.bio.back.repositories.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;


    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User saveUser(User user, Set<UserRole> userRoles) throws Exception {
        User userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal != null) {
            System.out.println("El usuario ya existe");
            throw new UserFoundException("El usuario ya esta presente");
        }
        else {
            for(UserRole userRole:userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userLocal = userRepository.save(user);
        }
        return userLocal;
    }

    @Override
    public User saveAdmin(User user, Set<UserRole> userRoles) throws Exception {
        User userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal != null) {
            System.out.println("El usuario ya existe");
            throw new UserFoundException("El usuario ya esta presente");
        }
        else {
            for(UserRole userRole:userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userLocal = userRepository.save(user);
        }
        return userLocal;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Set<User> getUsers() {
        return new LinkedHashSet<>(userRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public User updateUser(User user) throws UserNotFoundException {
        try {
            User userLocal = userRepository.findById(user.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            userLocal.setName(user.getName());
            userLocal.setUsername(user.getUsername());
            userLocal.setEmail(user.getEmail());
            userLocal.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            userLocal.setUserRoles(user.getUserRoles());
            System.out.println("Usuario actualizado con éxito: " + user.getUserId());
        /*
        userLocal.setProfile("default.png");
         */
            return userRepository.save(userLocal);
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
            throw new UserNotFoundException("User not found");
        }

    }

    @Override
    public void toggleUserStatus(Long userId, boolean newStatus) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setEnabled(newStatus);
            userRepository.save(user);
        }
    }

    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !userRepository.existsByUsername(username);
    }

    /*
    public void uploadProfilePicture(Long userId, MultipartFile photo) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User Not Found"));
        user.setProfilePicture(photo.getBytes());
        userRepository.save(user);
    }

    public byte[] getProfilePicture(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null ? user.getProfilePicture() : null;
    }
     */


    @Override
    public void uploadPhoto(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = Paths.get("src/main/resources/uploads" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error storing file", e);
        }
        user.setPhoto(fileName);
        userRepository.save(user);

    }



}
