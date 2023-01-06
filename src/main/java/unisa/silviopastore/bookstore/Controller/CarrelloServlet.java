package unisa.silviopastore.bookstore.Controller;

import unisa.silviopastore.bookstore.Model.Carrello;
import unisa.silviopastore.bookstore.Model.Carrello.ProdottoQuantita;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Carrello")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 3474223052444405325L;
	private final ProdottoDAO prodottoDAO = new ProdottoDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}

		String prodIdStr = request.getParameter("prodId");
		if (prodIdStr != null) {
			int prodId = Integer.parseInt(prodIdStr);

			String addNumStr = request.getParameter("addNum");
			if (addNumStr != null) {
				int addNum = Integer.parseInt(addNumStr);

				Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
				if (prodQuant != null) {
					prodQuant.setQuantita(prodQuant.getQuantita() + addNum);
				} else {
					carrello.put(prodottoDAO.doRetrieveById(prodId), addNum);
				}
			} else {
				String setNumStr = request.getParameter("setNum");
				if (setNumStr != null) {
					int setNum = Integer.parseInt(setNumStr);
					if (setNum <= 0) {
						carrello.remove(prodId);
					} else {
						ProdottoQuantita prodQuant = carrello.get(prodId);
						if (prodQuant != null) {
							prodQuant.setQuantita(setNum);
						} else {
							carrello.put(prodottoDAO.doRetrieveById(prodId), setNum);
						}
					}
				}
			}
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/carrello.jsp");
		requestDispatcher.forward(request, response);
	}
}
