package edu.ifri.smartclass.repository;

import org.springframework.stereotype.Repository;

import edu.ifri.smartclass.model.User;

@Repository
public interface UserRepository extends BaseUserRepository<User> {
}
