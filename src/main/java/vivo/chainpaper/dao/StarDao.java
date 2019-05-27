package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vivo.chainpaper.entity.Star;

import java.util.List;


public interface StarDao extends JpaRepository<Star, String> {

    @Query(value = "select s.userId from Star s where s.paperId=?1 and s.star=1")
    List<String> findStarByPaperId(String paperId);

    @Query(value = "select s from Star s where s.paperId=?1")
    List<Star>  findStarsByPaperId(String paperId);

    @Query(value = "select s.score from Star s where s.paperId=?1 and s.score<>0")
    List<Integer> findScoreByPaperId(String paperId);

    @Query(value = "select s.score from Star s where s.paperId=?1 and s.userId=?2")
    int check(String paperId, String user);

}
