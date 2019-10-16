package app.DTO;

import app.entity.Prenotazione;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class UtenteDTO {
    private static final int lunghezzaCampoCognome = 80;
    private static final int lunghezzaCampoNome = 80;
    private static final int lunghezzaCampoCodiceFiscale = 16;

    public static int getLunghezzaCampoCodiceFiscale(){
        return lunghezzaCampoCodiceFiscale;
    }
    public static int getLunghezzaCampoCognome(){
        return lunghezzaCampoCognome;
    }
    public static int getLunghezzaCampoNome(){
        return lunghezzaCampoNome;
    }

    private int id;

    @Size(min=lunghezzaCampoCodiceFiscale, max = lunghezzaCampoCodiceFiscale)
    private String codiceFiscale;

    @Size(max = lunghezzaCampoCognome)
    private String cognome;

    @Size(max = lunghezzaCampoNome)
    private String nome;

    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd") //controllare se va bene così "dd/MM/yyyy"
    private LocalDate dataNascita;

    private boolean superuser;

    private String password;

    @Size(max=256)
    private String immagine;

    private Set<Prenotazione> prenotazioni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
