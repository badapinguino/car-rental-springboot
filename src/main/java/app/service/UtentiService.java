package app.service;

import app.DTO.UtenteDTO;
import app.entity.Utente;
import app.repository.UtenteRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UtentiService {

    private UtenteRepository utenteRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ModelMapper mapper;

    public UtentiService(UtenteRepository utenteRepository, BCryptPasswordEncoder passwordEncoder){
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = new ModelMapper();
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
    public Utente creaUtente(UtenteDTO utenteDTO) {
        // codifico la password che mi arriva dal frontend in chiaro (e non so quanto vada bene)
        utenteDTO.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        //mappare con map utenteDTO su utente
        Utente utente = mapper.map(utenteDTO, Utente.class);
        return utenteRepository.save(utente);
    }

    @Transactional
    public Utente creaModificaUtente(UtenteDTO utenteDTO) {
        Utente u = selezionaUtenteByCF(utenteDTO.getCodiceFiscale());
        if (u != null && u.getNome() != null && u.getId() > 0) {
            utenteDTO.setId(u.getId());
        }
        return creaUtente(utenteDTO);
    }

    @Transactional
    public Utente eliminaUtenteById(String id){
        Utente utenteEliminato = selezionaUtenteById(id);
        utenteRepository.delete(utenteEliminato);
        return utenteEliminato;
    }

    // TODO: non funziona, la password criptata risulta diversa da quella dell'utente e non so perché
    public Utente selezionaUtenteIdPassword(String id, String password) {
        Utente u = selezionaUtenteById(id);
        String passwordCriptata = passwordEncoder.encode(password);
        if(u != null && u.getPassword().equals(passwordCriptata)){
            return u;
        }else{
            return null;
        }
    }
}
