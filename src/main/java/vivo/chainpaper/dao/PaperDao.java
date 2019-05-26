package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import vivo.chainpaper.entity.Paper;

public interface PaperDao extends JpaRepository<Paper, String> {

}
