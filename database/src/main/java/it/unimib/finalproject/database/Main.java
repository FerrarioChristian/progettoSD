package it.unimib.finalproject.database;

import java.net.*;
import java.io.*;

/**
 * Classe principale in cui parte il database.
 */
public class Main {
	static Database<String> proiezioni_db = new Database<String>();
	static Database<String> prenotazioni_db = new Database<String>();
	
	static GestisciPrenotazioni gp = new GestisciPrenotazioni();
	
    public static final int PORT = 3030;

    /**
     * Avvia il database e l'ascolto di nuove connessioni.
     *
     * @return Un server HTTP Grizzly.
     */
    public static void startServer() {
        try {
            @SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PORT);
            System.out.println("Database listening at localhost:" + PORT);
            while (true)
                new Handler(server.accept()).start();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Handler di una connessione del client.
     */
    private static class Handler extends Thread {
        private Socket client;

        public Handler(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                var out = new PrintStream(client.getOutputStream(), true);
                var in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                String dato = "";
                String inputLine = "";

                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        break;
                    }
                    dato += inputLine;
                }
                String[] dati = dato.split(";");
                
                switch(dati[0]) {
                	case "creazione": 
                		out.println(gp.nuovaPrenotazione(dati));
                		break;
                		
                	case "elimina":
                		out.println(gp.eliminaPrenotazione(dati));
                		break;
                		
                	case "modifica":
                		out.println(gp.modificaPrenotazione(dati));
                }
                
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Metodo principale di avvio del database.
     *
     * @param args argomenti passati a riga di comando.
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        startServer();
    }
}

