package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vivo.chainpaper.entity.Comment;
import vivo.chainpaper.entity.Star;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, String> {
    List<Comment> findCommentsByPaperId(String paperId);
}
