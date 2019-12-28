package control.studente;

import control.utili.SessionManager;
import model.dao.AulaDAO;
import model.dao.EdificioDAO;
import model.dao.PrenotazioneDAO;
import model.database.StubAulaDAO;
import model.database.StubEdificioDAO;
import model.database.StubPrenotazioneDAO;
import model.pojo.TipoUtente;
import model.pojo.Utente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PrenotaPostoServletBlackBoxTest {

    @Mock private HttpServletRequest req;
    @Mock private HttpServletResponse res;
    @Mock private ServletContext ctx;
    @Mock private HttpSession session;
    private static Utente utente;
    private AulaDAO aulaDAO = new StubAulaDAO();
    private PrenotazioneDAO prenotazioneDAO = new StubPrenotazioneDAO();
    private EdificioDAO edificioDAO = new StubEdificioDAO();
    private PrenotaPostoServlet servlet;
    private Map<String,Object> attributes = new HashMap<>();

    @BeforeAll
    static void init(){
        utente = new Utente();
        utente.setTipoUtente(TipoUtente.STUDENTE);
    }

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        servlet = new PrenotaPostoServlet();
        when(req.getServletContext()).thenReturn(ctx);
        when(ctx.getAttribute(PrenotaPostoServlet.AULA_DAO)).thenReturn(aulaDAO);
        when(ctx.getAttribute(PrenotaPostoServlet.EDIFICIO_DAO)).thenReturn(edificioDAO);
        when(ctx.getAttribute(PrenotaPostoServlet.PRENOTAZIONE_DAO)).thenReturn(prenotazioneDAO);
        when(req.getSession()).thenReturn(session);
        when(req.getContextPath()).thenReturn("");
        when(ctx.getContextPath()).thenReturn("");
        when(session.isNew()).thenReturn(false);
        doNothing().when(res).sendRedirect(anyString());

        Mockito.doAnswer((Answer<Object>) invocation -> {
            try {
                String key = (String) invocation.getArguments()[0];
                return attributes.get(key);
            } catch (NullPointerException e) {
                return null;
            }
        }).when(session).getAttribute(anyString());

        Mockito.doAnswer((Answer<Object>) invocation -> {
            String key = (String) invocation.getArguments()[0];
            Object value = invocation.getArguments()[1];
            attributes.put(key, value);
            return null;
        }).when(session).setAttribute(anyString(), any());

        SessionManager.setError(session, "");
        SessionManager.autentica(session, utente);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void TC_3_1() throws Exception {
        when(req.getParameter("edificio")).thenReturn("");
        when(req.getParameter("aula")).thenReturn("3");
        when(req.getParameter("durata")).thenReturn("60");
        servlet.doPost(req, res);
        assertEquals("Edificio non valido", SessionManager.getError(session));
    }

    @Test
    void TC_3_2() throws Exception {
        when(req.getParameter("edificio")).thenReturn("F3");
        when(req.getParameter("aula")).thenReturn("");
        when(req.getParameter("durata")).thenReturn("60");
        servlet.doPost(req, res);
        assertEquals("Aula non valida", SessionManager.getError(session));
    }

    @Test
    void TC_3_3() throws Exception {
        when(req.getParameter("edificio")).thenReturn("F3");
        when(req.getParameter("aula")).thenReturn("2");
        when(req.getParameter("durata")).thenReturn("60");
        servlet.doPost(req, res);
        assertEquals("Aula non disponibile", SessionManager.getError(session));
    }

    @Test
    void TC_3_4() throws Exception {
        when(req.getParameter("edificio")).thenReturn("F3");
        when(req.getParameter("aula")).thenReturn("1");
        when(req.getParameter("durata")).thenReturn("");
        servlet.doPost(req, res);
        assertEquals("Durata non valida", SessionManager.getError(session));
    }

    @Test
    void TC_3_5() throws Exception {
        when(req.getParameter("edificio")).thenReturn("F3");
        when(req.getParameter("aula")).thenReturn("1");
        when(req.getParameter("durata")).thenReturn("60");
        servlet.doPost(req, res);
        assertEquals("", SessionManager.getError(session));
        assertEquals(1, prenotazioneDAO.retriveAll().size());
    }
}