package com.codegym.service;

import com.codegym.model.Faculty;

public interface FacultyService {
    Iterable<Faculty> findAll();
    Faculty findById(Long id);
    void save(Faculty faculty);
    void remove(Long id);

}
