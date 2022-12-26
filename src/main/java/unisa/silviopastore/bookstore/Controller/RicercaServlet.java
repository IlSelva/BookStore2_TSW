package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;

@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = -8860646938571706098L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dest;
		String genere = request.getParameter("category");
		String query = request.getParameter("q");

		int pag = 1;
		String pagstr = request.getParameter("pag");
		if (pagstr != null) {
			pag = Integer.parseInt(pagstr);
		}
		request.setAttribute("pag", pag);

		int perpag = 10; // prodotti per pagina
		int totaleprodotti=0;

		dest = "WEB-INF/jsp/ricerca.jsp";

		//tutti i prodotti
		if( ((genere == null)||(genere.compareTo("") == 0)) && ((query == null)||(query.compareTo("") == 0)) ) {
			totaleprodotti = prodottoDAO.countAll();
			List<Prodotto> prodotti = prodottoDAO.doRetrieveAll((pag - 1) * perpag, perpag);
			request.setAttribute("prodotti", prodotti);
		}
		//ricerca per genere
		else if((query == null)||((query.compareTo("") == 0))) {
			dest = "/Genere?id="+genere;
		}
		//ricerca per titolo
		else if( (genere == null) || (genere.compareTo("") == 0) ){
			List<Prodotto> prodotti = prodottoDAO.doRetrieveByNomeOrDescrizione(query, (pag - 1) * perpag, perpag);
			totaleprodotti = prodotti.size();
			request.setAttribute("prodotti", prodotti);
		}
		//tutti i prodotti
		else{
			List<Prodotto> prodotti = prodottoDAO.doRetrieveByNomeOrDescrizioneAndGenere(query, genere, (pag - 1) * perpag, perpag);
			totaleprodotti = prodotti.size();
			request.setAttribute("prodotti", prodotti);
		}

		int npag = (totaleprodotti + perpag - 1) / perpag;
		request.setAttribute("npag", npag);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(dest);
		requestDispatcher.forward(request, response);
	}
}
