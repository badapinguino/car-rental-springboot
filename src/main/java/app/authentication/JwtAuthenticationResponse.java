package app.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String username;
    private String jwt;
    Collection<? extends GrantedAuthority> authorities;

    public JwtAuthenticationResponse(String username, Collection<? extends GrantedAuthority> authorities, String jwt) {
        this.jwt = jwt;
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return this.username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
