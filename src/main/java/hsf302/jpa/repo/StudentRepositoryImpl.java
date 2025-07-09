package hsf302.jpa.repo;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.dao.StudentDAO;
import jakarta.transaction.Transactional;

public class StudentRepositoryImpl implements StudentRepository {
    private static StudentDAO studentDAO = null ;

    public StudentRepositoryImpl() {
        studentDAO = new StudentDAO();
    }
    @Override
    @Transactional
    public void createStudent(Student student) {
        studentDAO.createStudent(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentDAO.deleteStudentById(id);
    }

    @Override
    @Transactional
    public void updateStudentById(Long id, Student student) {
        studentDAO.updateStudentById(id, student);
    }
}
