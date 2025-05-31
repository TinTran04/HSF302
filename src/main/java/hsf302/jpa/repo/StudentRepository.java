package hsf302.jpa.repo;

import hsf302.jpa.pojo.Student;

public interface StudentRepository {
    public void createStudent(Student student);
    public Student getStudentById(Long id);
    public void deleteStudentById(Long id);
    public void updateStudentById(Long id, Student student);
}
