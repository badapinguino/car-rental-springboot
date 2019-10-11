package app.DAO;

import app.entity.Utente;

public interface UtenteRepositoryCustom {
    Utente findByCodiceFiscale(String codiceFiscale);
}
