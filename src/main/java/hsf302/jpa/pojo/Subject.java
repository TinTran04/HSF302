package hsf302.jpa.pojo;

import jakarta.persistence.*;



@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name", length = 45, nullable = false)
    private String name;

    @Column(name = "subject_code" , length = 10, nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "student_id") // foreign key in 'subjects' table
    private Student students;

    public Subject() {
    }

    public Subject(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public Subject(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Student getStudent() {
        return students;
    }

    public void setStudent(Student student) {
        this.students = student;
    }

}
