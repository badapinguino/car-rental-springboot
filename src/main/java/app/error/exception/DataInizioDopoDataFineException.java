package app.error.exception;

public class DataInizioDopoDataFineException extends RuntimeException {
    public DataInizioDopoDataFineException(){
        super("La data di inizio prenotazione inserita è successiva alla data di fine prenotazione.");
    }
}
