package Owner;

import Animals.Cats;

import javax.persistence.*;

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    public Owner(){}

    public Owner(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    @OneToOne(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "owner"
    )
    private Cats cat;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCat(Cats cat) {
        this.cat = cat;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cats getCat() {
        return cat;
    }
}

