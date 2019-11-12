package com.codegym.service.impl;

import com.codegym.model.Faculty;
import com.codegym.model.Student;
import com.codegym.repository.FacultyRepository;
import com.codegym.repository.StudentRepository;
import com.codegym.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Iterable<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.findOne(id);
    }

    @Override
    public void save(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        Faculty faculty = findById(id);
        List<Student> students = (List<Student>) studentRepository.findAllByFaculty(faculty);
        for (int i = 0; i< students.size(); i++){
            studentRepository.delete(students.get(i));
        }
        facultyRepository.delete(id);
    }


}
