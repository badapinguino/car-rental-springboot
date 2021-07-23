package app.DTO;

import app.entity.Prenotazione;
import app.entity.Utente;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class UtenteDTO {

    public UtenteDTO() {}

    public UtenteDTO(Utente utente){
        this.setCodiceFiscale(utente.getCodiceFiscale());
        this.setCognome(utente.getCognome());
        this.setNome(utente.getNome());
        this.setDataNascita(utente.getDataNascita());
        this.setEmail(utente.getEmail());
        this.setId(utente.getId());
        this.setSuperuser(utente.isSuperuser());
        this.setImmagine(utente.getImmagine());
        this.setVerificato(utente.isVerificato());
        this.setPassword(utente.getPassword());
        this.setPrenotazioni(utente.getPrenotazioni());
    }

    private int id;

    private String codiceFiscale;

    private String cognome;

    private String nome;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataNascita;

    private boolean superuser;

    private String password;

    private String vecchiaPassword;

    @Size(max=256)
    private String immagine;

    private boolean verificato;

    private String email;

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

    public boolean isVerificato() {
        return verificato;
    }

    public void setVerificato(boolean verificato) {
        this.verificato = verificato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public String getVecchiaPassword() {
        return vecchiaPassword;
    }

    public void setVecchiaPassword(String vecchiaPassword) {
        this.vecchiaPassword = vecchiaPassword;
    }
}
