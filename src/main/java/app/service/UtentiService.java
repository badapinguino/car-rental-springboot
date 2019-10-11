package app.service;

import app.DAO.UtenteRepository;
import app.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UtentiService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;
//    public UtentiService(UtenteRepository utenteRepository){
//        this.utenteRepository = utenteRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String codiceFiscale) throws UsernameNotFoundException {
        Utente user = utenteRepository.findByCodiceFiscale(codiceFiscale);
        if (user == null){
            throw new UsernameNotFoundException("Codice fiscale o password non valide.");
        }
        return new org.springframework.security.core.userdetails.User(user.getCodiceFiscale(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("user")));
//                mapRolesToAuthorities(user.getRoles()));
    }

//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//
//    }
}
