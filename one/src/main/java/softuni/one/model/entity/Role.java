package softuni.one.model.entity;

import softuni.one.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Role extends BaseEntity {
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Column(unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
