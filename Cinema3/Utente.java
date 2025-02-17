package ProgettoCinemaGruppo1.Java.Cinema3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static ProgettoCinemaGruppo1.Java.Cinema2.Menu.getInt;
import static ProgettoCinemaGruppo1.Java.Cinema2.Menu.getString;


public class Utente {
    private static Connection connection = DbConnection.getConnection();
    private static int id;
    private static String nome;
    private static String username;
    private static String password;
    private static String ruolo;
    private static String query="";
    private static PreparedStatement pstmt;


    public static void log() {
        while (true){
            try {
                setUsername(getString("Inserisci username: "));
                setPassword(getString("Inserisci password: "));
                query = "SELECT * FROM utente";
                pstmt = connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String user = rs.getString("username");
                    String psw = rs.getString("password");
                    if(user.equals(getUsername()) && psw.equals(getPassword())) {
                        setId(rs.getInt("id"));
                        setNome(rs.getString("nome"));
                        setRuolo(rs.getString("ruolo"));
                        return;
                    }
                }
                int scelta = getInt("Username o password sbagliati\nDigita 0 per registrarti\naltro intero per riprovare");
                if (scelta==0){
                    reg();
                    return;
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
    //TODO RIMUOVERE
    public static void log(String username, String password) {
            try {
                setUsername(username);
                setPassword(password);
                query = "SELECT * FROM utente";
                pstmt = connection.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String user = rs.getString("username");
                    String psw = rs.getString("password");
                    if(user.equals(getUsername()) && psw.equals(getPassword())) {
                        setId(rs.getInt("id"));
                        setNome(rs.getString("nome"));
                        setRuolo(rs.getString("ruolo"));
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
    }


    public static void reg() {
        try {
            setNome(getString("Inserisci nome"));
            setUsername(getString("Inserisci username:"));
            setPassword(getString("Inserisci password:"));
            try{
                query = "INSERT INTO utente (nome,username,password,ruolo) VALUES (?,?,?,?)";
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, getNome());
                pstmt.setString(2, getUsername());
                pstmt.setString(3, getPassword());
                pstmt.setString(4,"utente");
                int inserimento = pstmt.executeUpdate();
                if(inserimento==1){
                    System.out.println("Utente registrato con successo");
                } else {
                    System.err.println("Errore nella registrazione");
                }
            } catch (Exception e){
                System.err.println("Errore nell'eseguire la query " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Errore: " + e.getMessage());
        }
        log();
    }

































    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Utente.id = id;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Utente.nome = nome;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Utente.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Utente.password = password;
    }

    public static String getRuolo() {
        return ruolo;
    }

    public static void setRuolo(String ruolo) {
        Utente.ruolo = ruolo;
    }
}
