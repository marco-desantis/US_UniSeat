package model.pojo;

/**
 * Questa classe modella il concetto di "Utente" all'interno del sistema.
 *
 * @author De Caro Antonio
 * @version 0.1
 * @inv il campo password deve sempre essere codificato in SHA_12
 * @see java.security.MessageDigest
 */
public class Utente {

    private String email;
    private String nome;
    private String cognome;
    private String password;
    private TipoUtente utente;

    public Utente() {
    }

    public Utente(String email,String nome,String cognome, String password,TipoUtente utente) {
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.utente = utente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUtente getTipoUtente() {
        return utente;
    }

    public void setTipoUtente(TipoUtente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", password='" + password + '\'' +
                ", utente=" + utente +
                '}';
    }
}
