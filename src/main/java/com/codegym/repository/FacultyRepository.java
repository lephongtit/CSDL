package com.codegym.repository;

import com.codegym.model.Faculty;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long> {
}
