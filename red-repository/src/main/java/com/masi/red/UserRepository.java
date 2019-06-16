package com.masi.red;

import com.masi.red.common.RoleName;
import com.masi.red.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRolesNameIn(RoleName roleName);

    boolean existsByUsernameOrEmail(String username, String email);
}
