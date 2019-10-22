package app.service;

import app.DTO.VeicoloDTO;
import app.entity.Veicolo;
import app.repository.VeicoloRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VeicoliService {

    VeicoloRepository veicoloRepository;
    private ModelMapper mapper;

    public VeicoliService(VeicoloRepository veicoloRepository){
        this.veicoloRepository = veicoloRepository;
        this.mapper = new ModelMapper();
    }

    public List<Veicolo> selezionaTuttiVeicoli(){
        return veicoloRepository.findAll();
    }

    public Veicolo selezionaVeicolo(String id) {
        if(StringUtils.isNumeric(id)){ // checks if is a string made by only digits
            return veicoloRepository.findById(Integer.parseInt(id));
        }else{
            return veicoloRepository.findByCodiceMezzo(id);
        }
    }

    @Transactional
    public Veicolo creaModificaVeicolo(VeicoloDTO veicoloDTO) {
        Veicolo v = selezionaVeicolo(veicoloDTO.getCodiceMezzo());
        if (v != null && v.getModello() != null && v.getId() > 0) {
            veicoloDTO.setId(v.getId());
        }
        return creaVeicolo(veicoloDTO);
    }

    @Transactional
    public Veicolo creaVeicolo(VeicoloDTO veicoloDTO) {
        System.out.println(veicoloDTO);
        //mappare con map veicoloDTO su veicolo
        Veicolo veicolo = mapper.map(veicoloDTO, Veicolo.class);
        return veicoloRepository.save(veicolo);
    }

    @Transactional
    public Veicolo eliminaVeicoloById(String id){
        Veicolo veicoloEliminato = selezionaVeicolo(id);
        veicoloRepository.delete(veicoloEliminato);
        return veicoloEliminato;
    }
}
