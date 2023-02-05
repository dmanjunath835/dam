package com.damapp.service;

import com.damapp.payloads.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createPost(StudentDto studentDto);

    List<StudentDto> getAll();

    StudentDto findIdStudent(long id);
}
