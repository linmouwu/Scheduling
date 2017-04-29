package cmu.heinz.model;

import javax.persistence.*;
import java.sql.Time;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Entity
@Table(name = "SHIFT_TYPE")
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String shiftName;

    Time startTime;

    Time endTime;

    String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "union_id")
    Union union;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
    }
}
