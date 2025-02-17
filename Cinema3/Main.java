package ProgettoCinemaGruppo1.Java.Cinema3;

import static ProgettoCinemaGruppo1.Java.Cinema3.Menu.menuPrincipale;

public class Main {
    public static void main(String[] args) {
        Menu.benvenuto();
        Menu.adminMenu();
        Menu.gestoreMenu();
        menuPrincipale();
    }
}





/*package org.code2care;

import com.bethecoder.ascii_table.ASCIITable;

public class JavaConsoleTableExample {

    public static void main(String[] args) {

    String [] tableHeaders = { "Employee Name", "Salary", "Designation","Department"};

    String[][] tableData = {
                { "Mike Kurt", "10000", "Developer", "IT"  },
                { "Steve Musk", "20000", "Lead DevOps", "IT" },
                { "Larry Jobs", "30000", "Java Developer", "IT" },
                { "Elon Peters", "400000", "Manager", "IT" },
                { "Jake Burg", "50000000", "CEO", "IT"  },
    };

        ASCIITable.getInstance().printTable(tableHeaders, tableData);
    }
}
Copy*/


/*

1) Primo Deliverable:  MVP (minimum viable product)
Progettazione, creazione DB, DDL + DCL + DML Inserimenti dati (cinema, film e proiezioni)
Applicazione cinema con menu utente configurato e controlli di immissione
Acquisto biglietto singolo o multiplo senza scelta posti


Milestone 3, Final Exercise
CINEMA DATA DRIVEN JAVA CONSOLE APPLICATION WITH PRETTY TABLES
Creare un'applicazione per l'acquisto di posti al Cinema.
Ci potrebbero essere una tabella cinema, una tabella film e una tabella proiezione. (Il Database e la sua struttura sono in mano completamente vostra)
Gli utenti potranno poi scegliere il cinema e il film da vedere e acquistare uno o più posti.
Per ogni proiezione fornire una mappa del cinema con tutti i posti disponibili e tutti i posti già occupati e lasciare la possibilità alle persone di comprare i posti che sono liberi e ovviamente impedirgli di comprare i posti che sono già stati acquistati. Idealmene una griglia sotto forma di tabella con i posti disponibili in verde e quelli occupati in rosso.
Gestire menu di navigazione utente con loop infiniti e controlli sull'immissione errata (valori immessi fuori range e valori immessi di tipo sbagliato).
Usate i Colori per i messaggi e per colorare i posti disponibili/non disponibili.
Inoltre per visualizzare delle "Tabelle carine" (anche della disposizione dei posti in sala) potete consultare queste pagine ( o ricercarne altre simili per lo scopo) e adottare il metodo che vi sembra allo stesso tempo più SEMPLICE, OPPORTUNO e FUNZIONALE:
https://stackoverflow.com/questions/15215326/how-can-i-create-table-using-ascii-in-a-console
https://code2care.org/java/display-output-in-java-console-as-a-table
Idealmente l'utente potrà scegliere se iniziare dalla scelta del Film o dalla scelta del cinema.
Se sceglie film vedrà tutti i cinema che proiettano quel film, se sceglie cinema vedrà tutti i film proiettati in quel cinema.
Ammettete la presenza di cinema multisala.



3)  Terzo Deliverable: More advanced
Implementare pretty tables ovunque nell'applicazione, ogni menu, ogni scelta.
Implementare un login con username e password per gli utenti.
Implementare un ruolo di Amministratore dell'applicazione (super User) che è in grado di creare spettacoli, film, sale e inizializzare i posti per ogni proiezione. Implementare un ruolo di amministratore Cinema che gestisce i propri cinema (creazione cinema, sale, proiezioni e film).
Normalmente la prima cosa richiesta dovrebbe essere username e password.
Se si tratta dell'amministratore vedrebbe poi il menu dell'admin (Super User con dominio su tutte le tabelle o Cinema Admin con dominio soltanto sui cinema di proprietà), se si stratta di un utente finale partirebbe poi la scelta "customer" dell'acquisto dei biglietti a partire dal cinema/film/spettacolo.
Elementi da tener ben presenti:
Eseguire la progettazione con lo strumento a voi più congeniale (draw.io , d2 playground o quello che vi pare) e poi salvare le due o più immagini della progettazione (flusso applicazione e diagramma classi) in una cartella "progettazione" del progetto.
La progettazione dovrà considerare i 3 deliverable diversi, cioè molto probabilmente dovranno esserci dei diagrammi di flusso diversi per ogni fase di consegna del progetto. Va da sè che l'applicazione però deve essere progettata fin dall'inizio con la versione finale ben presente per non essere costretti a riscrivere tutto o molto codice fra una fase e l'altra.
---
Devono esserci 3 fasi di rilascio e 3 deliverable diversi con 3 prodotti ben distinti !
Devono esserci 3 fasi di rilascio e 3 deliverable diversi con 3 prodotti ben distinti !
Devono esserci 3 fasi di rilascio e 3 deliverable diversi con 3 prodotti ben distinti !
(l'applicazione finale sarà poi sempre la stessa, non dovrete FISICAMENTE consegnare 3 progetti, ma dovrete far vedere al "cliente" ogni singola fase di delivery e ognuna di quelle consegne dovrà essere completamente funzionante rispetto ai requisiti di quello step del progetto)
---
IMPORTANTE : OGNI 30 MINUTI ESATTI SI CAMBIA LA PERSONA CHE CONDIVIDE IL MONITOR (mettetevi un timer!)
---
In ogni fase, a partire dalla prima riga di codice che scriverete, considerate di conoscere sempre la migliore approssimazione della risposta alle domande "A che percentuale di svolgimento siete" e "Quanto manca al completamento del task in termine di ore lavoro". E' una abitudine che vi tornerà molto utile sul luogo di lavoro.
esempio brutto di come intendo la sala del cinema coi posti disponibili, occupati, ecc*/