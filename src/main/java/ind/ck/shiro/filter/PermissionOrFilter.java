package ind.ck.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PermissionOrFilter extends AuthorizationFilter {

    /**
     * 自定义权限过滤器，拥有多权限之一的用户可以通过
     * @param servletRequest
     * @param servletResponse
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object object) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] permissions = (String[]) object;

        if (permissions == null || permissions.length == 0){
            return true;
        }

        for (String permission : permissions){
            if (subject.isPermitted(permission)){
                return true;
            }
        }

        return false;
    }
}
