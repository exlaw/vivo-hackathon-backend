package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.entity.Star;

import java.util.List;

public interface StarDao extends JpaRepository<Star, String> {
    List<Star> findStarsByPaperId(String paperId);
}
