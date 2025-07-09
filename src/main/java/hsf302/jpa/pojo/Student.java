package hsf302.jpa.pojo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "birth_year", nullable = false)
    private int birthYear;
    @Column(name = "email", length = 40, nullable = false, unique = true)
    private String email;
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id") // foreign key in 'subjects' table
    //orphanRemoval xoa ben bang nay se xoa luon bang ben kia
    private List<Subject> subjects;

    public Student() {
        subjects = new ArrayList<Subject>();
    }

    public Student(Long id, String name, int birthYear, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.email = email;
        this.password = password;
        this.subjects = new ArrayList<>();
    }

    public Student(String name, int birthYear, String email, String password) {
        this.name = name;
        this.birthYear = birthYear;
        this.email = email;
        this.password = password;
        this.subjects = new ArrayList<>();
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  List<Subject> getSubjects() {
        return subjects;
    }
}