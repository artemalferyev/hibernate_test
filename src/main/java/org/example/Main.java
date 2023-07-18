package org.example;

import Services.CatService;
import Services.DogService;
import Services.OwnerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        CatService catService = new CatService();
        DogService dogService = new DogService();
        OwnerService ownerService = new OwnerService();

        Cats nora= new Cats();
        Cats mara = new Cats();
        Dogs boggy = new Dogs();
        Owner artem = new Owner();

        dogService.add(boggy);
        catService.add(nora);
        catService.add(mara);
        ownerService.add(artem);

        catService.fetch(1);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

        EntityManager em = emf.createEntityManager();

        System.out.println(em.createNativeQuery("SELECT 1 + 1").getSingleResult());

        em.close();
        emf.close();
    }
}