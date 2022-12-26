package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Genere;
import unisa.silviopastore.bookstore.Model.GenereDAO;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;

@WebServlet("/Genere")
public class GenereServlet extends HttpServlet {
	private static final long serialVersionUID = 8639257287854851213L;
	private final GenereDAO genereDAO = new GenereDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String gen = request.getParameter("id");
		Genere genere = genereDAO.doRetrieveByName(gen);

		if (genere == null) {
			throw new MyServletException("Genere non trovato."); //MyServletException
		}
		ProdottoDAO prodottoDAO = new ProdottoDAO();

		int pag = 1;
		String pagstr = request.getParameter("pag");
		if (pagstr != null)
			pag = Integer.parseInt(pagstr);
		request.setAttribute("pag", pag);

		int perpag = 10; // prodotti per pagina

		int totaleprodotti = prodottoDAO.countByGenere(genere.getNome());
		int npag = (totaleprodotti + perpag - 1) / perpag;
		request.setAttribute("npag", npag);

		List<Prodotto> prodotti = prodottoDAO.doRetrieveByGenere(genere.getNome(), (pag - 1) * perpag, perpag);

		request.setAttribute("prodotti",prodotti);
		request.setAttribute("genere", genere);


		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/genere.jsp");
		requestDispatcher.forward(request, response);
	}
}
