package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    @Query("select p from Post p order by p.postTimestamp")
    List<Post> getPostOrderByPostTimestamp();

    Post findByPostName(String postName);

    Post findByPostId(Integer postId);
}
