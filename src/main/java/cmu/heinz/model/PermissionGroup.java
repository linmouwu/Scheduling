package cmu.heinz.model;

import javax.persistence.*;

/**
 */

@Entity
@Table(name = "PERMISSION_GROUP")
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
