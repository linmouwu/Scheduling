package cmu.heinz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Nan
 * @AndrewID nxia
 */

@Entity
@Table(name = "Event_Request")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnionId() {
        return unionId;
    }

    public void setUnionId(int unionId) {
        this.unionId = unionId;
    }

    public int getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(int recruitId) {
        this.recruitId = recruitId;
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

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventRange() {
        return eventRange;
    }

    public void setEventRange(String eventRange) {
        this.eventRange = eventRange;
    }

    private int officerId;
    private int unionId;
    private int recruitId;
    private Date startTime;
    private Date endTime;
    private String eventStatus;
    private String eventType;
    private String description;
    private String eventRange;
    private String uid;
    public Event() {

    }
}

