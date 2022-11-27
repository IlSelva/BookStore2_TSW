package unisa.silviopastore.bookstore.Model;

import java.util.Objects;

public class Editore {
	String nome;

	public String getNome(){
		return nome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Editore{" +
				"nome='" + nome + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Editore editore = (Editore) o;
		return Objects.equals(nome, editore.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
