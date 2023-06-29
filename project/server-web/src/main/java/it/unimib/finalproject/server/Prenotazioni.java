package it.unimib.finalproject.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("prenota")
public class Prenotazioni {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response nuovaPrenotazione(String body) throws UnknownHostException, IOException {
		var prenotazione = new Prenotazione();
    	
    	 try {
             var mapper = new ObjectMapper();
             prenotazione = mapper.readValue(body, Prenotazione.class);

             // Il nome e il numero ci devono essere.
             if (prenotazione.getKey() == null || prenotazione.getPosti().length == 0)
                 return Response.status(Response.Status.BAD_REQUEST).build();

             Socket socket = new Socket("localhost", 3030);
             PrintStream out = new PrintStream(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             
             //String database = "prenotazione";
             String operazione = "creazione";
             String proiezione = prenotazione.getFilm();
             String key = prenotazione.getKey();
             String[] valore = prenotazione.getPosti();
             
             String messaggio = operazione + ";" + proiezione + ";" + key  + ";" + String.join(",", valore);
             
             out.println(messaggio);
             out.println(".");
             
             String dato = "";
             String inputLine = "";
             
             while ((inputLine = in.readLine()) != null) {
                 if (".".equals(inputLine)) {
                     break;
                 }
                 dato += inputLine;
             }
             
             System.out.println(dato);
             
             if(dato.equals("false")) {
            	 return Response.status(Response.Status.CONFLICT).build();            	 
             }
             
             
             
             
         } catch (JsonParseException | JsonMappingException e) {
             System.out.println(e);
             return Response.status(Response.Status.BAD_REQUEST).build();
         } catch (IOException e) {
             System.out.println(e);
             return Response.serverError().build();
         }

    	 return Response.status(Response.Status.CREATED).build();
    	
	}

	@Path("/elimina")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response eliminaPrenotazione(String body) {
		var prenotazione = new Prenotazione();
    	
   	 try {
            var mapper = new ObjectMapper();
            prenotazione = mapper.readValue(body, Prenotazione.class);

            // Il nome e il numero ci devono essere.
            if (prenotazione.getKey() == null || prenotazione.getFilm() == null)
                return Response.status(Response.Status.BAD_REQUEST).build();

            Socket socket = new Socket("localhost", 3030);
            PrintStream out = new PrintStream(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //String database = "prenotazione";
            String operazione = "elimina";
            String proiezione = prenotazione.getFilm();
            String key = prenotazione.getKey();
            
            String messaggio = operazione + ";" + proiezione + ";" + key  + ";";
            System.out.println("Messaggio: " + messaggio);
            
            out.println(messaggio);
            out.println(".");
            
            String dato = "";
            String inputLine = "";
            
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    break;
                }
                dato += inputLine;
            }
            
            if(dato.equals("false")) {
           	 return Response.status(Response.Status.CONFLICT).build();            	 
            }
            
            
            
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (IOException e) {
            System.out.println(e);
            return Response.serverError().build();
        }
   	 
        return Response.status(Response.Status.CREATED).build();
        
	}

	@Path("/aggiorna")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificaPrenotazione(String body) {
		var prenotazione = new Prenotazione();
    	
   	 try {
            var mapper = new ObjectMapper();
            prenotazione = mapper.readValue(body, Prenotazione.class);

            // Il nome e il numero ci devono essere.
            if (prenotazione.getKey() == null || prenotazione.getPosti() == null)
                return Response.status(Response.Status.BAD_REQUEST).build();

            Socket socket = new Socket("localhost", 3030);
            PrintStream out = new PrintStream(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //String database = "prenotazione";
            String operazione = "modifica";
            String proiezione = prenotazione.getFilm();
            String key = prenotazione.getKey();
            String[] valore = prenotazione.getPosti();
            
            String messaggio = operazione + ";" + proiezione + ";" + key  + ";" + String.join(",", valore);
            
            out.println(messaggio);
            out.println(".");
            
            String dato = "";
            String inputLine = "";
            
            while ((inputLine = in.readLine()) != null) {
                if (".".equals(inputLine)) {
                    break;
                }
                dato += inputLine;
            }
            
            System.out.println(dato);
            
            if(dato.equals("false")) {
           	 return Response.status(Response.Status.CONFLICT).build();            	 
            }
            
            
            
            
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (IOException e) {
            System.out.println(e);
            return Response.serverError().build();
        }

   	 return Response.status(Response.Status.CREATED).build();
	}
}

