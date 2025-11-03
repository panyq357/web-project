package cn.ac.panlab.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.ac.panlab.backend.model.User;

@Mapper
public interface UserMapper {

    @Select("SELECT username from users")
    List<String> getAllUsernames();

    @Select("SELECT COUNT(*) from users where username = #{username}")
    Integer getCountByUsername(@Param("username") String username);

    @Insert("INSERT INTO users (username, email, password) VALUES (#{username}, #{email}, #{password})")
    void insertUser(User user);

    @Delete("DELETE FROM users WHERE username = #{username}")
    void deleteUser(@Param("username") String username);

    @Select("SELECT password FROM users where username = #{username}")
    String getPasswordByUsername(@Param("username") String username);
}

