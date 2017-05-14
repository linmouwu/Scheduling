package cmu.heinz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Officer model.
 * Referring to OFFICER table.
 */

@Entity
@Table(name = "OFFICER")
public class Officer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String uid;

    private String firstName;

    private String lastName;

    private String badgeNum;

    private String gender;

    private Integer seniority;

    private String title;


    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Union_ID")
    private Union union;

    private String recruitId;

    private String contractEmployee;

    private Date promotionDate;

    private Date hireDate;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Trainer_ID")
    private Officer trainer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Permission_Group")
    private PermissionGroup permissionGroup;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
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

    public Officer getTrainer() {
        return trainer;
    }

    public void setTrainer(Officer trainer) {
        this.trainer = trainer;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
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

