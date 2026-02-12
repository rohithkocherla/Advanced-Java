package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "student_age")
    private int age;

    public Student() {}

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters & Setters

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }

    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
