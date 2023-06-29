package it.unimib.finalproject.database;

import java.util.LinkedList;
import java.util.List;

public class GestisciPrenotazioni {
	public GestisciPrenotazioni() {
		
	}
	
	public boolean nuovaPrenotazione(String[] dati) {
		//verifico che l'utente non abbia gia una prenotazione
		if(Main.proiezioni_db.getData(dati[1]) != null && Main.proiezioni_db.getData(dati[1]).contains(dati[2])) {
			return false;
		}
		
		//verifico che i posti non siano gia prenotati
		if (Main.proiezioni_db.containsKey(dati[1])) {
		    String[] prenotazioni = Main.proiezioni_db.getData(dati[1]).split(";");
		    
		    if (prenotazioni != null) {
		        for (String pren : prenotazioni) {
		            String posti = Main.prenotazioni_db.getData(pren);
		            if (posti != null) {
		                for (String posto : dati[3].split(",")) {
		                    if (posti.contains(posto)) {
		                        return false;
		                    }
		                }
		            }
		        }
		    }
		}
		
		
		String prenotazioni = Main.proiezioni_db.getData(dati[1]);
		if(prenotazioni != null) {
			Main.proiezioni_db.deleteData(dati[1]);
			Main.proiezioni_db.insertData(dati[1], prenotazioni + dati[2] + ";");
		} else {
			Main.proiezioni_db.insertData(dati[1], dati[2] + ";");
		}
		

		Main.prenotazioni_db.insertData(dati[2], dati[3]);
		
		return true;
	}

	public boolean modificaPrenotazione(String[] dati) {
		if(Main.proiezioni_db.getData(dati[1]).contains(dati[2])) {
			eliminaPrenotazione(dati);
			nuovaPrenotazione(dati);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eliminaPrenotazione(String[] dati) {
		if(Main.proiezioni_db.getData(dati[1]) != null && Main.proiezioni_db.getData(dati[1]).contains(dati[2])) {
			String[] prenotazioni = Main.proiezioni_db.getData(dati[1]).split(";");
			String[] nuove_prenotazioni = removeElements(prenotazioni, dati[2]);
			
			if(String.join(";", nuove_prenotazioni).toString().equals("null")) {
				Main.proiezioni_db.insertData(dati[1], "");
			} else {
				Main.proiezioni_db.insertData(dati[1], String.join(";", nuove_prenotazioni));
			}
			Main.prenotazioni_db.deleteData(dati[2]);
			
			return true;
		}
		return false;
	}
	
	public static String[] removeElements(String[] input, String deleteMe) {
	    List<String> result = new LinkedList<String>();

	    for(String item : input)
	        if(!deleteMe.equals(item))
	            result.add(item);

	    return result.toArray(input);
	}
	
	
}
