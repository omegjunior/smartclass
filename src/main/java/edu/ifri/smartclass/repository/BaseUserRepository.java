package edu.ifri.smartclass.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import edu.ifri.smartclass.model.User;

@NoRepositoryBean
public interface BaseUserRepository<T extends User> extends JpaRepository<T, UUID> {
    Optional<T> findByUserNameOrEmailAndIsDeletedIsFalse(String username, String email);
    Optional<T> findByIdAndIsDeletedIsFalse(UUID id);
    List<T> findAllByIsDeletedIsFalse();
    Optional<T> findByEmailAndIsDeletedIsFalse(String email);
    Boolean existsByUserNameOrEmail(String username, String email);
}
