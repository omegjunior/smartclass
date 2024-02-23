package edu.ifri.smartclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.ifri.smartclass.enums.Role;
import edu.ifri.smartclass.exception.NotFoundException;
import edu.ifri.smartclass.exception.UnauthorizedException;
import edu.ifri.smartclass.model.Classroom;
import edu.ifri.smartclass.model.User;
import edu.ifri.smartclass.payload.request.ClassroomRequest;
import edu.ifri.smartclass.repository.ClassroomRepository;
import edu.ifri.smartclass.repository.UserRepository;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    public ClassroomService(ClassroomRepository classroomRepository, UserRepository userRepository, UserService userService) {
        this.classroomRepository = classroomRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Classroom CreateClassroom(ClassroomRequest classroomRequest) {
        User professor = userRepository.findByIdAndIsDeletedIsFalse(classroomRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("professor not found or deleted"));

       if(!userService.hasRole(professor, Role.PROFESSOR))
           throw new UnauthorizedException("You are not a professor");

        Classroom classroom = new Classroom();
        classroom.setLink(classroomRequest.getLink());
        classroom.setName(classroomRequest.getName());
        classroom.setUser(professor);
        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Long id, ClassroomRequest classroomRequest) {

        Classroom existingClassroom = classroomRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("Classroom not found"));

        User professor = userRepository.findByIdAndIsDeletedIsFalse(classroomRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("professor not found or deleted"));

        if(!userService.hasRole(professor, Role.PROFESSOR))
            throw new UnauthorizedException("You are not a professor");

        existingClassroom.setLink(classroomRequest.getLink());
        existingClassroom.setName(classroomRequest.getName());
        existingClassroom.setUser(professor);
        return classroomRepository.save(existingClassroom);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAllByIsDeletedIsFalse();
    }


    public Classroom getClassroomById(Long id) {
        return classroomRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("Classroom not exist or deleted"));
    }


    public void deleteClassroom(Long id) {
        Classroom existingClassroom = classroomRepository.findByIdAndIsDeletedIsFalse(id)
                .orElseThrow(() -> new NotFoundException("Classroom not found or already deleted"));
        existingClassroom.setDeleted(true);
        classroomRepository.save(existingClassroom);
    }

    public List<Classroom> getClassroomsByUser(User user) {
        return classroomRepository.findAllByUserAndIsDeletedIsFalse(user);
    }

}
