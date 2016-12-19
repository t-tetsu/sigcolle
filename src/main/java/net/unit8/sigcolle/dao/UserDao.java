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
    @Select(ensureResult = true)
    User selectById(Long userId);

    @Select
    List<User> selectAll();

    @Insert
    int insert(User user);

    @Select
    int countByUserId(Long userId);
}
