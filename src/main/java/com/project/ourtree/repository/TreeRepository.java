package com.project.ourtree.repository;

import com.project.ourtree.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TreeRepository extends JpaRepository<Tree, Integer> {

    Tree findById(int id);

    @Query(value = "SELECT t.* FROM tree t WHERE t.user_id=:user_id", nativeQuery = true)
    List<Tree> findAllByUser_Id(@Param("user_id") int user_id);
}
