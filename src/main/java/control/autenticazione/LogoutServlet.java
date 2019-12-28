package control.autenticazione;

import model.database.DBUtenteDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Questa servlet permette di effetuare il logout ad un utente
 * @author De Santis Marco
 * @version 0.1
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getSession().invalidate();
        response.sendRedirect(/*request.getServletContext().getContextPath() +*/ "/comuni/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    private static final long serialVersionUID = 1L;
}