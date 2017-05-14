package cmu.heinz.model;

import javax.persistence.*;

/**
 */
@Entity
@Table(name = "Schedule_Officer")
public class ScheduleOfficer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "officer_ID")
    private Officer officer;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "schedule_ID")
    private GroupSchedule groupSchedule;

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

    public GroupSchedule getGroupSchedule() {
        return groupSchedule;
    }

    public void setGroupSchedule(GroupSchedule groupSchedule) {
        this.groupSchedule = groupSchedule;
    }
}
