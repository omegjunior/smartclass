package edu.ifri.smartclass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifri.smartclass.model.Classroom;
import edu.ifri.smartclass.model.User;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByIdAndIsDeletedIsFalse(Long id);
    List<Classroom> findAllByIsDeletedIsFalse();
    List<Classroom> findAllByUserAndIsDeletedIsFalse(User user);
}
