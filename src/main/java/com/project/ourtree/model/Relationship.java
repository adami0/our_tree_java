package com.project.ourtree.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int member1_id;
    private int member2_id;
    private int relationship_type_code;
    private int member1_role_code;
    private int member2_role_code;

    public int getId() {
        return id;
    }

    public int getMember1_id() {
        return member1_id;
    }

    public int getMember2_id() {
        return member2_id;
    }

    public int getRelationship_type_code() {
        return relationship_type_code;
    }

    public int getMember1_role_code() {
        return member1_role_code;
    }

    public int getMember2_role_code() {
        return member2_role_code;
    }

    public Relationship() {
    }

    public Relationship(int member1_id, int member2_id, int relationship_type_code, int member1_role_code, int member2_role_code) {
        this.member1_id = member1_id;
        this.member2_id = member2_id;
        this.relationship_type_code = relationship_type_code;
        this.member1_role_code = member1_role_code;
        this.member2_role_code = member2_role_code;
    }
}
