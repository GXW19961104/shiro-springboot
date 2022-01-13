package com.gxw.shirospringboot;

import com.gxw.shirospringboot.entity.User;
import com.gxw.shirospringboot.mapper.UserMapper;
import com.gxw.shirospringboot.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public UserServiceImpl userService;
    @Test
    void contextLoads() {
    }
    @Test
    public void testMp(){
        List<User> users=userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSer(){
        User user=userService.queryUserByName("root");
        System.out.println(user);
    }


}
