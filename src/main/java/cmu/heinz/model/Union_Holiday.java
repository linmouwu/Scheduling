package cmu.heinz.model;

import javax.persistence.*;

/**
 * Created by hhahann on 4/29/17.
 */
@Entity
@Table(name = "UNION_HOLIDAY")
public class Union_Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unions_ID")
    Union union;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "holiday_ID")
    Holiday holiday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
    }

    public Holiday getHoliday() {
        return holiday;
    }

    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
    }
}
