package com.BankingWebApp1.dao;

import com.BankingWebApp1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userEmail = :userEmail")
    public User getUserByUsername(@Param("userEmail") String userEmail);

    @Query("select u from User u where u.userId = :userId")
    public Optional<User> getUserById(@Param("userId") long userId);

    @Query("select u from User u where u.userEmail = :userEmail")
    public Optional<User> getUserByEmail(@Param("userEmail") String userEmail);

    @Query("SELECT new com.BankingWebApp1.entities.User(u.userEmail, u.firstName, u.lastName, u.phoneNumber, u.address) FROM User u WHERE u.userId = ?1")
    public Optional<User> findByIdForAdmin(@Param("userId") long userId);


}
