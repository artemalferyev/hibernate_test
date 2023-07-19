package Services;

import Animals.Cats;
import Animals.Dogs;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DogService {

    private EntityManager em;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public Dogs fetch(Integer id) {
        em = emf.createEntityManager();
        try {
            return em.find(Dogs.class, id);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public Dogs add(Dogs dog){
        em = emf.createEntityManager();
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

    public List<Dogs> getDogs(){
        em = emf.createEntityManager();

        try {
            TypedQuery<Dogs> dogsQuery = em.createQuery("SELECT dogs FROM Dogs dogs", Dogs.class);
            return dogsQuery.getResultList();
        } finally {
            em.close();
        }
    }

    public Dogs findByName(String name){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dogs> query = builder.createQuery(Dogs.class);
        Root<Dogs> root = query.from(Dogs.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        return em.createQuery(query).getSingleResult();
    }

    public List<Dogs> findAgeGreaterThan(int age){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dogs> query = builder.createQuery(Dogs.class);
        Root<Dogs> root = query.from(Dogs.class);
        query.select(root).where(builder.equal(root.get("age"), age));
        return em.createQuery(query).getResultList();
    }

    public List<Dogs> findByBreed(String breed){
        em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Dogs> query = builder.createQuery(Dogs.class);
        Root<Dogs> root = query.from(Dogs.class);
        query.select(root).where(builder.like(root.get("breed"), breed + "%"));
        return em.createQuery(query).getResultList();
    }
}

