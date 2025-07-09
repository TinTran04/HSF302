package hsf302.jpa.service;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;

public interface SubjectService {
    public void createSubject(Subject subject);
    public void deleteSubject(Subject subject);
    public void updateSubject(Subject subject);
}
