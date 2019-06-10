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

    @GetMapping("/getTreeList/{user_id}")
    public List<Tree> getTreeListByUserId(@PathVariable int user_id) {
        return treeRepository.findAllByUser_Id(user_id);
    }

    @PostMapping("/postTree")
    public String postTree(@RequestBody Tree tree) {
        treeRepository.save(tree);
        return "tree saved";
    }

    @PutMapping("/updateTree")
    public String updateTree(@RequestBody Tree tree) {
        treeRepository.save(tree);
        return "tree updated";
    }
}
