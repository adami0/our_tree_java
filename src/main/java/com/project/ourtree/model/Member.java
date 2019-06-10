package com.project.ourtree.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="member", schema="public")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private Date death_date;
    private String birthplace;
    private String death_place;
    private String text;
    private int tree_id;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDeath_date() {
        return death_date;
    }

    public void setDeath_date(Date death_date) {
        this.death_date = death_date;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getDeath_place() {
        return death_place;
    }

    public void setDeath_place(String death_place) {
        this.death_place = death_place;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTree_id() {
        return tree_id;
    }

    public void setTree_id(int tree_id) {
        this.tree_id = tree_id;
    }

    public Member() {
    }

    public Member(String firstname, String lastname, Date birthdate, Date death_date, String birthplace, String death_place, String text, int tree_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.death_date = death_date;
        this.birthplace = birthplace;
        this.death_place = death_place;
        this.text = text;
        this.tree_id = tree_id;
    }
}
