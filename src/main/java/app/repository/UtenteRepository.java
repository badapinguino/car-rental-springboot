package app.repository;

import app.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UtenteRepository extends JpaRepository<Utente, Integer>{

    Utente findByCodiceFiscale(String codiceFiscale);

    Utente findById(int id);

    Utente save(Utente u);

    void delete(Utente u);

    @Query("UPDATE Utente SET immagine= ?2 WHERE id= ?1")
    void updateImageQuery(int idUtente, String immagineUrl);

}
