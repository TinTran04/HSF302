package hsf302.jpa.dao;

import hsf302.jpa.pojo.Student;
import hsf302.jpa.pojo.Subject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class SubjectDAO {
    private static String jpaName = "studentPU";
    public void createSubject(Subject subject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {


            em.getTransaction().begin();
            em.persist(subject);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }
    }

    public void updateSubject(Subject subject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(subject);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error updating subject: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }



    public void deleteSubject(Subject subject) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Subject managedSubject = em.find(Subject.class, subject.getId());
            if (managedSubject != null) {
                if (managedSubject.getStudent() != null) {
                    managedSubject.getStudent().getSubjects().remove(managedSubject);
                }
                managedSubject.setStudent(null);
                em.remove(managedSubject);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error deleting subject: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Subject> getSubjectsByStudentId(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        List<Subject> subjects = null;
        try {
            Student student = em.find(Student.class, id);
            if (student != null) {
                subjects = student.getSubjects();
            }
        } catch (Exception ex) {
            System.out.println("Error retrieving subjects by student ID: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return subjects;
    }
}
