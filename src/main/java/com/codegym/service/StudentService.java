package com.codegym.service;

import com.codegym.model.Faculty;
import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Date;

public interface StudentService {
    Page<Student> findAll(Pageable pageable);
    Student findById(Long id);
    void save(StudentForm studentForm);
    void remove(Long id);
    Student saveFileStudent(StudentForm studentForm);
    Page<Student> findAllByNameContaining(String firstName, Pageable pageable);
    Iterable<Student> findAllByFacultyContaining(Faculty faculty);
    boolean checkMaSV( String maSV);
    boolean checkDate(LocalDate date );
}
