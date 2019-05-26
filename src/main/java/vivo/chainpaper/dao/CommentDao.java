package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vivo.chainpaper.entity.Comment;
import vivo.chainpaper.entity.Star;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, String> {

    @Query(value = "select c from Comment c where c.paperId=?1")
    List<Comment> findCommentsByPaperId(String paperId);

}
