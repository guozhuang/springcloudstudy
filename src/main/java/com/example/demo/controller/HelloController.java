package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//简单服务编写：这里的注解会应用在当前控制器的全体上
@RestController
public class HelloController {
    private final JdbcTemplate jdbcTemplate;

    //此处相当于声明当前类下进行的实例化对象jdbcTemplate
    //如果不这么写的话，应该放到启动时转化成bean的方式
    @Autowired
    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // 根据主键ID查询
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                new BeanPropertyRowMapper<>(User.class));
    }
}
