package com.sys.bio.back.services.user;

import com.sys.bio.back.infra.exceptions.UserNotFoundException;
import com.sys.bio.back.models.user.User;
import com.sys.bio.back.models.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User saveUser(User user, Set<UserRole> userRoles) throws Exception;
    public User saveAdmin(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);
    Set<User> getUsers();
    User getUserById(Long userId);
    User getUserByUsername(String username);
    public void deleteUser(Long userId);
    public User updateUser(User user) throws UserNotFoundException;
    void toggleUserStatus(Long userId, boolean newStatus);

    boolean isUsernameUnique(String username);

    public long getTotalUsers();

    /*
    void uploadProfilePicture(Long userId, MultipartFile photo) throws IOException;
    byte[] getProfilePicture(Long userId);

     */

    void uploadPhoto(Long userId, MultipartFile file);




}
