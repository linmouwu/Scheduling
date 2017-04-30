package cmu.heinz.model;

import javax.persistence.*;

/**
 * Created by hhahann on 4/12/17.
 */
@Entity
@Table(name = "Schedule_Officer")
public class Schedule_Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "officer_ID")
    private Officer officer;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "schedule_ID")
    private Group_Schedule groupSchedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Officer getOfficer() {
        return officer;
    }

    public void setOfficer(Officer officer) {
        this.officer = officer;
    }

    public Group_Schedule getGroupSchedule() {
        return groupSchedule;
    }

    public void setGroupSchedule(Group_Schedule groupSchedule) {
        this.groupSchedule = groupSchedule;
    }
}
