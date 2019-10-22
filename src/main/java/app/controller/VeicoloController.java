package app.controller;

import app.DTO.VeicoloDTO;
import app.entity.Veicolo;
import app.service.VeicoliService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/api/veicoli/{id}", method = RequestMethod.GET)
    public Veicolo selezionaVeicolo(@PathVariable String id){
        return veicoliService.selezionaVeicolo(id);
    }

    @RequestMapping(path = "/api/veicoli", method = RequestMethod.POST)
    public Veicolo creaModificaVeicolo(@RequestBody VeicoloDTO veicoloDTO) {
        return veicoliService.creaModificaVeicolo(veicoloDTO);
    }

    @RequestMapping(value = "/api/veicoli/{id}", method = RequestMethod.DELETE)
    public Veicolo eliminaVeicoloById(@PathVariable String id) {
        return veicoliService.eliminaVeicoloById(id);
    }

}
