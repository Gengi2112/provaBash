package ProgettoCinemaGruppo1.Java.Cinema3;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DbConnection {
    private static Connection connection;
    private static String query="";
    private static PreparedStatement pstmt;
    private final static String database = "jdbc:mysql://localhost:3306/cinema";
    private final static String user = "utenteCinema";
    private final static String password = "ciao";

    static {
        try {
            connection = (Connection) DriverManager.getConnection(database,user,password);
        }catch (Exception e){
            System.out.println("Errore connessione");
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static ResultSet visualizzaCinema(){
        try{
            query="select * from cinema ";
            pstmt= connection.prepareStatement(query);

            return pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null;
    }

    public static ResultSet cercafilm(String nomeFilm){
        try{
            query="select * from proiezionefilm  where nomeFilm like ?";
            pstmt= connection.prepareStatement(query);
            pstmt.setString(1,nomeFilm);
            return pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null;
    }
    public static ResultSet scegliProiezione(int idProiezioni){
        try{
            query="select * from proiezionefilm  where idProiezioni = ?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idProiezioni);
            return pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null;
    }

    public static ResultSet proiezioneCinema(int idCinema){
        try{
            query="select * from proiezionefilm  where idCinema = ?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idCinema);
            return pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null;
    }
    public static ArrayList arrayPosti(int idProiezione){
        try{
            query="select num_post from biglietti join proiezione on biglietti.idproiezione=proiezione.id  where   proiezione.id = ?  ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idProiezione);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> postiPresi = new ArrayList<>();
                while(rs.next()){
                    postiPresi.add(rs.getInt("num_post"));

                }
                return postiPresi;
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null;
    }

    public static Integer postiMax(int idProiezione){
        try{
            query="select numeroposti from sala join proiezione on sala.id=proiezione.idsala where proiezione.id = ?  ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idProiezione);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt("numeroposti");
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return null ;
    }

    public static void postoPreso(int idProiezione,int num_post){
        try{
            query="insert into biglietti (idproiezione,num_post, idutente) values (?,?,?) ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idProiezione);
            pstmt.setInt(2,num_post);
            pstmt.setInt(3,Utente.getId());
            pstmt.executeUpdate();

            try{
                FileWriter fileWriter= new FileWriter(String.valueOf(num_post),true);
                fileWriter.write(" biglietto numero "+num_post+" proiezione con id: "+idProiezione+" "+Utente.getNome());
                fileWriter.close();
                System.out.println("ho stampato i biglietti");

            } catch (Exception e) {
                System.out.println("Errore scrittura file");
            }
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }





}
