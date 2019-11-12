package com.codegym.service.impl;

import com.codegym.model.Faculty;
import com.codegym.model.Student;
import com.codegym.model.StudentForm;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Autowired
    Environment env;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void save(StudentForm studentForm) {
        Student student = saveFileStudent(studentForm);
        studentRepository.save(student);
    }

    @Override
    public void remove(Long id) {
        studentRepository.delete(id);
    }

    @Override
    public Student saveFileStudent(StudentForm studentForm) {
        // lay ten file
        MultipartFile multipartFile = studentForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();


        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(studentForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (fileName.equals("") && studentForm.getId() != null) {
            Student student = findById(studentForm.getId());
            fileName = student.getAvatar();
        }

        // tao doi tuong de luu vao db
        if (studentForm.getId() == null) {
            return new Student(studentForm.getName(), studentForm.getBirthDate(), studentForm.getMaSV(), fileName, studentForm.getGender(), studentForm.getFaculty());
        } else {
            return new Student(studentForm.getId(), studentForm.getName(), studentForm.getBirthDate(), studentForm.getMaSV(), fileName, studentForm.getGender(), studentForm.getFaculty());
        }
    }

    @Override
    public Page<Student> findAllByNameContaining(String firstName, Pageable pageable) {
        return studentRepository.findAllByNameContaining(firstName, pageable);
    }

    @Override
    public Iterable<Student> findAllByFacultyContaining(Faculty faculty) {
        return studentRepository.findAllByFaculty(faculty);
    }

    @Override
    public boolean checkMaSV(String maSV) {
        List<Student> students = (List)studentRepository.findAll();
        for(int i =0;i<students.size();i++){
            if(students.get(i).getMaSV().equals(maSV)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkDate(LocalDate date) {
        long ageInMillis=new Date().getYear()- date.getYear();
        Date age=new Date(ageInMillis);
        if (age.getTime()>=18){
            return true;
        }else{
            return false;
        }
    }

}
