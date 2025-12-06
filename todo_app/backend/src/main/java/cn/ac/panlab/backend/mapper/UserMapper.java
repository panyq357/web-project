package cn.ac.panlab.backend.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ac.panlab.backend.model.User;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(1) > 0 FROM users WHERE username = #{username}")
    boolean existsByUsername(@Param("username") String username);

    @Insert("INSERT INTO users (username, email, password) VALUES (#{username}, #{email}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT id, username, password, email FROM users where username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Update("UPDATE users SET username=#{username}, password=#{password}, email=#{email} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE username = #{username}")
    void deleteUserByUsername(@Param("username") String username);
}

