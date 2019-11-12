package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class StudentForm {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String maSV;
    private MultipartFile avatar;
    private String gender;
    private Faculty faculty;

    public StudentForm() {
    }

    public StudentForm(String name, LocalDate birthDate, String maSV, MultipartFile avatar, String gender, Faculty faculty) {
        this.name = name;
        this.birthDate = birthDate;
        this.maSV = maSV;
        this.avatar = avatar;
        this.gender = gender;
        this.faculty = faculty;
    }

    public StudentForm(Long id, String name, LocalDate birthDate, String maSV, MultipartFile avatar, String gender, Faculty faculty) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.maSV = maSV;
        this.avatar = avatar;
        this.gender = gender;
        this.faculty = faculty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }



    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
