package org.example;

import Services.Animal;
import Services.DogService;

import javax.persistence.*;

@Entity
@Table(name = "Dogs")
public class Dogs extends Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String breed;
    private int age;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public Integer getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    @OneToOne
    private Owner owner;
}
