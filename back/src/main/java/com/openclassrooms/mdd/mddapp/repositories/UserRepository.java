package com.openclassrooms.mdd.mddapp.repositories;


import com.openclassrooms.mdd.mddapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where email = :u or username = :u")
    Optional<User> findByEmailOrUsername(@Param("u") String email);
    Optional<User> findById(Long id);
}
