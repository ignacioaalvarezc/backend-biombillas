package com.sys.bio.back.repositories.user;

import com.sys.bio.back.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    boolean existsByUsername(String username);

    long count();
}
