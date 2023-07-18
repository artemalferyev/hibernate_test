package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Owner")
public class Owner {

    @Id
    private Integer id;
    private String name;

    @OneToOne(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "Owner"
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

