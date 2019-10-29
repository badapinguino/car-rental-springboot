package app.controller;

import app.DTO.UtenteDTO;
import app.entity.Utente;
import app.service.UtentiService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UtenteController {

    private UtentiService utentiService;
    private ModelMapper mapper;

    public UtenteController(UtentiService utentiService) {
        this.utentiService = utentiService;
        this.mapper = new ModelMapper();
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



//    @RequestMapping(method = RequestMethod.POST,value ="/api/upload", headers=("content-type=image/*"))
    @PostMapping(value = "/api/upload/{idUtente}")
    public void postImage(@PathVariable String idUtente, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("received" + file);
        if(file!=null && file.getSize()>0) {
            String path = "/images/";
            String filename = file.getOriginalFilename();
            System.out.println(path  + filename);
            File convFile = new File(path  + filename);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            System.out.println(convFile.getName());

            Utente utenteACuiAggiungereImmagine = utentiService.selezionaUtenteById(idUtente);
            utenteACuiAggiungereImmagine.setImmagine(path + filename);
            UtenteDTO utenteDTOConImmagineAggiunta = mapper.map(utenteACuiAggiungereImmagine, UtenteDTO.class);
            utentiService.creaModificaUtente(utenteDTOConImmagineAggiunta);

//            utenteDTO.setImmagine(path + filename);
        }else{
//            utenteDTO.setImmagine(vecchioFile);
            System.out.println("NO FILE");
        }
    }

}
