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

@WebServlet(name = "HomeServlet", urlPatterns="", loadOnStartup=1)
public class HomeServlet extends HttpServlet {

	ProdottoDAO serviceProd = new ProdottoDAO();

	@Override
	public void init() throws ServletException {

		GenereDAO serviceGen = new GenereDAO();
		List<Genere> generi = serviceGen.doRetrieveAll();
		getServletContext().setAttribute("generi", generi);
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Prodotto> prodotti = serviceProd.doRetrieveAll(0, 5);
		List<Prodotto> ultimi = serviceProd.doRetrieveLast(0,5);
		request.setAttribute("prodotti", prodotti);
		request.setAttribute("ultimi",ultimi);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		requestDispatcher.forward(request, response);
	}

}