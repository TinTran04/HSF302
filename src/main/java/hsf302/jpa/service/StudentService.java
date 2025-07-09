package hsf302.jpa.service;

import hsf302.jpa.pojo.Student;

public interface StudentService {
    public void createStudent(Student student);
    public Student getStudentById(Long id);
    public void deleteStudentById(Long id);
    public void updateStudentById(Long id, Student student);
}
