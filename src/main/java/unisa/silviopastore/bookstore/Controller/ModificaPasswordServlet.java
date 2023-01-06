package unisa.silviopastore.bookstore.Controller;

import unisa.silviopastore.bookstore.Model.Utente;
import unisa.silviopastore.bookstore.Model.UtenteDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ModificaPassword")
public class ModificaPasswordServlet extends HttpServlet {

  UtenteDAO utenteDAO = new UtenteDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String Address = "WEB-INF/jsp/modificaPassword.jsp";

    String idstr = request.getParameter("id");
    String notifica = null;
    if (idstr != null) {
      Address = "WEB-INF/jsp/Profilo.jsp";
      Utente u = (Utente) request.getSession().getAttribute("utente");
      int id = Integer.parseInt(idstr);
      if ((u == null) || (u.getId() != id)) {
        throw new MyServletException("Errore utente");
      }
      String password = request.getParameter("password");
      String newpassword = request.getParameter("nuovapassword");
      if ((password != null) && (newpassword != null) && (u.checkPassword(password))) { //modifica password
        u.setPassword(newpassword);
        utenteDAO.doUpdate(u);
        request.setAttribute("notifica", " password modificata con successo");
        request.getSession().setAttribute("utente", u);
      } else {
        request.setAttribute("notifica", "Errore, password incorretta");
      }
    }
    RequestDispatcher requestDispatcher = request.getRequestDispatcher(Address);
    requestDispatcher.forward(request, response);
  }
}
