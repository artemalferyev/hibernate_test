package Services;

import net.bytebuddy.description.type.TypeDescription;
import org.example.Dogs;
import org.example.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class OwnerService {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Owner fetch(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Owner.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Owner add(Owner owner){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(owner);
            em.getTransaction().commit(); //
            return owner;
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
