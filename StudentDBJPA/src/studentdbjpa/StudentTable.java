/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdbjpa;

import studentdbjpa.StudentDBJPA;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class StudentTable {
        
    public static void insertStd(Student emp) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateStd(Student std) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, std.getId());
        fromDb.setName(std.getName());
        fromDb.setGpa(std.getGpa());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static Student findStd(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        Student emp = em.find(Student.class, id);
        em.close();
        return emp;
    }
    public static List<Student> findAllStd() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        List<Student> stdList = (List<Student>) em.createNamedQuery("Student.findAll").getResultList();
        em.close();
        return stdList;
    }
    public static List<Student> findStdByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Student.findByName");
        query.setParameter("name", name);
        List<Student> stdList = (List<Student>) query.getResultList();
        em.close();
        return stdList;
    }
    public static void removeStd(Student std) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentDBJPAPU");
        EntityManager em = emf.createEntityManager();
        Student fromDb = em.find(Student.class, std.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
                
    }
    
    
    
}
