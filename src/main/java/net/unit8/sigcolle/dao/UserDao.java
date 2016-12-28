package net.unit8.sigcolle.dao;

import net.unit8.sigcolle.DomaConfig;
import net.unit8.sigcolle.model.User;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import java.util.List;

/**
 * @auther takahashi
 */
@Dao(config = DomaConfig.class)
public interface UserDao {
    @Select
    List<User> selectAll();

    @Insert
    int insert(User user);

    @Select
    int selectByUserId(Long userId);

    @Select
    int countByEmail(String email);

    @Select(ensureResult = true)
    User selectByEmail(String email);
}
