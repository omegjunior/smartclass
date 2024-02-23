package edu.ifri.smartclass.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifri.smartclass.converter.ClassroomConverter;
import edu.ifri.smartclass.dto.ClassroomDTO;
import edu.ifri.smartclass.model.Classroom;
import edu.ifri.smartclass.payload.ApiResponse;
import edu.ifri.smartclass.payload.request.ClassroomRequest;
import edu.ifri.smartclass.service.ClassroomService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/classroom")
@Validated
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomConverter classroomConverter;

    @PostMapping
    public ResponseEntity<ApiResponse> createClassroom(@Valid @RequestBody ClassroomRequest classroomRequest) {
        ClassroomDTO classroom = classroomConverter.convert(classroomService.CreateClassroom(classroomRequest));
        //Classroom classroom = classroomService.CreateClassroom(classroomRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Classroom created successfully", HttpStatus.CREATED, classroom));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateClassroom(@PathVariable Long id, @RequestBody ClassroomRequest classroomRequest) {
        Classroom classroom = classroomService.updateClassroom(id, classroomRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Classroom updated successfully", HttpStatus.OK, classroomConverter.convert(classroom)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return ResponseEntity.ok(new ApiResponse(true, "Classroom List", HttpStatus.OK, classroomConverter.convert(classrooms)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.getClassroomById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Classroom Details", HttpStatus.OK, classroomConverter.convert(classroom)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok(new ApiResponse(true, "Classroom deleted successfully", HttpStatus.OK));
    }
}
