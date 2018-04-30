package me.zonglun.seckilling.dao;

import me.zonglun.seckilling.domain.SeckillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author : Administrator
 * @create 2018-04-29 21:41
 */
@Service
@Mapper
public interface SeckillUserDao {

    @Select("SELECT * FROM user WHERE id = #{id}")
    public SeckillUser getById(@Param("id") Long id);
}