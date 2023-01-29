package unisa.silviopastore.bookstore.Controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Autore;
import unisa.silviopastore.bookstore.Model.AutoreDAO;
import unisa.silviopastore.bookstore.Model.Genere;
import unisa.silviopastore.bookstore.Model.GenereDAO;

@WebServlet("/UploadProdottoForm")
public class UploadProdottoForm extends HttpServlet {
  private final GenereDAO genereDAO = new GenereDAO();
  private final AutoreDAO autoreDAO = new AutoreDAO();
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    ArrayList<Autore> autori = (ArrayList<Autore>) autoreDAO.doRetrieveAll(0, 20);
    request.setAttribute("autori", autori);
    ArrayList<Genere> generi = (ArrayList<Genere>) genereDAO.doRetrieveAll();
    request.setAttribute("generi", generi);

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/UploadProdotto.jsp");
    requestDispatcher.forward(request, response);
  }

}
