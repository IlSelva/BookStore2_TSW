package unisa.silviopastore.bookstore.Controller;

import java.util.Iterator;
import java.util.ListIterator;
import unisa.silviopastore.bookstore.Model.Carrello;
import unisa.silviopastore.bookstore.Model.Carrello.ProdottoQuantita;
import unisa.silviopastore.bookstore.Model.Prodotto;
import unisa.silviopastore.bookstore.Model.ProdottoDAO;
import unisa.silviopastore.bookstore.Model.Utente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AcquistoCompletato")
public class AcquistoCompletatoServlet extends HttpServlet {

  private final ProdottoDAO prodottoDAO = new ProdottoDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    Carrello carrello = (Carrello) session.getAttribute("carrello");
    Utente utente = (Utente) session.getAttribute("utente");

    if (utente == null) {
      throw new MyServletException("Effettuare il login");
    }

    Iterator<ProdottoQuantita> iterator = carrello.getProdotti().iterator();
    while(iterator.hasNext()){
      ProdottoQuantita pq = iterator.next();
      Prodotto p = prodottoDAO.doRetrieveById(pq.getProdotto().getId()); // prendo il prodotto dal carrello
      p.setCopie(p.getCopie() - pq.getQuantita()); // aggiungere eccezione se pq.quantita > p.getcopie
      /*
      if(p.getcopie() <0)
        throw new MyServletException("prodotti insufficienti");
       */
      prodottoDAO.doUpdate(p);
      iterator.remove();
    }


    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/acquisto.jsp");
    requestDispatcher.forward(request, response);
  }
}
