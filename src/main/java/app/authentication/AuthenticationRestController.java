package app.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationRestController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService; // al posto di UserDetailsService

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, /*Device device,*/ HttpServletResponse response) throws AuthenticationException, JsonProcessingException {
        // Effettuo l autenticazione
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Genero Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails/*, device*/);
        response.setHeader(tokenHeader,token);
        // Ritorno il token
        return ResponseEntity.ok(new JwtAuthenticationResponse(userDetails.getUsername(),userDetails.getAuthorities(), token));
    }

    @RequestMapping(value = "protected/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(tokenHeader);
        UserDetails userDetails =
                (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            response.setHeader(tokenHeader,refreshedToken);
            return ResponseEntity.ok(new JwtAuthenticationResponse(userDetails.getUsername(),userDetails.getAuthorities(), token));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
