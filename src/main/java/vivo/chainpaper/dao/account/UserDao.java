package vivo.chainpaper.dao.account;

import vivo.chainpaper.entity.Role;
import vivo.chainpaper.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    User findUserByUsername(String username);

    User findUserByUsernameAndIsActive(String username, boolean isActive);

    @Query(value = "select u.realName from User u where u.username=?1")
    String findRealNameByUsername(String username);

    @Query(value = "select u.avatarUrl from User u where u.username=?1")
    String findAvatarUrlByUsername(String username);

    @Query(value = "select u.username from User u where u.number=?1 and u.role=?2")
    String findUsernameByNumberAndRole(String number, Role role);

    List<User> findUsersByRole(Role role);

    List<User> findUsersByRoleAndTimeAfter(Role role, long timeStamp);

    List<User> findUsersByRoleAndTimeBetween(Role role, long start, long end);
}
