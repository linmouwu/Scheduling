package cmu.heinz.model;

import javax.persistence.*;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Entity
@Table(name = "UNIONS")
public class Union {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Holiday_ID")
    private Holiday holiday;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
