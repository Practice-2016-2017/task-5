package com.exigen.timetable.pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
