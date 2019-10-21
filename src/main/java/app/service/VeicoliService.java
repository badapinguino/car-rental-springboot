package app.service;

import app.entity.Veicolo;
import app.repository.VeicoloRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeicoliService {
    VeicoloRepository veicoloRepository;

    public VeicoliService(VeicoloRepository veicoloRepository){
        this.veicoloRepository = veicoloRepository;
    }

    public List<Veicolo> selezionaTuttiVeicoli(){
        return veicoloRepository.findAll();
    }

}
