package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;

@WebServlet("/RicercaAjax")
public class RicercaAjaxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JSONArray prodJson = new JSONArray();
        String query = request.getParameter("q");
        if (query != null) {
            query += "*";
            List<Prodotto> prodotti = prodottoDAO.doRetrieveByNome(query, 0, 5);
            for (Prodotto p : prodotti) {
                prodJson.put(p.getTitolo());
            }
        }
        response.setContentType("application/json");
        response.getWriter().append(prodJson.toString());
    }
}
