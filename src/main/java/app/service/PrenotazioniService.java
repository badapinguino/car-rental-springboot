package app.service;

import app.DTO.PrenotazioneDTO;
import app.entity.Prenotazione;
import app.entity.Veicolo;
import app.error.exception.DataInizioDopoDataFineException;
import app.error.exception.ModificaPrenotazionePrimaNGiorniException;
import app.error.exception.PrenotazioneNelPassatoException;
import app.error.exception.VeicoloNonDisponibileDateSelezionateException;
import app.repository.PrenotazioneRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PrenotazioniService {

    private final UtentiService utentiService;
    private PrenotazioneRepository prenotazioneRepository;
    private ModelMapper mapper;
    private VeicoliService veicoliService;

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
            if(p.getDataInizio().compareTo(LocalDate.now().plusDays(2))>=0) {
                prenotazioneDTO.setId(p.getId());
            } else {
                throw new ModificaPrenotazionePrimaNGiorniException(2);
            }
        }
        return creaPrenotazione(prenotazioneDTO);
    }

    @Transactional
    public Prenotazione creaPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione;
        if( (prenotazioneDTO.getDataInizio().compareTo(LocalDate.now())<=0) ||
                (prenotazioneDTO.getDataFine().compareTo(LocalDate.now())<=0) ){
            throw new PrenotazioneNelPassatoException();
        }
        if(prenotazioneDTO.getDataInizio().compareTo(prenotazioneDTO.getDataFine())<=0){
            //mappare con map veicoloDTO su veicolo
             prenotazione = mapper.map(prenotazioneDTO, Prenotazione.class);
        }else{
            throw new DataInizioDopoDataFineException();
        }

        boolean controlloDataInizio = esistePrenotazioneDataVeicolo(prenotazioneDTO.getDataInizio(),
                prenotazioneDTO.getVeicolo(), prenotazioneDTO.getId());
        boolean controlloDataFine = esistePrenotazioneDataVeicolo(prenotazioneDTO.getDataFine(),
                prenotazioneDTO.getVeicolo(), prenotazioneDTO.getId());
        List<Prenotazione> prenotazioniVeicolo = selezionaPrenotazioniByCodiceMezzo(prenotazioneDTO.getVeicolo().getId());
        boolean controlloNelMezzo = false;
        for (Prenotazione p : prenotazioniVeicolo ) {
            if( p.getId()!=prenotazioneDTO.getId() && (((p.getDataInizio()).compareTo(prenotazioneDTO.getDataInizio()) >= 0 && p.getDataInizio().compareTo(prenotazioneDTO.getDataFine()) <= 0) || (p.getDataFine().compareTo(prenotazioneDTO.getDataInizio()) >= 0 && p.getDataFine().compareTo(prenotazioneDTO.getDataFine()) <= 0))){
                controlloNelMezzo = true;
            }
        }
        if( !controlloDataInizio && !controlloDataFine && !controlloNelMezzo){
            //continua con il salvataggio della prenotazione
        }else{
            throw new VeicoloNonDisponibileDateSelezionateException();
        }

        return prenotazioneRepository.save(prenotazione);
    }

    @Transactional
    public Prenotazione eliminaPrenotazioneById(int id){
        Prenotazione prenotazioneEliminata = selezionaPrenotazione(id);
        if(prenotazioneEliminata.getDataInizio().compareTo(LocalDate.now().plusDays(2))>=0) {
            prenotazioneRepository.delete(prenotazioneEliminata);
            return prenotazioneEliminata;
        }else{
            throw new ModificaPrenotazionePrimaNGiorniException(2);
        }
    }


    @Transactional
    public boolean esistePrenotazioneDataVeicolo(LocalDate dataSelezionata, Veicolo veicolo, int numeroPrenotazione){
        Prenotazione prenotazione = null;
            prenotazione = prenotazioneRepository.getPrenotazioneDataVeicolo(veicolo.getId(), dataSelezionata);
        if(prenotazione==null || prenotazione.getId() == numeroPrenotazione){
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    public List<Prenotazione> selezionaPrenotazioniByCodiceMezzo(int codiceMezzo){
        List<Prenotazione> prenotazioni = null;
            prenotazioni = prenotazioneRepository.selezionaPrenotazioniByCodiceMezzo(codiceMezzo);
        return prenotazioni;
    }
}
