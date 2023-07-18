package Services;

import org.example.Dogs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class DogService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Dogs fetch(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Dogs.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Dogs add(Dogs dog){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dog);
            em.getTransaction().commit(); //
            return dog;
        } catch (RollbackException ex) {
            em.getTransaction().rollback();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

