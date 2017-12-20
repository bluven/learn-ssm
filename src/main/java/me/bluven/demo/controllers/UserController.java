package me.bluven.demo.controllers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import me.bluven.demo.entity.User;
import me.bluven.demo.mappers.UserMapper;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public User create(@Validated @RequestBody User user) {
        userMapper.insert(user);
        return user;
    }

    @GetMapping
    public PageInfo<User> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return userMapper.findAll().toPageInfo();
    }
}
