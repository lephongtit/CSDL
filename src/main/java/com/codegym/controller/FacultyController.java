package com.codegym.controller;

import com.codegym.model.Faculty;
import com.codegym.model.Student;
import com.codegym.service.FacultyService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/facultys")
    public ModelAndView listFaculty(){
        Iterable<Faculty> facultys = facultyService.findAll();
        ModelAndView modelAndView = new ModelAndView("/faculty/list");
        modelAndView.addObject("facultys",facultys);
        return modelAndView;
    }

    @GetMapping("/create-faculty")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/faculty/create");
        modelAndView.addObject("faculty",new Faculty());
        return modelAndView;
    }

    @PostMapping("/save-faculty")
    private ModelAndView saveFaculty(@ModelAttribute("faculty") Faculty faculty){

        facultyService.save(faculty);
        ModelAndView modelAndView = new ModelAndView("/faculty/create");
        modelAndView.addObject("faculty", new Faculty());
        modelAndView.addObject("message","New faculty  created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-faculty/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Faculty faculty = facultyService.findById(id);
        if (faculty != null){
            ModelAndView modelAndView = new ModelAndView("/faculty/edit");
            modelAndView.addObject("faculty", faculty);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/eror:404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-faculty")
    public ModelAndView updateDepartment(@ModelAttribute("faculty") Faculty faculty){
        facultyService.save(faculty);
        ModelAndView modelAndView = new ModelAndView("/faculty/edit");
        modelAndView.addObject("faculty", faculty);
        modelAndView.addObject("message","Faculty updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-faculty/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Faculty faculty = facultyService.findById(id);
        if (faculty != null){
            ModelAndView modelAndView = new ModelAndView("/faculty/delete");
            modelAndView.addObject("faculty", faculty);
            List<Student> students = (List<Student>) studentService.findAllByFacultyContaining(faculty);
            modelAndView.addObject("students",students);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-faculty")
    public String deleteDepartment(@ModelAttribute("faculty") Faculty faculty){
        facultyService.remove(faculty.getId());
        return "redirect:/facultys";
    }



}
