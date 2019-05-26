package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vivo.chainpaper.entity.Paper;

public interface PaperDao extends JpaRepository<Paper, String> {
    @Query(value = "select p.writer from Paper p where p.id=?1")
    String findUsernameByPaperId(String paperId);
}
