package com.openclassrooms.mdd.mddapp.repositories;


import com.openclassrooms.mdd.mddapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where email = :email or username = :username")
    Optional<User> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
