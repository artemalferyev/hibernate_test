package Services;

import Animals.Dogs;
import Owner.Owner;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OwnerService {

    private EntityManager em;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Owner fetch(Integer id) {
        em = emf.createEntityManager();
        try {
            return em.find(Owner.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Owner add(Owner owner){
        em = emf.createEntityManager();
        try{
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

    public List<Owner> getOwners(){
        em = emf.createEntityManager();

        try {
            TypedQuery<Owner> ownerQuery = em.createQuery("SELECT owner FROM Owner owner", Owner.class);
            return ownerQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public Owner findByName(String name){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Owner> query = builder.createQuery(Owner.class);
        Root<Owner> root = query.from(Owner.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        return em.createQuery(query).getSingleResult();
    }

}
