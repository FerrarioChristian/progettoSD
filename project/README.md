# Progetto Sistemi Distribuiti 2022-2023



## Componenti del gruppo

* Nome Cognome (Matricola) <email>
* Andrea Lanza	887501 a.lanza13@campus.unimib.it
* Christiano Ferrario  886230 c.ferrario30@campus.unimib.it
* Davide Bonfanti  873293 d.bonfanti13@campus.unimib.it

#### UTILIZZO

#### ClientWeb

Questo è un esempio di implementazione di un client web per interagire con un server tramite richieste HTTP. Il client consente di visualizzare i posti disponibili per una determinata proiezione cinematografica e di effettuare operazioni di prenotazione, aggiornamento ed eliminazione.

### Dipendenze
Il client utilizza JavaScript per la logica di front-end e richieste HTTP per comunicare con il server. Non sono richieste dipendenze esterne per l'esecuzione del client.

### Utilizzo
Il client viene caricato al momento del caricamento della pagina. Viene generata una chiave di prenotazione univoca e memorizzata come cookie nel browser.

#### Visualizzazione dei posti
Quando si seleziona un film dalla lista, vengono visualizzati i posti disponibili nella tabella. Facendo clic su un posto, viene selezionato o deselezionato. I posti selezionati vengono memorizzati nella variabile `selectedSeats`.

#### Prenotazione dei posti
Per effettuare una prenotazione, premi il pulsante "Prenota posti". Verrà inviata una richiesta POST al server contenente le informazioni sulla proiezione, la chiave di prenotazione e i posti selezionati. Se la prenotazione viene effettuata con successo, verrà visualizzato un messaggio di conferma.

#### Aggiornamento della prenotazione
Per aggiornare una prenotazione esistente, premi il pulsante "Aggiorna prenotazione". Verrà inviata una richiesta PUT al server contenente le informazioni sulla proiezione, la chiave di prenotazione e i posti selezionati. Se l'aggiornamento viene effettuato con successo, verrà visualizzato un messaggio di conferma.

#### Eliminazione della prenotazione
Per eliminare una prenotazione esistente, premi il pulsante "Elimina prenotazione". Verrà inviata una richiesta POST al server contenente le informazioni sulla proiezione e la chiave di prenotazione. Se l'eliminazione viene effettuata con successo, verrà visualizzato un messaggio di conferma.

#### Porte e indirizzi

Il server Web si pone in ascolto all'indirizzo `localhost` alla porta `8080`. Il database si pone in ascolto allo stesso indirizzo del server Web ma alla porta `3030`.
