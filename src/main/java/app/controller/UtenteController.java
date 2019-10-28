package app.controller;

import app.DTO.UtenteDTO;
import app.entity.Utente;
import app.service.UtentiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UtenteController {

    private UtentiService utentiService;

    public UtenteController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    @GetMapping
    @RequestMapping("/api/utenti")
    public List selezionaTuttiUtenti(){
        return utentiService.selezionaTuttiUtenti();
    }

    @RequestMapping("/api/utenti/{id}")
    @GetMapping
    public Utente selezionaUtenteById(@PathVariable String id) {
        return utentiService.selezionaUtenteById(id);
    }


    // Qui però viene passata la password tra client e server in chiaro. Non è l'ideale
    // Meglio inserire un DTO se no ogni volta che devo modificare un utente mi viene sprecato un id nel DB,
    //  perché viene assegnato all'utente temporaneo che poi viene sostituito
    //  con quello dell'utente da modificare presente nel DB
    @RequestMapping(path = "/api/utenti", method = RequestMethod.POST)
    public Utente creaModificaUtente(@RequestBody UtenteDTO utenteDTO) {
        return utentiService.creaModificaUtente(utenteDTO);
    }

    @RequestMapping(value = "/api/utenti/{id}", method = RequestMethod.DELETE)
//    @DeleteMapping
    public Utente eliminaUtenteById(@PathVariable String id) {
        return utentiService.eliminaUtenteById(id);
    }

}
