package app.repository;

import app.entity.Prenotazione;
import app.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {

    Prenotazione findById(int id);

    List<Prenotazione> findAllByUtente(Utente utente);

    Prenotazione save(Prenotazione prenotazione);

    void delete(Prenotazione prenotazione); // controllare se funziona il Veicolo come return


    @Query("SELECT p FROM Prenotazione p WHERE id_veicolo= ?1 AND ?2 >=dataInizio AND ?2 <=dataFine")
    Prenotazione getPrenotazioneDataVeicolo(int idVeicolo, LocalDate dataSelezionata);

    @Query("SELECT p FROM Prenotazione p WHERE id_veicolo= ?1 ORDER BY dataInizio DESC, dataFine DESC")
    List<Prenotazione> selezionaPrenotazioniByCodiceMezzo(int idVeicolo);
}
