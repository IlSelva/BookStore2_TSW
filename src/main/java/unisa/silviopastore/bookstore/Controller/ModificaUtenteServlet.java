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

@WebServlet("/ModificaUtente")
public class ModificaUtenteServlet extends HttpServlet {

  UtenteDAO utenteDAO = new UtenteDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String Address = "WEB-INF/jsp/modificaDati.jsp";

    String idstr = request.getParameter("id");
    if (idstr != null) {
      Address = "WEB-INF/jsp/profilo.jsp";
      Utente u = (Utente) request.getSession().getAttribute("utente");
      int id = Integer.parseInt(idstr);
      if ((u == null) || (u.getId() != id)) //si potrebbe anche controllare il login
      {
        throw new MyServletException("Errore utente");
      }
      String nome = request.getParameter("nome");
      String email = request.getParameter("email");
      if ((nome != null) && (email != null)) {    //modifica dati
        u.setNome(nome);
        u.setEmail(email);
        utenteDAO.doUpdate(u);
        request.getSession().setAttribute("utente", u);
        request.setAttribute("notifica", "<span style=\"color:green\"> Informazioni modificate con successo </span>");
      } else {
        request.setAttribute("notifica", "<span style=\"color:red\">informazioni insufficienti </span>"); //errore
      }
    }
    RequestDispatcher requestDispatcher = request.getRequestDispatcher(Address);
    requestDispatcher.forward(request, response);
  }
}