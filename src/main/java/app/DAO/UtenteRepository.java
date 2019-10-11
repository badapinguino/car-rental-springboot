package app.DAO;

import app.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer>{

    Utente findByCodiceFiscale(String codiceFiscale);
}
