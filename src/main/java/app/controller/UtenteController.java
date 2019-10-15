package app.controller;

import app.entity.Utente;
import app.repository.UtenteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Transactional va nel Service, e dal controller devo richiamare i service, che poi richiamano i repository! Da cambiare

@RestController
public class UtenteController {

    private UtenteRepository utenteRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UtenteController(UtenteRepository utenteRepository, BCryptPasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @RequestMapping("/utenti")
    public List selezionaTuttiUtenti(){
        return utenteRepository.findAll();
    }

    @RequestMapping("/utenti/{id}")
    @GetMapping
    public Utente selezionaUtenteById(@PathVariable String id) {
        if(StringUtils.isNumeric(id)){ // checks if is a string made by only digits
            return utenteRepository.findById(Integer.parseInt(id));
        }else{
            return utenteRepository.findByCodiceFiscale(id);
        }

    }

    @RequestMapping(path = "/utenti", method = RequestMethod.GET)
    public Utente selezionaUtentiByCF(@RequestParam String codiceFiscale) {
        return utenteRepository.findByCodiceFiscale(codiceFiscale);
    }

    //CAMBIARE CON SAVE OR UPDATE, SE NO DA ERRORE 500:
    // could not execute statement; SQL [n/a]; constraint [codice_fiscale]; nested exception is
    // org.hibernate.exception.ConstraintViolationException: could not execute statement

    //qui però viene passato tra client e server in chiaro. Non è l'ideale
//    @PostMapping
    @RequestMapping(path = "/utenti", method = RequestMethod.POST)
    public Utente creaUtente(@RequestBody Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

//    @PutMapping("/{id}")
//    public Contact update(@PathVariable("id") long id, @RequestBody Contact contact) {
//        return contactService.update(id, contact);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") long id) {
//        contactService.delete(id);
//    }

//    @RequestMapping(path = "/mno/objectKey/{id}/{name}", method = RequestMethod.GET)
//    public Book getBook(@PathVariable int id, @PathVariable String name) {
//        // code here
//    }

//    @RequestMapping("/utenti")
//    public List selezionaUtente(){
//        return utenteRepository.findByCodiceFiscale();
//    }

}
