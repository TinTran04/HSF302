package hsf302.jpa.service;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.repo.StudentRepository;
import hsf302.jpa.repo.StudentRepositoryImpl;
import jakarta.transaction.Transactional;

public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    public StudentServiceImpl() {
        studentRepository = new StudentRepositoryImpl();
    }

    @Override
    @Transactional
    public void createStudent(Student student) {
        studentRepository.createStudent(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentById(id);
    }

    @Override
    @Transactional
    public void updateStudentById(Long id, Student student) {
        studentRepository.updateStudentById(id, student);
    }
}
