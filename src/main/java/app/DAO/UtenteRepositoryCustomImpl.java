package app.DAO;


import app.entity.Utente;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional() // readOnly = true
public class UtenteRepositoryCustomImpl implements UtenteRepositoryCustom{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Utente findByCodiceFiscale(String codiceFiscale) {
        Query query = entityManager.createNativeQuery("SELECT ut.* FROM utente as ut " +
                "WHERE ut.codiceFiscale = ?", Utente.class);
        query.setParameter(1, codiceFiscale);
        return (Utente) query.getSingleResult();
    }
}
