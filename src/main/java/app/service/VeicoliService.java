package app.service;

import app.DTO.VeicoloDTO;
import app.entity.Veicolo;
import app.repository.VeicoloRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
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
        if(veicoloDTO.getCasaCostruttrice().length()>Veicolo.getLunghezzaCampoCasaCostruttrice()){
            throw new RuntimeException("Il nome della casa costruttrice non può superare i " +
                    Veicolo.getLunghezzaCampoCasaCostruttrice() + " caratteri.");
        }else if(veicoloDTO.getModello().length()>Veicolo.getLunghezzaCampoModello()){
            throw new RuntimeException("La casa costruttrice non può superare i " +
                    Veicolo.getLunghezzaCampoModello() + " caratteri.");
        }else if(veicoloDTO.getCodiceMezzo().length()>Veicolo.getLunghezzaCampoCodiceMezzo()){
            throw new RuntimeException("Il codice del mezzo non può superare i " +
                    Veicolo.getLunghezzaCampoCodiceMezzo() + " caratteri.");
        }else if(veicoloDTO.getTarga().length()>Veicolo.getLunghezzaCampoTarga()){
            throw new RuntimeException("La targa non può essere più lunga di " +
                    Veicolo.getLunghezzaCampoTarga() + " caratteri.");
        }else if(veicoloDTO.getAnno() < 1900 || veicoloDTO.getAnno() > Calendar.getInstance().get(Calendar.YEAR)+1){
            throw new RuntimeException("L'anno deve essere compreso tra il 1900 ed il " +
                    (Calendar.getInstance().get(Calendar.YEAR)+1) + ".");
        }else if(veicoloDTO.getPrezzoGiornata()<=0){
            throw new RuntimeException("Il prezzo del veicolo per una giornata deve essere superiore a 0€.");
        }
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
