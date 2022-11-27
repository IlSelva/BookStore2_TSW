package unisa.silviopastore.bookstore.Model;

import java.util.Objects;

public class Genere {
	String nome;

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Genere{" +
				"nome='" + nome + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Genere genere = (Genere) o;
		return Objects.equals(nome, genere.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

}
