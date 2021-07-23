package app.service;

import app.DTO.UtenteDTO;
import app.controller.UtenteController;
import app.email.Mailer;
import app.entity.Utente;
import app.repository.UtenteRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Utente> listaUtenti = utenteRepository.findAll();
        List<UtenteDTO> listaUtentiDto = new ArrayList<>();
        for (Utente utente: listaUtenti) {
            utente.setPassword(null);
            UtenteDTO uDTO = new UtenteDTO(utente);
            // Non ho inserito la password tra i valori ritornati
            listaUtentiDto.add(uDTO);
        }
        return listaUtentiDto;
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
    public UtenteDTO selezionaUtenteDTOById(String id) {
        if(StringUtils.isNumeric(id)){ // checks if is a string made by only digits
            try {
                Utente utente = utenteRepository.findById(Integer.parseInt(id));
                // Non inserisco la password tra i valori ritornati
                utente.setPassword(null);
                UtenteDTO utenteDTO = new UtenteDTO(utente);
                return utenteDTO;
            }catch (NumberFormatException nfe){
                // se è un numero che da eccezione probabilmente è lungo 16 caratteri ed è la stringa del codice fiscale
                Utente utente = utenteRepository.findByCodiceFiscale(id);
                // Non inserisco la password tra i valori ritornati
                utente.setPassword(null);
                UtenteDTO utenteDTO = new UtenteDTO(utente);
                return utenteDTO;
            }
        }else{
            Utente utente = utenteRepository.findByCodiceFiscale(id);
            // Non inserisco la password tra i valori ritornati
            utente.setPassword(null);
            UtenteDTO utenteDTO = new UtenteDTO(utente);
            return utenteDTO;
        }
    }

    private Utente selezionaUtenteByCF(String codiceFiscale) {
        return utenteRepository.findByCodiceFiscale(codiceFiscale);
    }

    @Transactional
    public Utente creaUtente(UtenteDTO utenteDTO) {
        // codifico la password che mi arriva dal frontend in chiaro (e non so quanto vada bene)
        utenteDTO.setPassword(passwordEncoder.encode(utenteDTO.getPassword()));
        // controllo se utente è verificato o altrimenti invio mail di conferma
        if(!utenteDTO.isVerificato()){
            //invio mail
            //from,password,to,subject,message
            Mailer.send("carrental.badapinguino@gmail.com","carrental",utenteDTO.getEmail(),
                    "Completa la registrazione",
                    "Per completare l'iscrizione su Car Rental clicca sul link seguente: \n" +
                            "http://localhost:4200/completaIscrizione?codiceFiscale=" + utenteDTO.getCodiceFiscale());
        }
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
            // controllo se password vecchia uguale a nuova
            if(passwordEncoder.matches(utenteDTO.getVecchiaPassword(), u.getPassword())){
                utenteDTO.setId(u.getId());
            }else{
                throw new RuntimeException("La password precedente inserita non corrisponde alla password dell'utente.");
            }
        }
        return creaUtente(utenteDTO);
    }

    @Transactional
    public UtenteDTO eliminaUtenteById(String id){
        Utente utenteEliminato = selezionaUtenteById(id);
        utenteRepository.delete(utenteEliminato);
        UtenteDTO utenteDTOEliminato = new UtenteDTO(utenteEliminato);
        return utenteDTOEliminato;
    }

    @Transactional
    public void aggiornaImmagineUtente(String idUtente, MultipartFile file) throws IOException {
        String path = "./images/";
        File pathAsFile = new File(path);
        if (!Files.exists(Paths.get(path))) {
            pathAsFile.mkdir();
        }
        String filename = file.getOriginalFilename();
        System.out.println(path  + filename);
        File convFile = new File(path  + filename);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        System.out.println(convFile.getName());

        Utente utenteACuiAggiungereImmagine = selezionaUtenteById(idUtente);
        utenteRepository.updateImageQuery(utenteACuiAggiungereImmagine.getId(), path + filename);

//        Utente utenteACuiAggiungereImmagine = selezionaUtenteById(idUtente);
//        UtenteDTO utenteDTOConImmagineAggiunta = mapper.map(utenteACuiAggiungereImmagine, UtenteDTO.class);
//        utenteDTOConImmagineAggiunta.setImmagine(path + filename);
//            utentiService.creaModificaUtente(utenteDTOConImmagineAggiunta);
    }

    @Transactional
    public void aggiornaUtenteVerificato(String idUtente, boolean verificato){
        Utente utenteDaAggiornareVerificato = selezionaUtenteById(idUtente);
        utenteRepository.updateUtenteVerificato(utenteDaAggiornareVerificato.getId(), verificato);
    }
}
