package app.service;

import app.DTO.UtenteDTO;
import app.entity.Utente;
import app.repository.UtenteRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
            try {
                return utenteRepository.findById(Integer.parseInt(id));
            }catch (NumberFormatException nfe){
                // se è un numero che da eccezione probabilmente è lungo 16 caratteri ed è la stringa del codice fiscale
                return utenteRepository.findByCodiceFiscale(id);
            }
        }else{
            return utenteRepository.findByCodiceFiscale(id);
        }
    }

    private Utente selezionaUtenteByCF(String codiceFiscale) {
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
        if(utenteDTO.getCodiceFiscale().length()!=Utente.getLunghezzaCampoCodiceFiscale()){
            throw new RuntimeException("Il codice fiscale deve essere lungo " +
                    Utente.getLunghezzaCampoCodiceFiscale() + " caratteri.");
        }else if(utenteDTO.getNome().length()>Utente.getLunghezzaCampoNome()){
            throw new RuntimeException("Il nome non può essere più lungo di " +
                    Utente.getLunghezzaCampoNome() + " caratteri.");
        }else if(utenteDTO.getCognome().length()>Utente.getLunghezzaCampoCognome()){
            throw new RuntimeException("Il cognome non può essere più lungo di " +
                    Utente.getLunghezzaCampoCognome() + " caratteri.");
        }else if(utenteDTO.getDataNascita().compareTo(LocalDate.parse("1900-01-01")) < 0){
            throw new RuntimeException("La data di nascita non può essere antecedente al 1900.");
        }else if(utenteDTO.getDataNascita().compareTo(LocalDate.now()) > 0){
            throw new RuntimeException("La data di nascita non può essere futura.");
        }

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
}
