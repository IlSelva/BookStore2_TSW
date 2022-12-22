package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;

@WebServlet("/Prodotto")
public class ProdottoServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final ProdottoDAO prodottoDAO = new ProdottoDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {
      int id = Integer.parseInt(request.getParameter("id"));
      Prodotto prodotto = prodottoDAO.doRetrieveById(id);

      if (prodotto == null) {
        throw new MyServletException("Prodotto non trovato.");
      }
      request.setAttribute("prodotto", prodotto);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/prodotto.jsp");
      requestDispatcher.forward(request, response);

    } catch (NumberFormatException nfe) {
      throw new MyServletException("l'id deve essere un numero");
    }
  }
}
