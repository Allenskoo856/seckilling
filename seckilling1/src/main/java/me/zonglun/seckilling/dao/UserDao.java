package me.zonglun.seckilling.dao;

import me.zonglun.seckilling.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-04-28 21:36
 */
@Service
@Mapper
public interface UserDao {

    /**
     * 根据用户的id 查找 user
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getById(@Param("id") int id) ;

    @Insert("INSERT INTO user(id,name)VALUES (#{id},#{name})")
    public int insert(User user);
}
