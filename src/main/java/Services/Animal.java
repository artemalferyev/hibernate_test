package Services;

import jdk.jfr.Percentage;

import javax.persistence.*;

@MappedSuperclass
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int age;
    private String name;
    private String breed;
}
