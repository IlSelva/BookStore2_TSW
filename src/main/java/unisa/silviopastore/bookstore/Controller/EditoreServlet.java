package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Editore;
import unisa.silviopastore.bookstore.Model.EditoreDAO;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;

@WebServlet("/Editore")
public class EditoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1516566616599210015L;
	private final EditoreDAO editoreDAO = new EditoreDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String edi = request.getParameter("id");
		Editore editore = editoreDAO.doRetrieveByName(edi);

		if (editore == null) {
			throw new MyServletException("Editore non trovato."); //MyServletException
		}
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		int pag = 1;
		String pagstr = request.getParameter("pag");
		if (pagstr != null)
			pag = Integer.parseInt(pagstr);
		request.setAttribute("pag", pag);

		int perpag = 10; // prodotti per pagina

		int totaleprodotti = prodottoDAO.countByEditore(editore.getNome());
		int npag = (totaleprodotti + perpag - 1) / perpag;
		request.setAttribute("npag", npag);

		List<Prodotto> prodotti = prodottoDAO.doRetrieveByEditore(editore.getNome(), (pag - 1) * perpag, perpag);

		request.setAttribute("prodotti",prodotti);
		request.setAttribute("editore", editore);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/editore.jsp");
		requestDispatcher.forward(request, response);
	}
}
