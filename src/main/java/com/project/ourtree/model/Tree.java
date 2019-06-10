package com.project.ourtree.model;

import javax.persistence.*;

@Entity
@Table(name="tree")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    private int user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Tree() {
    }

    public Tree(String name, int user_id) {
        this.name = name;
        this.user_id = user_id;
    }
}
