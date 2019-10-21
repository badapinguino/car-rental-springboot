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
}
