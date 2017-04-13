package cmu.heinz.model;

import javax.persistence.*;

/**
 * Created by hhahann on 4/12/17.
 */
@Entity
@Table(name = "Recruit")
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
