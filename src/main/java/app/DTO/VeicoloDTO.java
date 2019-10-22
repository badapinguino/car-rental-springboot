package app.DTO;

import app.entity.Prenotazione;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

public class VeicoloDTO {
    private static final int lunghezzaCampoCodiceMezzo = 15;
    private static final int lunghezzaCampoTarga = 10;
    private static final int lunghezzaCampoCasaCostruttrice = 80;
    private static final int lunghezzaCampoModello = 80;
    private static final int lunghezzaCampoTipologia = 20;

    private int id;

    @Size(max = lunghezzaCampoCodiceMezzo)
    private String codiceMezzo;

    @Size(max = lunghezzaCampoTarga)
    private String targa;

    @Size(max = lunghezzaCampoModello)
    private String modello;

    @Size(max = lunghezzaCampoCasaCostruttrice)
    private String casaCostruttrice;

    @Min(value = 1900)
    private int anno;

    @Size(max = lunghezzaCampoTipologia)
    private String tipologia;

    @Positive
    private float prezzoGiornata;

    private Set<Prenotazione> prenotazioni;

    public static int getLunghezzaCampoCodiceMezzo() {
        return lunghezzaCampoCodiceMezzo;
    }

    public static int getLunghezzaCampoTarga() {
        return lunghezzaCampoTarga;
    }

    public static int getLunghezzaCampoCasaCostruttrice() {
        return lunghezzaCampoCasaCostruttrice;
    }

    public static int getLunghezzaCampoModello() {
        return lunghezzaCampoModello;
    }

    public static int getLunghezzaCampoTipologia() {
        return lunghezzaCampoTipologia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodiceMezzo() {
        return codiceMezzo;
    }

    public void setCodiceMezzo(String codiceMezzo) {
        this.codiceMezzo = codiceMezzo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getCasaCostruttrice() {
        return casaCostruttrice;
    }

    public void setCasaCostruttrice(String casaCostruttrice) {
        this.casaCostruttrice = casaCostruttrice;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public float getPrezzoGiornata() {
        return prezzoGiornata;
    }

    public void setPrezzoGiornata(float prezzoGiornata) {
        this.prezzoGiornata = prezzoGiornata;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }
}
