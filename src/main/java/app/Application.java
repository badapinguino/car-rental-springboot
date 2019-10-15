package app;

import app.repository.UtenteRepository;
import app.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    @PostConstruct
    public void init(){
        Utente user = new Utente();
            user.setCodiceFiscale("BDGDNL97C12F704S");
            user.setNome("Daniele");
            user.setCognome("Badagliacca");
            LocalDate data = LocalDate.of(1997, 3, 12);
            user.setDataNascita(data);
            user.setPassword(passwordEncoder.encode("1234"));
            user.setSuperuser(true);
//                new Utente(
//                "Memory",
//                "Not Found",
//                "info@memorynotfound.com",
//                passwordEncoder.encode("password"),
//                Arrays.asList(
//                        new Role("ROLE_USER"),
//                        new Role("ROLE_ADMIN")));

        if (utenteRepository.findByCodiceFiscale(user.getCodiceFiscale()) == null){
            utenteRepository.save(user);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }

}
