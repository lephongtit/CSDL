package com.codegym.formatter;

import com.codegym.model.Faculty;
import com.codegym.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class LongToFacultyFormatter implements Formatter<Faculty> {
    private FacultyService facultyService;
    @Autowired
    public LongToFacultyFormatter(FacultyService facultyService){
        this.facultyService = facultyService;
    }

    @Override
    public Faculty parse(String text, Locale locale) throws ParseException {
        return facultyService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Faculty object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
