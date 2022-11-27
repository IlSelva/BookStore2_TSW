package unisa.silviopastore.bookstore.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoreDAO {

	public List<Editore> doRetrieveAll() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT editore FROM Editori");
			ArrayList<Editore> editori = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Editore c = new Editore();
				c.setNome(rs.getString(1));
				editori.add(c);
			}
			return editori;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Editore doRetrieveByName(String editore){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT editore FROM Editori WHERE editore = ?");
			ps.setString(1, editore);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Editore c = new Editore();
				c.setNome(rs.getString(1));
				return c;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
