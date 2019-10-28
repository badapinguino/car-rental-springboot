package app.repository;

import app.entity.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface VeicoloRepository extends JpaRepository<Veicolo, Integer> {

    Veicolo findByCodiceMezzo(String codiceMezzo);

    Veicolo findById(int id);

    Veicolo findFirstByTarga(String targa);

    Veicolo save(Veicolo veicolo);

    void delete(Veicolo veicolo);
}
