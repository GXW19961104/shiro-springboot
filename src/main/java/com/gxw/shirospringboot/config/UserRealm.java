package com.gxw.shirospringboot.config;

import com.gxw.shirospringboot.entity.User;
import com.gxw.shirospringboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=授权=doGetAuthorizationInfo");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject=SecurityUtils.getSubject();
        User CurrentUser=(User) subject.getPrincipal();
        info.addStringPermission(CurrentUser.getPrams());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token ) throws AuthenticationException {
        System.out.println("执行了=认证=doGetAuthorizationInfo");
        //用户名、密码 应从数据库中取
//        String name="root";
//        String password="123456";
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        User user=userService.queryUserByName(userToken.getUsername());

        if(user==null){
            return null;
        }
        Subject currentSubject=SecurityUtils.getSubject();
        Session session=currentSubject.getSession();
        session.setAttribute("loginUser",user);
        //密码认证，shiro封装好了
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
