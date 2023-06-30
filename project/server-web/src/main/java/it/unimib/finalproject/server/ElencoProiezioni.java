package it.unimib.finalproject.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/elenco_proiezioni")
public class ElencoProiezioni {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized Response restituisciElencoProiezioni() {
		String listaProiezioni = "";
		try {
			listaProiezioni = Files.readString(Paths.get(".\\lista_proiezioni.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Response.ok(listaProiezioni).build();
	}
}
