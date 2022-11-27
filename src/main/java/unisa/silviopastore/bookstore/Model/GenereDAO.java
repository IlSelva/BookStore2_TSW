package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenereDAO {

	public List<Genere> doRetrieveAll() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT genere FROM Generi");
			ArrayList<Genere> generi = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Genere c = new Genere();
				c.setNome(rs.getString(1));
				generi.add(c);
			}
			return generi;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Genere doRetrieveByName(String genere){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT genere FROM Generi WHERE genere = ?");
			ps.setString(1, genere);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Genere c = new Genere();
				c.setNome(rs.getString(1));
				return c;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
