package control.comuni;

import com.google.gson.Gson;
import control.utili.PassowrdEncrypter;
import control.utili.SessionManager;
import model.dao.AulaDAO;
import model.dao.PrenotazioneDAO;
import model.dao.UtenteDAO;
import model.daostub.StubAulaDAO;
import model.daostub.StubEdificioDAO;
import model.daostub.StubPrenotazioneDAO;
import model.daostub.StubUtenteDAO;
import model.pojo.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.*;

public class PrelevaPrenotazioneServletTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse res;
    @Mock
    private ServletContext ctx;
    @Mock
    private HttpSession session;
    private UtenteDAO utenteDAO = new StubUtenteDAO();
    private PrenotazioneDAO prenotazioneDAO = new StubPrenotazioneDAO();
    private AulaDAO aulaDAO = new StubAulaDAO();
    private PrelevaPrenotazioneServlet servlet;
    private Map<String, Object> attributes = new HashMap<>();
    private StringWriter stringWriter;

    @BeforeEach
    void setUp() throws IOException {
        stringWriter = new StringWriter();
        MockitoAnnotations.initMocks(this);
        servlet = new PrelevaPrenotazioneServlet();
        when(req.getServletContext()).thenReturn(ctx);
        when(res.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(ctx.getAttribute(PrelevaPrenotazioneServlet.PRENOTAZIONE_DAO_PARAM)).thenReturn(prenotazioneDAO);
        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("");
        when(ctx.getContextPath()).thenReturn("");
        when(SessionManager.isAlradyAuthenticated(session)).thenReturn(true);
        doNothing().when(res).sendRedirect(anyString());

        Mockito.doAnswer((Answer<Object>) invocation -> {
            String key = (String) invocation.getArguments()[0];
            return attributes.get(key);
        }).when(session).getAttribute(anyString());

        Mockito.doAnswer((Answer<Object>) invocation -> {
            String key = (String) invocation.getArguments()[0];
            Object value = invocation.getArguments()[1];
            attributes.put(key, value);
            return null;
        }).when(session).setAttribute(anyString(), any());

        Utente u = new Utente("m.rossi12@studenti.unisa.it", "Mario", "Rossi",
                PassowrdEncrypter.criptaPassword("MarioRossi12"), TipoUtente.STUDENTE);
        Utente u1 = new Utente("a.decaro@studenti.unisa.it", "Antonio", "De Caro",
                PassowrdEncrypter.criptaPassword("Antonio2"), TipoUtente.STUDENTE);
        Utente u2 = new Utente("c.gravino@studenti.unisa.it", "Carmine", "Gravino",
                PassowrdEncrypter.criptaPassword("Gravino1"), TipoUtente.DOCENTE);
        utenteDAO.insert(u);
        utenteDAO.insert(u1);
        utenteDAO.insert(u2);
        Date d = new Date(Calendar.getInstance().getTime().getTime());
        Edificio ed = new StubEdificioDAO().retriveByName("F3");
        String dispP3 = Files.readString(Paths.get("./src/test/resources/TC_3/disp_aulaP3.json"));
        String dispP4 = Files.readString(Paths.get("./src/test/resources/TC_4/disp_aulaP1.json"));
        Aula aulaP3 = new Aula("P3", 70, 100, dispP3, ed);
        Aula aulaP4 = new Aula("P4", 0, 100, dispP4, ed);
        aulaP3.setId(1);
        aulaP4.setId(2);
        aulaDAO.insert(aulaP3);
        aulaDAO.insert(aulaP4);
        prenotazioneDAO.insert(new Prenotazione(1, d, new Time(14), new Time(16),
                TipoPrenotazione.POSTO, aulaP3, u1));
        prenotazioneDAO.insert(new Prenotazione(2, d, new Time(14), new Time(16),
                TipoPrenotazione.POSTO, aulaP4, u2));
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void testGetAndAuth() throws Exception {
        SessionManager.autentica(session, null);
        servlet.doGet(req, res);
        assertEquals("LogIn non effettuato",
                SessionManager.getError(session));
    }

//    @Test
//    void testExc() throws Exception {
//        SessionManager.autentica(session, utenteDAO.retriveAll().get(1));
//        Date d = Date.valueOf(LocalDate.now());
//        servlet.doGet(req, res);
//        assertEquals("La data "+d+" ancora deve avvenire",
//                SessionManager.getError(session));
//    }

    @Test
    void TC_1_1() throws Exception {
        Utente us =utenteDAO.retriveByEmail("m.rossi12@studenti.unisa.it");
        SessionManager.autentica(session, us);
        servlet.doGet(req, res);
        assertNull(SessionManager.getError(session));
    }

    @Test
    void TC_1_2() throws Exception {
        Utente us =utenteDAO.retriveByEmail("a.decaro@studenti.unisa.it");
        SessionManager.autentica(session, us);
        Gson gson = new Gson();
        servlet.doGet(req, res);
        assertEquals(gson.toJson(prenotazioneDAO.retriveByUtente(us).get(0)), stringWriter.toString());
    }

    @Test
    void TC_1_3() throws Exception {
        Utente us =utenteDAO.retriveByEmail("c.gravino@studenti.unisa.it");
        SessionManager.autentica(session, us);
        Gson gson = new Gson();
        servlet.doGet(req, res);
        assertEquals(gson.toJson(prenotazioneDAO.retriveByUtente(us)), stringWriter.toString());
    }


}
