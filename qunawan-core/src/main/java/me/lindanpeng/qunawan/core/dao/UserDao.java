package me.lindanpeng.qunawan.core.dao;

import me.lindanpeng.qunawan.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    User findById(Long id);
    User findByEmail(String email);

}
