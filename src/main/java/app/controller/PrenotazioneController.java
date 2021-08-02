package app.controller;

import app.DTO.PrenotazioneDTO;
import app.entity.Prenotazione;
import app.service.PrenotazioniService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrenotazioneController {

    private PrenotazioniService prenotazioniService;

    public PrenotazioneController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostAuthorize("returnObject.utente.codiceFiscale == authentication.name || hasAuthority('Admin')")
    @RequestMapping(value = "/api/prenotazioni/{id}", method = RequestMethod.GET)
    public Prenotazione selezionaPrenotazione(@PathVariable int id){
        return prenotazioniService.selezionaPrenotazione(id);
    }

    @PreAuthorize("#codiceFiscale == authentication.name || hasAuthority('Admin')")
    @RequestMapping(value="/api/prenotazioni", method = RequestMethod.GET)
    public @ResponseBody
    List<Prenotazione> selezionaPrenotazioniUtenteCF(@RequestParam(value = "codiceFiscale", required = false) String codiceFiscale) {
        if(codiceFiscale!=null) {
            return prenotazioniService.selezionaPrenotazioneCodiceFiscale(codiceFiscale);
        }else{
            return prenotazioniService.selezionaTuttePrenotazioni();
        }
    }

    @PreAuthorize("#prenotazioneDTO.utente.codiceFiscale == authentication.name")
    @RequestMapping(path = "/api/prenotazioni", method = RequestMethod.POST)
    public Prenotazione creaModificaVeicolo(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        return prenotazioniService.creaModificaPrenotazione(prenotazioneDTO);
    }

    @PostAuthorize("returnObject.utente.codiceFiscale == authentication.name")
    @RequestMapping(value = "/api/prenotazioni/{id}", method = RequestMethod.DELETE)
    public Prenotazione eliminaPrenotazioneById(@PathVariable int id) {
        return prenotazioniService.eliminaPrenotazioneById(id);
    }

}
