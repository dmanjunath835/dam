package com.damapp.controller;

import com.damapp.payloads.StudentDto;
import com.damapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

@Autowired
    private StudentService studentService;

@PostMapping
public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
    StudentDto d = studentService.createPost(studentDto);
    return new ResponseEntity<>(d, HttpStatus.CREATED);

}
@GetMapping
    public List<StudentDto>getAll(){
    return studentService.getAll();
}
@GetMapping("/{id}")
    public ResponseEntity<StudentDto>getIdStudent(@PathVariable("id")long id){
    StudentDto idStudent = studentService.findIdStudent(id);
    return new ResponseEntity<>(idStudent,HttpStatus.OK);
}


}
