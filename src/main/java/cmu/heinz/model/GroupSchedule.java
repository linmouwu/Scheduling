package cmu.heinz.model;

/**
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Group_Schedule")
public class GroupSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private Date startTime;
    private Date endTime;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "admin_ID")
    private Officer admin;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "union_ID")
    private Union union;

    private int recruitId;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="shift_id")
    private ShiftType shiftType;
    private String scheduleStatus;
    private int selectedOfficer;

    public GroupSchedule() {
    }

    public GroupSchedule(int recruit, String d, Date s, Date e, Officer admin, Union uni, ShiftType shift, String status, int selected) {
        this.setDescription(d);
        this.setRecruitId(recruit);
        this.setSelectedOfficer(selected);
        this.setStartTime(s);
        this.setEndTime(e);
        this.setAdmin(admin);
        this.setUnion(uni);
        this.setShiftType(shift);
        this.setScheduleStatus(status);
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String status) {
        this.scheduleStatus = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ShiftType getShiftType() {
        return shiftType;
    }

    public void setShiftType(ShiftType shiftType) {
        this.shiftType = shiftType;
    }

    public int getSelectedOfficer() {
        return selectedOfficer;
    }

    public void setSelectedOfficer(int selectedOfficer) {
        this.selectedOfficer = selectedOfficer;
    }

    public Officer getAdmin() {
        return admin;
    }

    public void setAdmin(Officer admin) {
        this.admin = admin;
    }

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
    }

    public int getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(int recruit) {
        this.recruitId = recruit;
    }
}
