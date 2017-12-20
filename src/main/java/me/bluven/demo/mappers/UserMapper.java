package me.bluven.demo.mappers;

import java.util.List;


import org.apache.ibatis.annotations.*;

import me.bluven.demo.entity.User;
import org.springframework.stereotype.Repository;
import com.github.pagehelper.Page;


/**
 * 基于注解实现持久化操作
 *
 */

@Repository
@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE name = #{name}")
	List<User> findByName(@Param("name") String name);

	@Select("SELECT * FROM user WHERE id = #{id}")
	List<User> findById(@Param("id") int id);

	@Insert("INSERT INTO user(name, password, email, mobile) VALUES(#{name}, #{password}, #{email}, #{mobile})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(User user);

	@Select("SELECT * FROM user WHERE 1=1 ")
	Page<User> findAll();

	@Update("UPDATE user SET password=#{password} WHERE username=#{username}")
	void update(User user);

	@Delete("DELETE FROM user WHERE id =#{id}")
	void delete(int id);

	@Results({ @Result(property = "username", column = "username"), @Result(property = "realname", column = "realname") })
	@Select("SELECT username,realname FROM user WHERE 1=1")
	List<User> queryById();
}