package com.codegym.repository;

import com.codegym.model.Faculty;
import com.codegym.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    Page<Student> findAllByNameContaining(String firstName, Pageable pageable);
    Iterable<Student> findAllByFaculty(Faculty faculty);
    boolean existsByMaSV(String maSv);

}
