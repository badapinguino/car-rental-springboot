package app.error.exception;

public class ModificaPrenotazionePrimaNGiorniException extends RuntimeException {
    public ModificaPrenotazionePrimaNGiorniException(int giorni){
        super("Non è possibile modificare o eliminare prenotazioni a meno di " +
                giorni + " giorni dalla data di inizio prenotazione");
    }
}
