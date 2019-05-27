package vivo.chainpaper.dao.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vivo.chainpaper.entity.Role;
import vivo.chainpaper.entity.account.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    User findUserByUsername(String username);

    @Query(value = "select u.avatarUrl from User u where u.username=?1")
    String findAvatarUrlByUsername(String username);

    List<User> findUsersByRole(Role role);

}
