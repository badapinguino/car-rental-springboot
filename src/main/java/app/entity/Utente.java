package app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Utente")
public class Utente implements Serializable {
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

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;

    @NotNull
    @Size(min=lunghezzaCampoCodiceFiscale, max = lunghezzaCampoCodiceFiscale)
    @Column(name = "codice_fiscale", nullable = false, unique = true)
    private String codiceFiscale;

    @NotNull
    @Size(max = lunghezzaCampoCognome)
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @NotNull
    @Size(max = lunghezzaCampoNome)
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @NotNull
    @Column(name = "superuser", nullable = false)
    private boolean superuser;

    @NotNull
    @Column(name = "password_utente", nullable = false)
    private String password;

    @Size(max=256)
    @Column(name = "immagine")
    private String immagine;

    @NotNull
    @Column(name = "verificato")
    private boolean verificato;

    @NotNull
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "utente", cascade=CascadeType.REMOVE)
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
}
