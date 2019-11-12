package com.codegym.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String maSV;
    private LocalDate birthDate;
    private String name;
    private String avatar;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    public Student() {
    }

    public Student(String name, LocalDate birthDate, String maSV, String avatar, String gender, Faculty faculty) {
        this.name = name;
        this.birthDate = birthDate;
        this.maSV = maSV;
        this.avatar = avatar;
        this.gender = gender;
        this.faculty = faculty;
    }

    public Student(Long id, String name, LocalDate birthDate, String maSV, String avatar, String gender, Faculty faculty) {
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


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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
    public void setStudent(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.birthDate = student.getBirthDate();
        this.maSV = student.getMaSV();
        this.avatar = student.getAvatar();
        this.gender = student.getGender();
        this.faculty = student.getFaculty();
    }
}
