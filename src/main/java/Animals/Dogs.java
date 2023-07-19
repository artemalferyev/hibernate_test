package Animals;

import Services.Animal;
import Owner.Owner;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Dogs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Dogs extends Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String breed;
    private int age;
    private String name;
    public Dogs(){}

    public Dogs(String breed, int age, String name){
        this.breed = breed;
        this.age = age;
        this.name = name;
    }

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

    public void setOwnerList(Set<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public Set<Owner> getOwnerList() {
        return ownerList;
    }

    @ManyToMany
    private Set<Owner> ownerList;
}
