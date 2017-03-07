package cmu.heinz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */

@Entity
@Table(name = "OFFICER")
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String firstName;

    private String lastName;

    private String badgeNum;

    private String gender;

    private Integer seniority;

    private String title;

    private String unionId;

    private String recruitId;

    private String contractEmployee;

    private Date promotionDate;

    private Date hireDate;

    private String trainerId;

    private String permissionGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(String recruit) {
        this.recruitId = recruit;
    }

    public String getContractEmployee() {
        return contractEmployee;
    }

    public void setContractEmployee(String contractEmployee) {
        this.contractEmployee = contractEmployee;
    }

    public Date getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(Date promotionDate) {
        this.promotionDate = promotionDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermission_group(String permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public String getBadgeNum() {
        return badgeNum;
    }

    public void setBadgeNum(String badgeNum) {
        this.badgeNum = badgeNum;
    }

    @Override
    public String toString() {
        return "[Officer ID: " + this.id + ", Last Name: " + this.lastName + ", First Name: " + this.firstName
                + ", Gender: " + this.gender + "]";
    }

}

