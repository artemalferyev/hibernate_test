package Services;

import Animals.Cats;
import Animals.Dogs;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CatService {

    private EntityManager em;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Cats fetch(Integer id) {
        em = emf.createEntityManager();
        try {
            return em.find(Cats.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Cats add(Cats cat){

        em = emf.createEntityManager();
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
    public List<Cats> getCats(){
        em = emf.createEntityManager();
        try {
            TypedQuery<Cats> catsQuery = em.createQuery("SELECT cats FROM Cats cats", Cats.class);
            return catsQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public Cats findByName(String name){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cats> query = builder.createQuery(Cats.class);
        Root<Cats> root = query.from(Cats.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        return em.createQuery(query).getSingleResult();
        }

    public List<Cats> findAgeGreaterThan(int age){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cats> query = builder.createQuery(Cats.class);
        Root<Cats> root = query.from(Cats.class);
        query.select(root).where(builder.equal(root.get("age"), age));
        return em.createQuery(query).getResultList();
    }

    public List<Cats> findByBreed(String breed){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Cats> query = builder.createQuery(Cats.class);
        Root<Cats> root = query.from(Cats.class);
        query.select(root).where(builder.like(root.get("breed"), breed + "%"));
        return em.createQuery(query).getResultList();
    }
}
