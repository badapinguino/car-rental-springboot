package app.error.exception;

public class PrenotazioneNelPassatoException extends RuntimeException {
    public PrenotazioneNelPassatoException(){
        super("Non è possibile effettuare una prenotazione nel passato, inserire date future.");
    }
}
