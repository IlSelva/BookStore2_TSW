package unisa.silviopastore.bookstore.Controller;

import unisa.silviopastore.bookstore.Model.LoginDAO;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -8463478464670004824L;
	private final LoginDAO loginDAO = new LoginDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("utente");

		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			// cookie con nome 'login' o null se non esiste
			Cookie cookie = Arrays.stream(cookies).filter(c -> c.getName().equals("login")).findAny().orElse(null);
			if (cookie != null) {
				cookie.setMaxAge(0); // rimuove cookie
				response.addCookie(cookie);
				String id = cookie.getValue().split("_")[0];
				loginDAO.doDelete(id);
			}
		}

		String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/Logout") || dest.contains("/Utente") || dest.contains("/Modifica") || dest.trim().isEmpty()) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}
}
