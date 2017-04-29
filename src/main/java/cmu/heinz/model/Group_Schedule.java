package cmu.heinz.model;

/**
 * Created by hhahann on 4/12/17.
 */
import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "Group_Schedule")
public class Group_Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private Date startTime;
    private Date endTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_ID")
    private Officer adminId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "union_ID")
    private Union unionId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recruit_ID")
    private Recruit recruitId;
    private String shiftType;
    private String schedule_status;
    private int selected_Officer;

    public String getStatus() {
        return schedule_status;
    }

    public void setStatus(String status) {
        this.schedule_status = status;
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

    public Officer getAdminId() {
        return adminId;
    }

    public void setAdminId(Officer adminId) {
        this.adminId = adminId;
    }

    public Union getUnionId() {
        return unionId;
    }

    public void setUnionId(Union unionId) {
        this.unionId = unionId;
    }

    public Recruit getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Recruit recruitId) {
        this.recruitId = recruitId;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public int getSelected_Officer() {
        return selected_Officer;
    }

    public void setSelected_Officer(int selected_Officer) {
        this.selected_Officer = selected_Officer;
    }
}
