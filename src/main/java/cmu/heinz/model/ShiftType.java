package cmu.heinz.model;

import javax.persistence.*;
import java.sql.Time;

/**
 */

@Entity
@Table(name = "SHIFT_TYPE")
public class ShiftType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String shiftName;

    private Time startTime;

    private Time endTime;

    private String description;

    private boolean activated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "union_id")
    private Union union;

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


    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "ShiftType{" +
                "id=" + id +
                ", shiftName='" + shiftName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                ", union=" + union +
                '}';
    }

}
