package hsf302.jpa.service;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;
import hsf302.jpa.repo.SubjectRepository;
import hsf302.jpa.repo.SubjectRepositoryImpl;
import jakarta.transaction.Transactional;

import java.util.List;

public class SubjectServiceImpl implements  SubjectService {
    private SubjectRepository subjectRepository;

    public SubjectServiceImpl() {
        subjectRepository = new SubjectRepositoryImpl();
    }
    @Override
    @Transactional
    public void createSubject(Subject subject) {
        subjectRepository.createSubject(subject);
    }

    @Override
    @Transactional
    public void deleteSubject(Subject subject) {
        subjectRepository.deleteSubject(subject);
    }


    @Override
    @Transactional
    public void updateSubject(Subject subject) {
        subjectRepository.updateSubject(subject);
    }


    public List<Subject> getSubjectsByStudentId(Long id) {
        return subjectRepository.getSubjectsByStudentId(id);
    }
}
