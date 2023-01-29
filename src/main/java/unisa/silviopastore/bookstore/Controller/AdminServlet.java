package unisa.silviopastore.bookstore.Controller;

import unisa.silviopastore.bookstore.Model.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminProdotto")
public class AdminServlet extends HttpServlet {

  private final ProdottoDAO prodottoDAO = new ProdottoDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
    if (utente == null || !utente.isAdmin()) //controllo che l'utente sia amministratore
    {
      throw new MyServletException();
    }

    String idstr = request.getParameter("id");
    if (idstr != null) {
      int id = Integer.parseInt(idstr);

      if (request.getParameter("rimuovi") != null) {  //rimozione
        prodottoDAO.doDelete(id);
        request.setAttribute("notifica", "<span style=\"color:green\"> Prodotto RIMOSSO con successo </span>");
      } else {
        Prodotto prodotto = prodottoDAO.doRetrieveById(id);

        String titolo = request.getParameter("titolo");
        String descrizione = request.getParameter("descrizione");
        String copie = request.getParameter("copie");
        String prezzoCent = request.getParameter("prezzoCent");
        if ((titolo != null) && (descrizione != null) && (copie != null) && (prezzoCent != null)) { //modifica
          prodotto.setTitolo(titolo);
          prodotto.setDescrizione(descrizione);
          int t = Integer.parseInt(copie);
          if (t >= 0) {
            prodotto.setCopie(Integer.parseInt(copie));
          }
          prodotto.setPrezzoCent(Long.parseLong(prezzoCent));
          prodottoDAO.doUpdate(prodotto);
          request.setAttribute("notifica", "<span style=\"color:green\"> Prodotto modificato con successo </span>");
        }
        request.setAttribute("prodotto", prodotto);
      }
    } else {    //nuovo prodotto
      Prodotto prodotto = null;

      String titolo = request.getParameter("titolo");
      String descrizione = request.getParameter("descrizione");
      String copie = request.getParameter("copie");
      String prezzoCent = request.getParameter("prezzoCent");
      String autore = request.getParameter("autore");
      String editore = request.getParameter("editore");
      String genere = request.getParameter("genere");
      if ((titolo != null) && (descrizione != null) && (copie != null) && (prezzoCent != null) && (autore != null) &&
            (editore != null) && (genere != null)) {
        AutoreDAO autoredao = new AutoreDAO();
        prodotto = new Prodotto();
        prodotto.setTitolo(titolo);
        Autore a = autoredao.doRetrieveById(Integer.parseInt(autore));
        prodotto.setAutore(a);
        prodotto.setEditore(editore);
        prodotto.setDescrizione(descrizione);
        prodotto.setCopie(Integer.parseInt(copie));
        prodotto.setPrezzoCent(Long.parseLong(prezzoCent));

        prodottoDAO.doSave(prodotto);
        request.setAttribute("notifica", "<span style=\"color:green\"> Prodotto aggiunto con successo </span>");
      } else {
        request.setAttribute("notifica", "<span style=\"color:red\"> Compilare tutti i campi </span>"); //errore
      }
    }
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminProdotto.jsp");
    requestDispatcher.forward(request, response);
  }
}
