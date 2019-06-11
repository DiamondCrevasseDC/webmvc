package ind.ck.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Shiro中的Authentication，主要是检验token时使用
 */
public class AesToken implements AuthenticationToken {

    private String token;

    public AesToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
