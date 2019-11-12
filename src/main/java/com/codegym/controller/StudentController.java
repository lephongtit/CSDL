package com.codegym.controller;


import com.codegym.model.Faculty;
import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import com.codegym.service.FacultyService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private FacultyService facultyService;

    @ModelAttribute("facultys")
    public Iterable<Faculty> facultys() {
        return facultyService.findAll();
    }

    @GetMapping("/student")
    public ModelAndView listStudent(@RequestParam("s") Optional<String> s, @PageableDefault(value = 10) Pageable pageable) {
        Page<Student> students;
        if (s.isPresent()) {
            students = studentService.findAllByNameContaining(s.get(), pageable);
        } else {
            students = studentService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("studentForm", new StudentForm());
        return modelAndView;
    }

    @PostMapping("/save-student")
    public ModelAndView saveEmployee(@ModelAttribute StudentForm studentForm ) {
        if (studentService.checkMaSV(studentForm.getMaSV())){
            if (!studentService.checkDate(studentForm.getBirthDate())){
                String message = "New Student created is not successfully";
                studentService.save(studentForm);
                message = "New Student created successfully";
                ModelAndView modelAndView = new ModelAndView("/student/create");
                modelAndView.addObject("studentForm", new StudentForm());
                modelAndView.addObject("message", message);
            }

        }
        return new ModelAndView("/student/error404");
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public String updateEmployee(@ModelAttribute("student") StudentForm studentForm, BindingResult result) {
        if (result.hasFieldErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        studentService.save(studentForm);

        return "redirect:/student";
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", student);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteEmployee(@ModelAttribute("student") StudentForm studentForm) {
        studentService.remove(studentForm.getId());
        return "redirect:/student";
    }

}
