# Protocollo di Comunicazione TCP

Di seguito viene descritto il protocollo di comunicazione TCP utilizzato per le operazioni di creazione, eliminazione e modifica delle prenotazioni tramite API REST.


## Creazione di una Prenotazione

1. Il client invia una richiesta HTTP POST al server web per creare una nuova prenotazione.
   - Il corpo della richiesta contiene i dettagli della prenotazione da creare.
   - Il server web riceve la richiesta e avvia la gestione della prenotazione.

2. Il server web utilizza la classe Prenotazioni per gestire la richiesta del cliente.
   - La classe Prenotazioni effettua una connessione TCP con il database per comunicare con la classe Database.

3. Il server web crea una connessione TCP con il database.
   - Il server web stabilisce una connessione TCP con l'indirizzo IP e la porta del database (localhost:3030).

4. Il server web invia una richiesta al database per salvare la nuova prenotazione.
   - La classe Prenotazioni trasmette l'operazione di "creazione", il nome della proiezione, la chiave della prenotazione e i valori associati al database tramite la connessione TCP.

5. Il database riceve la richiesta di creazione.
   - Il database verifica i parametri della richiesta e inserisce la nuova prenotazione associata alla chiave specificata.

6. Il database invia una conferma di successo al server web.
   - Il server web riceve la conferma di successo e risponde al cliente con una risposta HTTP con stato "CREATED".

7. Il cliente (API REST) riceve la risposta del server web.
   - Il cliente riceve la risposta HTTP con stato "CREATED" per indicare che la prenotazione è stata creata con successo.


## Eliminazione e Modifica di una Prenotazione

Il protocollo di comunicazione per le operazioni di "eliminazione" e "modifica" segue un flusso simile con lievi variazioni nei parametri trasmessi. La classe GestionePrenotazioni non viene coinvolta nella comunicazione TCP diretta, ma fornisce i metodi per gestire le operazioni di prenotazione nel server web, lavorando in collaborazione con il database per salvare, eliminare o modificare le prenotazioni.
