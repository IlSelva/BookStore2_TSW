package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class LoginDAO {

	public Login doRetrieveById(String id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT id, idUtente, token, time FROM login WHERE id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Login l = new Login();
				l.setId(rs.getString(1));
				l.setIdUtente(rs.getInt(2));
				l.setToken(rs.getString(3));
				l.setTime(rs.getTimestamp(4));
				return l;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Login login) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO login (id, idUtente, token, time) VALUES(?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
			String id = UUID.randomUUID().toString();
			ps.setString(1, id);
			ps.setInt(2, login.getIdUtente());
			ps.setString(3, login.getToken());
			ps.setTimestamp(4, login.getTime());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			login.setId(id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Login login) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE login SET idUtente=?, token=?, time=? WHERE id=?");
			ps.setInt(1, login.getIdUtente());
			ps.setString(2, login.getToken());
			ps.setTimestamp(3, login.getTime());
			ps.setString(4, login.getId());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("UPDATE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doDelete(String id) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM login WHERE id=?");
			ps.setString(1, id);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
