package app;

import app.repository.UtenteRepository;
import app.entity.Utente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories
public class Application {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UtenteRepository utenteRepository;

    private ModelMapper mapper;

//    @PostConstruct
//    public void init(){
//        Utente user = new Utente();
//            user.setCodiceFiscale("BDGDNL97C12F704S");
//            user.setNome("Daniele");
//            user.setCognome("Badagliacca");
//            LocalDate data = LocalDate.of(1997, 3, 12);
//            user.setDataNascita(data);
//            user.setPassword(passwordEncoder.encode("1234"));
//            user.setSuperuser(true);
//
//        if (utenteRepository.findByCodiceFiscale(user.getCodiceFiscale()) == null){
//            utenteRepository.save(user);
//        }
//    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
