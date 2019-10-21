package app.controller;

import app.service.VeicoliService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class VeicoloController {

    private VeicoliService veicoliService;

    public VeicoloController(VeicoliService veicoliService) {
        this.veicoliService = veicoliService;
    }

    @GetMapping
    @RequestMapping("/api/veicoli")
    public List selezionaTuttiVeicoli(){
        return veicoliService.selezionaTuttiVeicoli();
    }

}
