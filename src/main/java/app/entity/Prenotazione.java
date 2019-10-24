package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Prenotazione")
public class Prenotazione implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    //mettere anche controllo @Future? in teoria un domani quel futuro potrebbe essere passato, in quel caso come si comporterebbe?
    @Column(name="data_inizio", nullable = false)
    private LocalDate dataInizio;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="data_fine", nullable = false)
    private LocalDate dataFine;

    @NotNull
    @Column(name="approvata", nullable = false)
    private boolean approvata;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_veicolo", nullable = false)
    private Veicolo veicolo;

    @NotNull
    @ManyToOne
    @JoinColumn(name="id_utente", nullable = false)
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

    public boolean isApprovata() {
        return approvata;
    }

    public void setApprovata(boolean approvata) {
        this.approvata = approvata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prenotazione)) return false;
        Prenotazione that = (Prenotazione) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
