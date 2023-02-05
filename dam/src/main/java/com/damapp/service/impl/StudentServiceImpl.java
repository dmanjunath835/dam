package com.damapp.service.impl;

import com.damapp.Exception.ResourceNotFoundException;
import com.damapp.entites.Student;
import com.damapp.payloads.StudentDto;
import com.damapp.repository.StudentRepository;
import com.damapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public StudentDto createPost(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student students = studentRepository.save(student);
        StudentDto s = mapToDto(students);


        return s;
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> posts = studentRepository.findAll();


        return posts.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
    }

    @Override
    public StudentDto findIdStudent(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("student", "id", id));

        return mapToDto(student);
    }


    private StudentDto mapToDto(Student students) {
        StudentDto studentDto=new StudentDto();
        studentDto.setId(students.getId());
        studentDto.setName(students.getName());
        studentDto.setAge(students.getAge());


        return studentDto;
    }

    private Student mapToEntity(StudentDto studentDto) {
        Student student=new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());

        return student;
    }
}
