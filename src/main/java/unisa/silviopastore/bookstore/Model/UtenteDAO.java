package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

	public List<Utente> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT id, passwordhash, nome, email, admin FROM Cliente LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			ArrayList<Utente> utenti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utente u = new Utente();
				u.setId(rs.getInt(1));
				u.setPasswordhash(rs.getString(2));
				u.setNome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setAdmin(rs.getBoolean(5));
				utenti.add(u);
			}
			return utenti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Utente doRetrieveById(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, passwordhash, nome, email, admin FROM Cliente WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente p = new Utente();
				p.setId(rs.getInt(1));
				p.setPasswordhash(rs.getString(2));
				p.setNome(rs.getString(3));
				p.setEmail(rs.getString(4));
				p.setAdmin(rs.getBoolean(5));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Utente doRetrieveByEmailPassword(String username, String password) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, passwordhash, nome, email, admin FROM Cliente WHERE email=? AND passwordhash=SHA1(?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente p = new Utente();
				p.setId(rs.getInt(1));
				p.setPasswordhash(rs.getString(2));
				p.setNome(rs.getString(3));
				p.setEmail(rs.getString(4));
				p.setAdmin(rs.getBoolean(5));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Utente doRetrieveByEmail(String email) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT id, passwordhash, nome, email, admin FROM Cliente WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Utente p = new Utente();
				p.setId(rs.getInt(1));
				p.setPasswordhash(rs.getString(2));
				p.setNome(rs.getString(3));
				p.setEmail(rs.getString(4));
				p.setAdmin(rs.getBoolean(5));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Utente utente) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO Cliente (passwordhash, nome, email, admin) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, utente.getPasswordhash());
			ps.setString(2, utente.getNome());
			ps.setString(3, utente.getEmail());
			ps.setBoolean(4, utente.isAdmin());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			utente.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Utente utente) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("UPDATE Cliente SET nome=?, id=?, email=?, passwordhash=?, admin=? WHERE id=?");
			ps.setString(1, utente.getNome());
			ps.setInt(2, utente.getId());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getPasswordhash());
			ps.setBoolean(5, utente.isAdmin());
			ps.setInt(6, utente.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void doDelete(int id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM Cliente WHERE id=?");
			ps.setInt(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
