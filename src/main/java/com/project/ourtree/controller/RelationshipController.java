package com.project.ourtree.controller;

import com.project.ourtree.model.Relationship;
import com.project.ourtree.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RelationshipController {

    @Autowired
    private RelationshipRepository relationshipRepository;

    @GetMapping("/getRelationshipList/{member1_id}")
    public List<Relationship> getRelationshipListByMemberId(@PathVariable int member1_id) {
        return relationshipRepository.findRelationshipListByMemberId(member1_id);
    }

    @PostMapping("/postRelationship")
    public String postRelationship(@RequestBody Relationship relationship){
        relationshipRepository.save(relationship);
        return "relationship saved";
    }
}
