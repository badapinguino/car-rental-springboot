package app.repository;

import app.entity.Prenotazione;
import app.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    Prenotazione findById(int id);

    List<Prenotazione> findAllByUtente(Utente utente);

    Prenotazione save(Prenotazione prenotazione);

    void delete(Prenotazione prenotazione); // controllare se funziona il Veicolo come return

}
