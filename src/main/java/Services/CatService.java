package Services;

import org.example.Cats;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class CatService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Cats fetch(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cats.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Cats add(Cats cat){

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cat);
            em.getTransaction().commit(); //
            return cat;
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
