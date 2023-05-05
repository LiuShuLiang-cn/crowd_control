package org.zucc.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.zucc.dao.UserDao;
import org.zucc.entity.User;

/**
 * 自定义realm
 */
@Component
public class CustomerRealm extends AuthorizingRealm {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /**
         *  授权
         * @author  LiuShuLiang
         * @date    2023/4/27 10:46
         * @param   principals
         * @return  org.apache.shiro.authz.AuthorizationInfo
         */
        // 获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        // 根据主身份信息获取角色和权限信
        User user = userDao.getUserByName(primaryPrincipal);
        // 授权角色信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         *  认证
         * @author  LiuShuLiang
         * @date    2023/4/27 10:46
         * @param   token
         * @return  org.apache.shiro.authc.AuthenticationInfo
         */
        String principal = (String) token.getPrincipal();
        //在工厂中获取service对象
        User user = userDao.getUserByName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), this.getName());
        }
        return null;
    }
}
