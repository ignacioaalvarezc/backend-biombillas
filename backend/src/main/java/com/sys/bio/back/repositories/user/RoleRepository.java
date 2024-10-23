package com.sys.bio.back.repositories.user;

import com.sys.bio.back.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
