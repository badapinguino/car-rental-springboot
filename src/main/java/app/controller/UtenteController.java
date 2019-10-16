package app.controller;

import app.entity.Utente;
import app.service.UtentiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {

    private UtentiService utentiService;

    public UtenteController(UtentiService utentiService) {
        this.utentiService = utentiService;
    }

    @GetMapping
    @RequestMapping("/utenti")
    public List selezionaTuttiUtenti(){
        return utentiService.selezionaTuttiUtenti();
    }

    @RequestMapping("/utenti/{id}")
    @GetMapping
    public Utente selezionaUtenteById(@PathVariable String id) {
        return utentiService.selezionaUtenteById(id);
    }

//    @RequestMapping(path = "/utenti", method = RequestMethod.GET)
//    public Utente selezionaUtentiByCF(@RequestParam String codiceFiscale) {
//        return utentiService.selezionaUtenteByCF(codiceFiscale);
//    }


    // qui però viene passata la password tra client e server in chiaro. Non è l'ideale
    // TODO: meglio inserire un DTO se no ogni volta che devo modificare un utente mi viene sprecato un id nel DB,
    //  perché viene assegnato all'utente temporaneo che poi viene sostituito
    //  con quello dell'utente da modificare presente nel DB
    @RequestMapping(path = "/utenti", method = RequestMethod.POST)
    public Utente creaModificaUtente(@RequestBody Utente utente) {
        return utentiService.creaModificaUtente(utente);
    }

    @RequestMapping(value = "/utenti/{id}", method = RequestMethod.DELETE)
//    @DeleteMapping
    public Utente eliminaUtenteById(@PathVariable String id) {
        return utentiService.eliminaUtenteById(id);
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

}
