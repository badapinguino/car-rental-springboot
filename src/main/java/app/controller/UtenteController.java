package app.controller;

import app.DAO.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UtenteController {

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public List getUsers(){
        return utenteRepository.findAll();
    }

}
