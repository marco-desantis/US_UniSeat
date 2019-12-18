package model.database;

import model.dao.EdificioDAO;
import model.pojo.Edificio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe implementa l'interfaccia EdificioDAO, utilizzando come gestore della persistenza il DataBase
 * @author De Caro Antonio
 * @version 0.1
 * @see EdificioDAO
 * */
public class DBEdificioDAO implements EdificioDAO {
    private static Logger logger = Logger.getLogger(DBAulaDAO.class.getName());
    private static DBEdificioDAO dao;

    /**
     * Ritorna un oggetto singleton di tipo DBEdificioDAO.
     *
     * @return l'oggetto DBAulaDAO che accede agli oggetti Edificio persistenti
     * @since 0.1
     * */
    public static EdificioDAO getInstance(){
        if (dao == null){
            try {
                dao = new DBEdificioDAO(DBConnection.getInstance());
            } catch (SQLException e){
                logger.log(Level.SEVERE, "{0}", e);
            }
        }
        return dao;
    }

    private DBEdificioDAO(Connection connection) throws SQLException {
        this.connection = DBConnection.getInstance();
    }

    private Connection connection;

    @Override
    public Edificio retriveByName(String nome) {
        //TODO implement
        return null;
    }
}
