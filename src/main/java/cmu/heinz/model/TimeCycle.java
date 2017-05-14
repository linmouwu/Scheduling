package cmu.heinz.model;

import javax.persistence.*;
import java.util.Date;

/**
 */

@Entity
@Table(name = "TIME_CYCLE")
public class TimeCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date startDate;

    private Date endDate;

    private Boolean activated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
