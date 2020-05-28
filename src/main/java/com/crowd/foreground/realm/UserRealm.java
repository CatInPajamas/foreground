package com.crowd.foreground.realm;

import com.crowd.foreground.entity.User;
import com.crowd.foreground.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user =userService.findByUserName(token.getUsername());

        if(user==null){
            //用户名不存在
            return null;//shiro底层会抛出UnKnowAccountException
        }

        //2.判断密码
        //第二个字段是user.getPassword()，注意这里是指从数据库中获取的password。第三个字段是realm，即当前realm的名称。
        //这块对比逻辑是先对比username，但是username肯定是相等的，所以真正对比的是password。
        //从这里传入的password（这里是从数据库获取的）和token（filter中登录时生成的）中的password做对比，如果相同就允许登录，
        // 不相同就抛出IncorrectCredentialsException异常。
        //如果认证不通过，就不会执行下面的授权方法了
        return new SimpleAuthenticationInfo(user,user.getUserpswd(),"");
    }

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }
}
