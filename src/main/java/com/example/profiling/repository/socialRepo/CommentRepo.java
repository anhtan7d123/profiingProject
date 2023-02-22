package com.example.profiling.repository.socialRepo;

import com.example.profiling.entity.socialEntity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Comment findByCommentId(Integer commentId);

    Comment findByCommentName(String commentName);
}
