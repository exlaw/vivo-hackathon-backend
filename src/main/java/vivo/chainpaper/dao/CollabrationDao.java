package vivo.chainpaper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vivo.chainpaper.entity.Collabration;

import java.util.List;

public interface CollabrationDao extends JpaRepository<Collabration, String> {

    @Query(value = "select c.collabrationID from Collabration c where c.toUsername=?1 and c.type=?2")
    List<String> getByRequester(String username, String type);

    @Query(value = "select c.collabrationID from Collabration c where c.fromUsername=?1 and c.type=?2")
    List<String> getByInviter(String username, String type);



}
