package hsf302.jpa.repo;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;

import java.util.List;

public interface SubjectRepository {
    public void createSubject(Subject subject);
    public void deleteSubject(Subject subject);
    public void updateSubject(Subject subject);


    List<Subject> getSubjectsByStudentId(Long id);
}
