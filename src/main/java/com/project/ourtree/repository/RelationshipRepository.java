package com.project.ourtree.repository;

import com.project.ourtree.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Integer> {

    @Query(value="SELECT r.* FROM relationship r, member m WHERE m.tree_id=:tree_id AND m.id=r.member1_id",nativeQuery = true)
    List<Relationship> findRelationshipListByTree_id(@Param("tree_id") int tree_id);

    @Query(value="SELECT r.* FROM relationship r WHERE r.member1_id=:member1_id",nativeQuery = true)
    List<Relationship> findRelationshipListByMemberId(@Param("member1_id") int member1_id);
}
