package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

	AutoreDAO service = new AutoreDAO();

	public List<Prodotto> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT  id,titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveLast(int offset, int limit){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT  id,titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro ORDER BY id desc LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Prodotto doRetrieveById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int countByGenere(String genere) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) FROM Libro WHERE genere = ?");
			ps.setString(1, genere);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int countByAutore(int autore){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) FROM Libro WHERE Autore = ?");
			ps.setInt(1, autore);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int countByEditore(String editore){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) FROM Libro WHERE Editore = ?");
			ps.setString(1, editore);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);


		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int countAll(){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Libro");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Prodotto> doRetrieveByGenere(String genere, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE genere = ? LIMIT ?, ?");
			ps.setString(1, genere);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveByAutore(int autoreid, int offset, int limit){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT id, titolo, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE autore = ? LIMIT ?, ?");
			ps.setInt(1, autoreid);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			Autore autore = service.doRetrieveById(autoreid);
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(autore);
				p.setEditore(rs.getString(3));
				p.setGenere(rs.getString(4));
				p.setDescrizione(rs.getString(5));
				p.setcopie(rs.getInt(6));
				p.setPrezzoCent(rs.getLong(7));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveByEditore(String editore, int offset, int limit){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE editore = ? LIMIT ?, ?");
			ps.setString(1, editore);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveByNome(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE MATCH(titolo) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setPrezzoCent(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveByNomeOrDescrizione(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE MATCH(titolo, descrizione, editore) AGAINST(? IN BOOLEAN MODE) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Prodotto> doRetrieveByNomeOrDescrizioneAndGenere(String against, String categoria, int offset, int limit){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent FROM Libro WHERE genere=? AND MATCH(titolo, descrizione, editore) AGAINST(? IN BOOLEAN MODE ) LIMIT ?, ?");
			ps.setString(1,categoria);
			ps.setString(2, against);
			ps.setInt(3, offset);
			ps.setInt(4, limit);
			ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setTitolo(rs.getString(2));
				p.setAutore(service.doRetrieveById(rs.getInt(3)));
				p.setEditore(rs.getString(4));
				p.setGenere(rs.getString(5));
				p.setDescrizione(rs.getString(6));
				p.setcopie(rs.getInt(7));
				p.setPrezzoCent(rs.getLong(8));
				prodotti.add(p);
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Prodotto prodotto) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Libro (titolo, autore, editore, genere, descrizione, ncopiedisp, prezzocent) VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, prodotto.getTitolo());
			ps.setInt(2, prodotto.getAutore().getId());
			ps.setString(3, prodotto.getEditore());
			ps.setString(4,prodotto.getGenere());
			ps.setString(5,prodotto.getDescrizione());
			ps.setInt(6,prodotto.getcopie());
			ps.setLong(7,prodotto.getPrezzoCent());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			prodotto.setId(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Prodotto prodotto) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Libro SET titolo=?, autore=?, editore=?, genere=?, descrizione=?, ncopiedisp=?, prezzocent=? WHERE id=?");
			ps.setString(1, prodotto.getTitolo());
			ps.setInt(2, prodotto.getAutore().getId());
			ps.setString(3, prodotto.getEditore());
			ps.setString(4, prodotto.getGenere());
			ps.setString(5, prodotto.getDescrizione());
			ps.setInt(6, prodotto.getcopie());
			ps.setLong(7, prodotto.getPrezzoCent());
			ps.setInt(8, prodotto.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doDelete(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Libro WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
