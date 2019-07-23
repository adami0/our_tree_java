package com.project.ourtree.controller;

import com.project.ourtree.model.Tree;
import com.project.ourtree.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeController {

    @Autowired
    private TreeRepository treeRepository;

    @GetMapping("/getTree/{id}")
    public Tree getTreeById(@PathVariable int id) {
        return treeRepository.findById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getTreeList/{user_id}")
    public List<Tree> getTreeListByUserId(@PathVariable int user_id) {
        return treeRepository.findAllByUser_Id(user_id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/postTree")
    public String postTree(@RequestBody Tree tree) {
        treeRepository.save(tree);
        return "tree saved";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateTree")
    public String updateTree(@RequestBody Tree tree) {
        treeRepository.save(tree);
        return "tree updated";
    }
}