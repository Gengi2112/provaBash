package ProgettoCinemaGruppo1.Java.Cinema3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import com.bethecoder.ascii_table.ASCIITable;

import static ECOMMERCE.Menu.getInt;
import static ECOMMERCE.Menu.getString;

public class Menu {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream writer = new PrintStream(System.out);
    public static final String biancoReset = "\u001B[0m";
    public static final String rosso = "\u001B[31m";
    public static final String verde = "\u001B[32m";

    public static ArrayList<Integer> listaFilm = new ArrayList<>();


    public static void benvenuto(){
        System.out.println("Benvenuto!");
        while (true){
            int scelta = getInt("Scegli un'opzione:\n1)Accedi\n2)Registrati\n3)Esci");
            switch (scelta) {
                case 1:
                    Utente.log();
                    return;
                case 2:
                    Utente.reg();
                    return;
                case 3:
                    exit();
                default:
                    System.out.println("Scelta non presente");
            }
        }
    }

    public static void menuPrincipale(){
        System.out.println("Benvenuto " + Utente.getNome() +"!");
        while (Utente.getRuolo().equals("utente")) {
            int scelta = getInt("Scegli cosa fare\n1) Vuoi cercare la proiezione di un film\n2) Vedere le proiezioni di un cinema\n3) Esci");
            switch (scelta) {
                case 1:
                    cercaPerFilm();
                    break;
                case 2:
                    cercaPerCinema();
                    break;
                case 3: exit();
                default:
                    System.out.println("Errore inserimento.Riprova");
            }
        }
    }


    public static void adminMenu() {
        while (Utente.getRuolo().equals("admin")) {
            int scelta = getInt("Scegli la tabella sulla quale vuoi operare: \n1) per creare spettacoli \n2) per creare film \n3) per creare sale \n4)eliminare tutti i biglietti di una proiezione \n5)esci");
            switch (scelta) {
                case 1:
                    Crud.creaSpettacoli();
                    break;
                case 2:
                    Crud.creaFilm();
                    break;
                case 3:
                    Crud.creaSala();
                    break;
                case 4:
                    Crud.eliminaBiglietti();
                    break;
                case 5: exit();
                default: System.out.println("Scelta non disponibile");
            }
        }
    }


    public static void gestoreMenu() {
        while (Utente.getRuolo().equals("gestore")) {
            ArrayList<Integer> cinemaGestiti = Crud.controlloGestore();
            int scelta1=0;
            if (cinemaGestiti.size()>1){
                 scelta1=getInt("Scegli il cinema sul quale vuoi operare :");
            }else if (cinemaGestiti.isEmpty()) {
                System.out.println("Non hai cinema");
                System.exit(0);
                }else {
                    scelta1=cinemaGestiti.get(0);
                }
            int scelta = getInt("Scegli la tabella sulla quale vuoi operare: \n1) per creare spettacoli  \n2) per creare sale \n3)eliminare tutti i biglietti di una proiezione \n4) indietro \n5) esci");
            switch (scelta) {
                case 1:
                    Crud.creaSpettacoliGestore(scelta1);
                    break;
                case 2:
                    Crud.creaSalaGestore(scelta1);
                    break;
                case 3:
                    Crud.eliminaBigliettiGestore(scelta1);
                    break;
                case 4:
                    break;
                case 5: exit();
                default: System.out.println("Scelta non disponibile");
            }
        }
    }




    /*String [] tableHeaders = { "Employee Name", "Salary", "Designation","Department"};

    String[][] tableData = {
                { "Mike Kurt", "10000", "Developer", "IT"  },
                { "Steve Musk", "20000", "Lead DevOps", "IT" },
                { "Larry Jobs", "30000", "Java Developer", "IT" },
                { "Elon Peters", "400000", "Manager", "IT" },
                { "Jake Burg", "50000000", "CEO", "IT"  },
    };

        ASCIITable.getInstance().printTable(tableHeaders, tableData);*/
    private static void cercaPerCinema(){
        while (true){
            try {
                ResultSet rs=DbConnection.visualizzaCinema();

                ArrayList<Integer> listaCinema = new ArrayList<>();
                String [] tableHeaders = { "id", "nome cinema"};
                ArrayList<String[]> tableDataList = new ArrayList<>(){};
                while (rs.next()){
                    tableDataList.add(new String[]{ rs.getString("id"), rs.getString("nome") });
                    listaCinema.add(rs.getInt("id"));
                }
                stampaTabella(tableHeaders,tableDataList);

                int scelta=getInt("scegli l'id del cinema ");
                if (listaCinema.contains(scelta)){
                    rs=DbConnection.proiezioneCinema(scelta);
                    while (rs.next()){
                        System.out.println(rs.getInt("idProiezioni")+" ) " + rs.getString("nomeFilm") + " Cinema: " + rs.getString("nomeCinema") + " il " + rs.getDate("dataorario") + " alle " + rs.getTime("dataorario"));
                         listaFilm.add(rs.getInt("idProiezioni"));
                    }
                    compraBiglietti(listaFilm);
                    listaFilm.clear();





                }else {
                    System.out.println("non ci sono proiezioni per la tua ricerca");
                }

            } catch (SQLException e) {
                System.out.println("Errore "+e.getMessage());
            }
        }
    }


    private static void cercaPerFilm(){
        while (true){
            try {
                String nomefilm=getString("Inserisci il nome del film da cercare");
                ResultSet rs =DbConnection.cercafilm("%"+nomefilm+"%");
                String [] tableHeaders = { "id", "nome film","nome cinema","data & orario"};
                ArrayList<String[]> tableDataList = new ArrayList<>(){};
                while (rs.next()){
                    tableDataList.add(new String[]{rs.getString("idProiezioni") , rs.getString("nomeFilm") ,  rs.getString("nomeCinema") ,  rs.getString("dataorario")});
                    listaFilm.add(rs.getInt("idProiezioni"));
                }

                if (!listaFilm.isEmpty()){
                    stampaTabella(tableHeaders,tableDataList);
                    compraBiglietti(listaFilm);
                    listaFilm.clear();
                }else {
                    System.out.println("non ci sono proiezioni per la tua ricerca");
                }

            } catch (SQLException e) {
                System.out.println("Errore "+e.getMessage());
            }
        }
    }

    private static void compraBiglietti(ArrayList listaFilm){
        int contaBiglietti=0;
        while(true){
            try{
                int scelta=getInt("Per qual film vuoi dei biglietti? \nInserisci 0 per tornare indietro ");
                if (scelta==0){
                    return;
                } else if (listaFilm.contains(scelta)) {
                    ResultSet rs=DbConnection.scegliProiezione(scelta);
                    while(true){
                        mostraPosto(scelta);
                        int sceltaUno=getInt("quale biglietto vuoi comprare?");
                        if (DbConnection.arrayPosti(scelta).contains(sceltaUno)){
                            System.out.println("scegli un posto verde!!!");
                        }else {
                            DbConnection.postoPreso(scelta,sceltaUno);
                            contaBiglietti++;

                            int sceltaDue =getInt("vuoi comprarne altri? \n1)si \n2)no");
                            if (sceltaDue==2){
                                String proiezioneScelta="";
                                while (rs.next()){
                                    proiezioneScelta= rs.getInt("idProiezioni")+" ) " + rs.getString("nomeFilm") + " Cinema: " + rs.getString("nomeCinema") + " il " + rs.getDate("dataorario") + " alle " + rs.getTime("dataorario");
                                }
                                System.out.println("hai comprato "+contaBiglietti+" biglietti per la proiezione di: "+proiezioneScelta);
                                exit();
                            }
                        }

                    }


                }


            }catch (Exception e){

            }
        }
    }

    private static void mostraPosto(int idProiezione){
                ArrayList<Integer> postiPresi = DbConnection.arrayPosti(idProiezione);
                int postiMax = DbConnection.postiMax(idProiezione);
                int width=5;
                int height=postiMax/5;
                int count=1;
                for(int i=0;i<width ; i++) {
                    System.out.println("+----+----+----+----+----+----+----+----+----+----+");

                    for(int j=0;j<height;j++) {
                        if (postiPresi.contains(count)){
                            System.out.format("|"+rosso+" %2d "+biancoReset, count++);

                        }else{
                            System.out.format("|"+verde+" %2d "+biancoReset, count++);
                        }


                        if(j==height-1) { // closing | for last column
                            System.out.print("|");
                        }
                    }
                    System.out.println();
                    if(i==width-1) { // closing line for last row
                        System.out.print("+----+----+----+----+----+----+----+----+----+----+\n");
                    }
                }

    }






























    //utility
    public static String getString(String message){
        while (true){
            try {
                writer.println(message);
                return reader.readLine();
            }catch (Exception e){
                writer.println("errore di input, riprovare");
            }
        }
    }

   /* public static Integer getInt(String message){
        while (true){
            try {
                writer.println(message);
                return Integer.parseInt(reader.readLine());
            }catch (Exception e){
                writer.println("errore di input, inserisci un valore intero");
            }
        }
    }*/

    public static Integer getInt(String message){
        while (true){
            try {
                writer.println(message);
                return Integer.parseInt(reader.readLine());
            }catch (Exception e){
                writer.println("errore di input, inserisci un valore intero");
            }
        }
    }



    public static double getDouble(String message){
        while (true){
            try {
                writer.println(message);
                return Double.parseDouble(reader.readLine());
            }catch (Exception e){
                writer.println("errore di input, inserisci un valore intero");
            }
        }
    }

    public static void exit(){
        System.out.println("Arrivederci ");
        System.exit(0);
    }

    public static void stampaTabella(String[] header, ArrayList<String[]> dati){
    String[][] tableData = dati.toArray(new String[0][]);
    ASCIITable.getInstance().printTable(header, tableData);

    }

    public static LocalDate getData(String message){
        while (true){
            try {
                writer.println(message);
                return LocalDate.parse(reader.readLine());
            }catch (Exception e){
                writer.println("errore di input");
            }
        }
    }
    public static LocalTime getOra(String message){
        while (true){
            try {
                writer.println(message);
                return LocalTime.parse(reader.readLine());
            }catch (Exception e){
                writer.println("errore di input, inserisci un valore intero");
            }
        }
    }







}
