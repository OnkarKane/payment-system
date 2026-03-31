package com.onkar.payment_system.repository;

import com.onkar.payment_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}