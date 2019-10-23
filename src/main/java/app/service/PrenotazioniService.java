package app.service;

import app.DTO.PrenotazioneDTO;
import app.entity.Prenotazione;
import app.repository.PrenotazioneRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrenotazioniService {

    private final UtentiService utentiService;
    private PrenotazioneRepository prenotazioneRepository;
    private ModelMapper mapper;

    public PrenotazioniService(PrenotazioneRepository prenotazioneRepository, UtentiService utentiService){
        this.prenotazioneRepository = prenotazioneRepository;
        this.mapper = new ModelMapper();
        this.utentiService = utentiService;
    }

    public List<Prenotazione> selezionaTuttePrenotazioni(){
        return prenotazioneRepository.findAll();
    }


    //TODO: Richiamare separatamente queste due a seconda se numero o no all'interno del controller
    public Prenotazione selezionaPrenotazione(int id) {
        return prenotazioneRepository.findById(id);
    }
    public List<Prenotazione> selezionaPrenotazioneCodiceFiscale(String codiceFiscale) {
        return prenotazioneRepository.findAllByUtente(utentiService.selezionaUtenteById(codiceFiscale));
    }

    @Transactional
    public Prenotazione creaModificaPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione p = selezionaPrenotazione(prenotazioneDTO.getId());
        if (p != null && p.getDataInizio() != null && p.getId() > 0) {
            prenotazioneDTO.setId(p.getId());
        }
        return creaPrenotazione(prenotazioneDTO);
    }

    @Transactional
    public Prenotazione creaPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        System.out.println(prenotazioneDTO);
        //mappare con map veicoloDTO su veicolo
        Prenotazione prenotazione = mapper.map(prenotazioneDTO, Prenotazione.class);
        return prenotazioneRepository.save(prenotazione);
    }

    @Transactional
    public Prenotazione eliminaPrenotazioneById(int id){
        Prenotazione prenotazioneEliminata = selezionaPrenotazione(id);
        prenotazioneRepository.delete(prenotazioneEliminata);
        return prenotazioneEliminata;
    }
}
