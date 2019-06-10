package com.project.ourtree.repository;

import com.project.ourtree.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query(value="SELECT m.* FROM member m WHERE m.tree_id =:tree_id", nativeQuery = true)
    List<Member> findAllByTree_id(@Param("tree_id") int tree_id);



}


