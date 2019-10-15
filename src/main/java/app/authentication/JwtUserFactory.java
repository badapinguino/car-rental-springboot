package app.authentication;

import app.authentication.model.JwtUser;
import app.entity.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Utente user) {
        return new JwtUser(
                user.getCodiceFiscale(),
                user.getPassword(),
                mapToGrantedAuthorities(/*user.getAuthorities()*/user.isSuperuser()),
                true
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(/*List<Authority> authorities*/ boolean superuser) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Customer"));
        authorities.add(new SimpleGrantedAuthority("Admin"));
        if(superuser){
            return authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority("Admin"))
                    .collect(Collectors.toList());
        }else{
            return authorities.stream()
                    .map(authority -> new SimpleGrantedAuthority("Customer"))
                    .collect(Collectors.toList());
        }
    }
}
