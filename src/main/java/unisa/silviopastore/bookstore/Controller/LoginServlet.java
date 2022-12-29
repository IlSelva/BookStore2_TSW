package unisa.silviopastore.bookstore.Controller;

import java.io.Console;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import unisa.silviopastore.bookstore.Model.Login;
import unisa.silviopastore.bookstore.Model.LoginDAO;
import unisa.silviopastore.bookstore.Model.Utente;
import unisa.silviopastore.bookstore.Model.UtenteDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 422938517959400504L;
	private final UtenteDAO utenteDAO = new UtenteDAO();
	private final LoginDAO loginDAO = new LoginDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Utente utente = null;
		if (email != null && password != null) {
			utente = utenteDAO.doRetrieveByEmailPassword(email, password);
		}

		if (utente == null) {
			throw new MyServletException("email e/o password non validi.");
		}

		Login login = new Login();
		login.setIdUtente(utente.getId());
		login.setToken(UUID.randomUUID().toString());
		login.setTime(Timestamp.from(Instant.now()));

		loginDAO.doSave(login);

		Cookie cookie = new Cookie("login", login.getId() + "_" + login.getToken());
		cookie.setMaxAge(30 * 24 * 60 * 60); // 30 giorni
		response.addCookie(cookie);

		request.getSession().setAttribute("utente", utente);

		String dest = request.getHeader("referer");
		if ( (dest == null || dest.contains("/Login") || dest.contains("/Registrazione") || dest.trim().isEmpty()) ) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}
}
