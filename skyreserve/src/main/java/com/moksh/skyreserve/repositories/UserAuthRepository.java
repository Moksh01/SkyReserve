package com.moksh.skyreserve.repositories;
import java.util.*;
import com.moksh.skyreserve.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String user_email);
}
