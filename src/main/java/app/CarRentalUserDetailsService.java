package app;

import app.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//@Service
//public class CarRentalUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utente utente = userRepository.findByUsername(username);
//
//        if (utente == null) {
//            throw new UsernameNotFoundException("Username not found");
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                username
//                , utente.getPassword()
//                , Collections.singleton(new SimpleGrantedAuthority("user")));
//    }
//}
