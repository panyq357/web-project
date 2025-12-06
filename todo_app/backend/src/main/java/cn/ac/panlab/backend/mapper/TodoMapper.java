package cn.ac.panlab.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.ac.panlab.backend.model.Todo;

@Mapper
public interface TodoMapper {

    @Insert("INSERT INTO todos (user_id, message) VALUES (#{userId}, #{message})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // Let MyBatis write auto increment id back to todo if insert success.
    void insertTodo(Todo todo);

    @Select("SELECT id, user_id as userId, message FROM todos WHERE id = #{id}")
    Todo getTodoById(@Param("id") Long id);

    @Select("SELECT id, message FROM todos WHERE user_id = #{userId}")
    List<Todo> getTodoListByUserId(@Param("userId") Long userId);

    @Update("UPDATE todos SET message=#{message} WHERE id=#{id}")
    void updateTodo(Todo todo);

    @Delete("DELETE FROM todos WHERE id = #{id}")
    int deleteTodoById(@Param("id") Long id);
}
