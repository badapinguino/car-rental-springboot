package app.DTO;

import app.entity.Utente;
import app.entity.Veicolo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PrenotazioneDTO {

    private int id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInizio;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataFine;

    private boolean approvata;

    private Veicolo veicolo;
    private Utente utente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public boolean isApprovata() {
        return approvata;
    }

    public void setApprovata(boolean approvata) {
        this.approvata = approvata;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
