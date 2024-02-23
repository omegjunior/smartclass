package edu.ifri.smartclass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifri.smartclass.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
