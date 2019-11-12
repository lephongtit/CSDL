package com.codegym.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String maKhoa;
    private String name;

    @OneToMany(targetEntity = Student.class)
    private List<Student> students;

    public Faculty() {
    }

    public Faculty(String name, List<Student> students , String maKhoa) {
        this.name = name;
        this.maKhoa=maKhoa;
        this.students = students;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
