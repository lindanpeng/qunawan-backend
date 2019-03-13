package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from user where id=#{id}")
    User findById(Long id);
    @Select("select * from user where email =#{email}")
    User findByEmail(String email);

}
