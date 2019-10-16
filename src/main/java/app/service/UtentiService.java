package app.service;

import app.entity.Utente;
import app.repository.UtenteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UtentiService {

    private UtenteRepository utenteRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UtentiService(UtenteRepository utenteRepository, BCryptPasswordEncoder passwordEncoder){
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List selezionaTuttiUtenti(){
        return utenteRepository.findAll();
    }

    public Utente selezionaUtenteById(String id) {
        if(StringUtils.isNumeric(id)){ // checks if is a string made by only digits
            return utenteRepository.findById(Integer.parseInt(id));
        }else{
            return utenteRepository.findByCodiceFiscale(id);
        }
    }

    public Utente selezionaUtenteByCF(String codiceFiscale) {
        return utenteRepository.findByCodiceFiscale(codiceFiscale);
    }

    @Transactional
    public Utente creaUtente(Utente utente) {
        // codifico la password che mi arriva dal frontend in chiaro (e non so quanto vada bene)
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    @Transactional
    public Utente creaModificaUtente(Utente utente) {
        Utente u = selezionaUtenteByCF(utente.getCodiceFiscale());
        if (u != null && u.getNome() != null && u.getId() > 0) {
            utente.setId(u.getId());
        }
        return creaUtente(utente);
    }

    @Transactional
    public Utente eliminaUtenteById(String id){
        Utente utenteEliminato = selezionaUtenteById(id);
        utenteRepository.delete(utenteEliminato);
        return utenteEliminato;
    }

}
