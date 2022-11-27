package unisa.silviopastore.bookstore.Model;

import java.util.Objects;

public class Autore {

	private int id;
	private String nome;
	private String cognome;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getCognome(){
		return cognome;
	}

	public void setCognome(String cognome){
		this.cognome = cognome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Autore autore = (Autore) o;
		return id == autore.id &&
				Objects.equals(nome, autore.nome) &&
				Objects.equals(cognome, autore.cognome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome, cognome);
	}

	@Override
	public String toString() {
		return "Autore{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cognome='" + cognome + '\'' +
				'}';
	}
}
