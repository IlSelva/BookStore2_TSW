package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoreDAO {

	public List<Autore> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT  id, nome, cognome FROM Autore LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Autore> autori = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Autore a = new Autore();
				a.setId(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setCognome(rs.getString(3));
				autori.add(a);
			}
			return autori;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Autore doRetrieveById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, nome, cognome FROM Autore WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Autore a = new Autore();
				a.setId(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setCognome(rs.getString(3));
				return a;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Autore> doRetrieveByNomeCognome(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, nome, cognome FROM Autore WHERE MATCH(nome, cognome) AGAINST(?) LIMIT ?, ?");
			ps.setString(1, against);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Autore> autori = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Autore a = new Autore();
				a.setId(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setCognome(rs.getString(3));
				autori.add(a);
			}
			return autori;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Autore autore) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Autore (nome, cognome) VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, autore.getNome());
			ps.setString(1, autore.getCognome());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			autore.setId(id);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Autore autore) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE autore SET nome=?, cognome=? WHERE id=?");
			ps.setString(1, autore.getNome());
			ps.setString(2, autore.getNome());
			ps.setInt(3, autore.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doDelete(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM autore WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
