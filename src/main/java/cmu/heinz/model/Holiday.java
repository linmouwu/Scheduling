package cmu.heinz.model;

import javax.persistence.*;

/**
 * Holiday model.
 * Referring to HOLIDAY table.
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Entity
@Table(name = "HOLIDAY")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

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
