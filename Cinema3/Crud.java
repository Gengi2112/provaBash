package ProgettoCinemaGruppo1.Java.Cinema3;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static ProgettoCinemaGruppo1.Java.Cinema3.Menu.getData;
import static ProgettoCinemaGruppo1.Java.Cinema3.Menu.getOra;
import static ProgettoCinemaGruppo1.Java.Cinema3.Menu.*;

public class Crud {
    /*3)  Terzo Deliverable: More advanced
 FATTO Implementare pretty tables ovunque nell'applicazione, ogni menu, ogni scelta.
Implementare un login con username e password per gli utenti.
Implementare un ruolo di Amministratore dell'applicazione (super User) che è in grado di creare spettacoli, film, sale e inizializzare i posti per ogni proiezione. Implementare un ruolo di amministratore Cinema che gestisce i propri cinema (creazione cinema, sale, proiezioni e film).
Normalmente la prima cosa richiesta dovrebbe essere username e password.
Se si tratta dell'amministratore vedrebbe poi il menu dell'admin (Super User con dominio su tutte le tabelle o Cinema Admin con dominio soltanto sui cinema di proprietà), se si stratta di un utente finale partirebbe poi la scelta "customer" dell'acquisto dei biglietti a partire dal cinema/film/spettacolo.*/

    private static Connection connection =DbConnection.getConnection();
    private static String query="";
    private static PreparedStatement pstmt;




    public static void creaSpettacoli(){

            try{
                ArrayList<Integer> listaIdFilm=filmRead();
                int idfilm=0;
                int idsala=0;
                while(true) {
                    idfilm = getInt("di quale film vuoi creare una proiezione?");
                    if (!listaIdFilm.contains(idfilm)) {
                        System.out.println("errore,il film non è presente");
                        continue;
                    }
                    break;
                }
                while(true){
                    ArrayList<Integer> listaIdSale=salaRead();
                    idsala = getInt("inserisci l'id della sala dove ci sarà lo spettacolo ");
                    if (!listaIdSale.contains(idsala)){
                        System.out.println("errore,la sala non è presente");
                        continue;
                    }
                    break;
                }
                LocalDate data =getData("Inserisci la data della proiezione nel formato AAAA-MM-GG");
                LocalTime ora = getOra("Inserisci l'ora dello spettacolo ne formato HH:MM");
                LocalDateTime dataOra = LocalDateTime.of(data, ora);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                String dataorario = dataOra.format(formatter);
                query="insert into proiezione (idfilm,dataorario,idsala) values ( ? , ? , ? ) ";
                pstmt= connection.prepareStatement(query);
                pstmt.setInt(1,idfilm);
                pstmt.setString(2,dataorario);
                pstmt.setInt(3,idsala);
                pstmt.executeUpdate();
                proiezioniRead();
            } catch (Exception e) {
                System.out.println("Errore : " + e.getMessage());
            }
    }


    //TODO
    public static void creaSpettacoliGestore(int id_cinema){
        try{
            int idfilm=0;

            ArrayList<Integer> listaIdFilm=filmRead();
            while(true){
                idfilm=getInt("di quale film vuoi creare una proiezione?");
                if (!listaIdFilm.contains(idfilm)){
                    System.out.println("errore, il film non è presente");
                    continue;
                }
            break;
            }
            query="select * from sala where idcinema = ?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,id_cinema);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id", "numeroposti "};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            ArrayList<Integer> sale = new ArrayList<>();

            while (rs.next()){
                sale.add(rs.getInt("id"));
                tableDataList.add(new String[]{rs.getString("id") ,  rs.getString("numeroposti")});
            }
            stampaTabella(tableHeaders,tableDataList);
            int idsala=0;
            while(true){
                idsala=getInt("inserisci l'id della sala dove ci sarà lo spettacolo ");
                if (!sale.contains(idsala)){
                    System.out.println("non gestisci questa sala");
                    continue;
                }
                break;
            }

            LocalDate data =getData("Inserisci la data della proiezione nel formato AAAA-MM-GG");
            LocalTime ora = getOra("Inserisci l'ora dello spettacolo ne formato HH:MM");
            LocalDateTime dataOra = LocalDateTime.of(data, ora);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dataorario = dataOra.format(formatter);
            query="insert into proiezione (idfilm,dataorario,idsala) values ( ? , ? , ? )  ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idfilm);
            pstmt.setString(2,dataorario);
            pstmt.setInt(3,idsala);
            pstmt.executeUpdate();
            proiezioniCinema(id_cinema);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }

    public static void creaFilm(){
        try{
            filmRead();
            String nome=getString("inserisci il nome del film");
            String trama=getString("inserisci la trama del film");


            query="insert into film (nome,trama) values ( ? , ? ) ";
            pstmt= connection.prepareStatement(query);
            pstmt.setString(1,nome);
            pstmt.setString(2,trama);

            pstmt.executeUpdate();
            filmRead();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }
    public static void creaSala(){

        try{
            int idcinema=0;
            ArrayList<Integer> listaIdCinema=cinemaRead();
            while(true){
                idcinema=getInt("inserisci l'id del cinema dove vuoi creare la sala");
                if (!listaIdCinema.contains(idcinema)){
                    System.out.println("non esiste quel cinema!!");
                    continue;
                }
                break;
            }

            int numeroposti=getInt("inserisci il numero di posti della sala");


            query="insert into sala (idcinema,numeroposti) values ( ? , ? ) ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idcinema);
            pstmt.setInt(2,numeroposti);


            pstmt.executeUpdate();
            salaRead();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }
    public static void creaSalaGestore(int id_cinema){
        try{

            int numeroposti=getInt("inserisci il numero di posti della sala");


            query="insert into sala (idcinema,numeroposti) values ( ? , ? ) ";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,id_cinema);
            pstmt.setInt(2,numeroposti);


            pstmt.executeUpdate();
            salaRead();
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }


    public static void eliminaBiglietti(){
        try{
            ArrayList<Integer> listaIdProiezioni=new ArrayList<>();
            query="select * from biglietti join proiezione on biglietti.idproiezione=proiezione.id ";
            pstmt= connection.prepareStatement(query);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "idbiglietto","idproiezione", "dataorario ","idsala"};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                tableDataList.add(new String[]{rs.getString("id") ,rs.getString("idproiezione"),  rs.getString("dataorario"),rs.getString("idsala")});
                listaIdProiezioni.add(rs.getInt("idproiezione"));
            }
            stampaTabella(tableHeaders,tableDataList);
            int scelta=0;

            while(true){
                scelta = getInt("Di quale proiezione vuoi azzerare i biglietti?");
                if (!listaIdProiezioni.contains(scelta)){
                    System.out.println("non c'è questa proiezione!!!");
                    continue;
                }
                break;
            }
            query="delete from biglietti where idproiezione=?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,scelta);
            pstmt.executeUpdate();
            System.out.println("Biglietti azzerati con successo");
            }
            catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }
    public static void eliminaBigliettiGestore(int idcinema){
        try{
            ArrayList <Integer> idproiezioni=proiezioniCinema(idcinema);
            while (true){
                int scelta = getInt("Di quale proiezione vuoi azzerare i biglietti?");
                if (idproiezioni.contains(scelta)){
                    query="delete * from biglietti where idproiezione=?";
                    pstmt= connection.prepareStatement(query);
                    pstmt.setInt(1,scelta);
                    pstmt.executeUpdate();
                    System.out.println("Biglietti azzerati con successo");
                    break;
                }
        }}
        catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
    }




    public static ArrayList filmRead(){
        ArrayList<Integer> listafilm= new ArrayList<>();
        try{
            query="select * from film";
            pstmt= connection.prepareStatement(query);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id", "nome "};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                tableDataList.add(new String[]{rs.getString("id") , rs.getString("nome")});
                listafilm.add(rs.getInt("id"));
            }
            stampaTabella(tableHeaders,tableDataList);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return listafilm;
    }
    public static ArrayList<Integer> cinemaRead(){
        ArrayList<Integer> listacinema=new ArrayList<>();
        try{

            query="select * from cinema";
            pstmt= connection.prepareStatement(query);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id", "nome "};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                tableDataList.add(new String[]{rs.getString("id") , rs.getString("nome")});
                listacinema.add(rs.getInt("id"));
            }
            stampaTabella(tableHeaders,tableDataList);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return listacinema;
    }





    public static ArrayList<Integer> salaRead(){
        ArrayList listasale = new ArrayList<>();
        try{
            query="select * from sala";
            pstmt= connection.prepareStatement(query);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id","idcinema", "numeroposti "};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                tableDataList.add(new String[]{rs.getString("id") ,rs.getString("idcinema"),  rs.getString("numeroposti")});
                listasale.add(rs.getInt("id"));
            }
            stampaTabella(tableHeaders,tableDataList);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return listasale;
    }
    public static ArrayList<Integer> proiezioniRead(){
        ArrayList<Integer> listaIdProiezioni= new ArrayList<>();
        try{
            query="select * from proiezione";
            pstmt= connection.prepareStatement(query);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id","idfilm", "dataorario ","idsala"};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                tableDataList.add(new String[]{rs.getString("id") ,rs.getString("idfilm"),  rs.getString("dataorario"),rs.getString("idsala")});
                listaIdProiezioni.add(rs.getInt("id"));
            }
            stampaTabella(tableHeaders,tableDataList);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return listaIdProiezioni;
    }
    public static ArrayList<Integer> proiezioniCinema(int idcinema){
        ArrayList<Integer> idproiezioni=new ArrayList<>();
        try{
            query="select * from proiezionefilm  where idCinema = ?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1,idcinema);
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id","nomeFilm", "dataorario ","idsala"};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                idproiezioni.add(rs.getInt("idProiezioni"));
                tableDataList.add(new String[]{rs.getString("idProiezioni") ,rs.getString("nomeFilm"),  rs.getString("dataorario"),rs.getString("idSala")});
            }
            stampaTabella(tableHeaders,tableDataList);
        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return idproiezioni;
    }


    public static ArrayList<Integer> controlloGestore(){
        ArrayList<Integer> cinemaGestiti=new ArrayList<>();
        try{
            query="select *  from cinema where id_gestore=?";
            pstmt= connection.prepareStatement(query);
            pstmt.setInt(1, Utente.getId());
            ResultSet rs= pstmt.executeQuery();
            String [] tableHeaders = { "id", "nome "};
            ArrayList<String[]> tableDataList = new ArrayList<>(){};
            while (rs.next()){
                cinemaGestiti.add(rs.getInt("id"));
                tableDataList.add(new String[]{rs.getString("id") , rs.getString("nome")});
            }
            stampaTabella(tableHeaders,tableDataList);


        } catch (Exception e) {
            System.out.println("Errore : " + e.getMessage());
        }
        return cinemaGestiti;
    }


}
