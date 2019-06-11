package ind.ck.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import ind.ck.shiro.token.AesToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AesFilter extends BasicHttpAuthenticationFilter {

    /**
     * 过滤方法
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = ((HttpServletRequest) request).getHeader("token");
        Map<String, Object> resultMap = new HashMap<>(16);

        // 判断请求头上是否有token
        if (token != null){
            try {
                AesToken at = new AesToken(token);
                getSubject(request, response).login(at);
                return true;
            }catch (AuthenticationException e){
                e.printStackTrace();
                resultMap.put("message", "token错误或已过期！");
                JSONObject jsonObject = new JSONObject(resultMap);
                responseOutWithJson(response, jsonObject);
                return false;
            }
        }

        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 以JSON格式输出
     * @param servletResponse
     * @param jsonObject
     */
    protected void responseOutWithJson(ServletResponse servletResponse, JSONObject jsonObject) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        try(PrintWriter pw = response.getWriter()){
            pw.append(jsonObject.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
