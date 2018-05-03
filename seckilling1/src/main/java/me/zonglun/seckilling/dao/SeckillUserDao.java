package me.zonglun.seckilling.dao;

import me.zonglun.seckilling.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-04-29 21:41
 */
@Service
@Mapper
public interface SeckillUserDao {

    /**
     *  得根据用户id得到用户信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    public SeckillUser getById(@Param("id") Long id);

    /**
     * 根据账户信息、更改密码
     * @param toBeUpdate
     */
    @Update("update user set password = #{password} where id = #{id}")
    public void update(SeckillUser toBeUpdate);
}