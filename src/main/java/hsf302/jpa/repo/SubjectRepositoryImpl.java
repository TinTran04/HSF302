package hsf302.jpa.repo;

import hsf302.jpa.dao.SubjectDAO;
import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;
import jakarta.transaction.Transactional;

import java.util.List;

public class SubjectRepositoryImpl implements  SubjectRepository {
    private static SubjectDAO subjectDAO = null;

    public SubjectRepositoryImpl() {
        subjectDAO = new SubjectDAO();
    }

    @Override
    @Transactional
    public void createSubject(Subject subject) {
        subjectDAO.createSubject(subject);
    }

    @Override
    @Transactional
    public void deleteSubject(Subject subject) {
        subjectDAO.deleteSubject(subject);
    }

    @Override
    @Transactional
    public void updateSubject(Subject subject) {
        subjectDAO.updateSubject(subject);
    }

    @Override
    public List<Subject> getSubjectsByStudentId(Long id) {
        return subjectDAO.getSubjectsByStudentId(id);
    }
}
