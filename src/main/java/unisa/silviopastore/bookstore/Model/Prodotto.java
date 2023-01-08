package unisa.silviopastore.bookstore.Model;

import java.util.Objects;

public class Prodotto {

	private int id;
	private String titolo;
	private Autore autore;
	private String editore;
	private String genere;
	private String descrizione;
	private int copie;
	private long prezzoCent;

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTitolo(){
		return this.titolo;
	}

	public void setTitolo(String titolo){
		this.titolo = titolo;
	}

	public Autore getAutore(){
		return this.autore;
	}

	public void setAutore(Autore autore){
		this.autore = autore;
	}

	public String getEditore(){
		return this.editore;
	}

	public void setEditore(String editore){
		this.editore = editore;
	}

	public String getGenere(){
		return this.genere;
	}

	public void setGenere(String genere){
		this.genere = genere;
	}

	public String getDescrizione(){
		return this.descrizione;
	}

	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}

	public int getCopie(){
		return this.copie;
	}

	public void setCopie(int copie){
		this.copie = copie;
	}

	public long getPrezzoCent(){
		return prezzoCent;
	}

	public void setPrezzoCent(long prezzoCent){
		this.prezzoCent = prezzoCent;
	}

	public String getPrezzoEuro() {
		return String.format("%.2f", prezzoCent / 100.);
	}

	@Override
	public String toString() {
		return "Prodotto{" +
				"id=" + id +
				", titolo='" + titolo + '\'' +
				", autore=" + autore +
				", editore='" + editore + '\'' +
				", genere='" + genere + '\'' +
				", descrizione='" + descrizione + '\'' +
				", copie=" + copie +
				", prezzoCent=" + prezzoCent +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Prodotto prodotto = (Prodotto) o;
		return id == prodotto.id &&
				autore == prodotto.autore &&
				copie == prodotto.copie &&
				prezzoCent == prodotto.prezzoCent &&
				Objects.equals(titolo, prodotto.titolo) &&
				Objects.equals(editore, prodotto.editore) &&
				Objects.equals(genere, prodotto.genere) &&
				Objects.equals(descrizione, prodotto.descrizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, titolo, autore, editore, genere, descrizione, copie, prezzoCent);
	}
}
