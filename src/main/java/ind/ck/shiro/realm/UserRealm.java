package ind.ck.shiro.realm;

import ind.ck.entity.Permission;
import ind.ck.entity.Role;
import ind.ck.entity.User;
import ind.ck.service.PermissionService;
import ind.ck.service.RoleService;
import ind.ck.service.UserService;
import ind.ck.shiro.token.AesToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private User user = null;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AesToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("---------权限认证------------");

        List<String> roleIds = roleService.findRoleIdByUserId(user.getId());
        List<Permission> permissions = permissionService.findByRoleIds(roleIds);

        Set<String> permissionSet = new HashSet<>(16);
        for(Permission permission : permissions){
            permissionSet.add(permission.getName());
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("---------身份认证------------");

        String token = (String) authenticationToken.getCredentials();
        user = userService.findByToken(token);

        if(this.user== null){
            throw new AuthenticationException("token认证失败！");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
