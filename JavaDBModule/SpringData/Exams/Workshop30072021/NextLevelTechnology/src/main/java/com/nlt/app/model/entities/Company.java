package com.nlt.app.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name="companies")
public class Company extends BaseEntity{

    @Column(name="name", nullable = false)
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
