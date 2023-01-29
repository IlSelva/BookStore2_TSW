package unisa.silviopastore.bookstore.Controller;

import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import org.apache.commons.io.FilenameUtils;
import unisa.silviopastore.bookstore.Model.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadProdotto")
@MultipartConfig()
public class UploadProdotto extends HttpServlet {

  private static final Logger LOGGER = Logger.getLogger("UploadProdotto");
  private static final String CARTELLA_UPLOAD = "C:\\Users\\e-silvio.pastore\\Pictures\\tsw\\img\\prodotti";
  private final AutoreDAO autoreDAO = new AutoreDAO();
  private final ProdottoDAO prodottoDAO = new ProdottoDAO();
  private final GenereDAO genereDAO = new GenereDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Utente utente = (Utente) request.getSession().getAttribute("utente"); // prendo l'utente dalla sessione
    if (utente == null || !utente.isAdmin()) //controllo che l'utente sia amministratore
    {
      throw new MyServletException();
    }

    String titolo = request.getParameter("titolo");
    String autore = request.getParameter("autore");
    String editore = request.getParameter("editore");
    String genere = request.getParameter("genere");
    String descrizione = request.getParameter("descrizione");
    String ncopie = request.getParameter("copie");
    String prezzo = request.getParameter("prezzo");
    if ((titolo != null) && (autore != null) && (editore != null) && (genere != null) && (descrizione != null) &&
          (ncopie != null) && (prezzo != null)) {
      Prodotto prodotto = new Prodotto();
      prodotto.setTitolo(titolo);
      prodotto.setAutore(autoreDAO.doRetrieveById(Integer.parseInt(autore)));
      prodotto.setEditore(editore);
      prodotto.setGenere(genere);
      prodotto.setDescrizione(descrizione);
      prodotto.setCopie(Integer.parseInt(ncopie));
      prodotto.setPrezzoCent(Long.parseLong(prezzo));
      prodottoDAO.doSave(prodotto);

      Part filePart = request.getPart("file");
      String fileName = Integer.toString(prodotto.getId());

      String destinazione = CARTELLA_UPLOAD + "\\" + fileName + "." + FilenameUtils.getExtension(filePart.getSubmittedFileName());
      Path pathDestinazione = Paths.get(destinazione);

      InputStream fileInputStream = filePart.getInputStream();
      // crea CARTELLA_UPLOAD, se non esiste
      Files.createDirectories(pathDestinazione.getParent());
      // scrive il file
      Files.copy(fileInputStream, pathDestinazione);

      request.setAttribute("notifica", "<span style=\"color:green\"> Upload effettuato con successo </span>");
    } else {
      throw new MyServletException("Errore durante l'invio dei dati");
    }
    ArrayList<Autore> autori = (ArrayList<Autore>) autoreDAO.doRetrieveAll(0, 20);
    request.setAttribute("autori", autori);
    ArrayList<Genere> generi = (ArrayList<Genere>) genereDAO.doRetrieveAll();
    request.setAttribute("generi", generi);

    RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/jsp/UploadProdotto.jsp");
    requestDispatcher.forward(request, response);
  }
}