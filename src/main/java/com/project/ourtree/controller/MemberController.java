package com.project.ourtree.controller;

import com.project.ourtree.model.Member;
import com.project.ourtree.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/getMemberList/{tree_id}")
    public List<Member> getMemberListByTreeId(@PathVariable int tree_id) {
        return memberRepository.findAllByTree_id(tree_id);
    }

    @PostMapping("/postMember")
    public String postMember(@RequestBody Member member) {
        memberRepository.save(member);
        return "member saved";
    }

    @PutMapping("/updateMember")
    public String updateMember(@RequestBody Member member) {
        memberRepository.save(member);
        return "member updated";
    }

}
