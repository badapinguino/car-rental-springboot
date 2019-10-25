package app.error.exception;

public class VeicoloNonDisponibileDateSelezionateException extends RuntimeException{
    public VeicoloNonDisponibileDateSelezionateException(){
        super("Il veicolo scelto non è disponibile nelle date selezionate.");
    }
}
