package org.example;

import Services.CatService;
import Services.DogService;
import Services.OwnerService;
import Animals.Cats;
import Animals.Dogs;
import Owner.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        CatService catService = new CatService();
        DogService dogService = new DogService();
        OwnerService ownerService = new OwnerService();

        Cats cat1 = new Cats("normal", 21, "Nora");
        Cats cat2 = new Cats("normal", 5, "Mara");
        Dogs dog1 = new Dogs("normal", 1, "Boggy");

        Owner marina = new Owner();
        Owner artem = new Owner();

        catService.getCats();

        artem.setCat(cat1);

        ownerService.add(artem);
        ownerService.add(marina);

        ownerService.fetch(artem.getId());
    }
}