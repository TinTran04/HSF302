package hsf302.jpa.dao;

import hsf302.jpa.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDAO {
    private static String jpaName = "studentPU";

    public void createStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {


            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            em.close();
            emf.close();
        }

    }

    public Student getStudentById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        Student student = null;
        try {
            student = em.find(Student.class, id);
        } catch (Exception ex) {
            System.out.println("Error retrieving student by ID: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
        return student;
    }

    public void deleteStudentById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error deleting student by ID: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public void updateStudentById(Long id, Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(jpaName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student existingStudent = em.find(Student.class, id);
            if (existingStudent != null) {
                existingStudent.setName(student.getName());
                existingStudent.setBirthYear(student.getBirthYear());
                existingStudent.setEmail(student.getEmail());
                existingStudent.setPassword(student.getPassword());
                em.merge(existingStudent);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error updating student by ID: " + ex.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

}
