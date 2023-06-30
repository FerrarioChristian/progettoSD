# Progetto Sistemi Distribuiti 2022-2023 - API REST

Di seguito viene fornita la documentazione dell'API REST del progetto "Gestione Prenotazioni".


## `/prenota`

Questa risorsa consente di effettuare una nuova prenotazione.


### POST

**Descrizione**: Crea una nuova prenotazione.

**Parametri**: Non sono previsti parametri nel percorso o nella richiesta.

**Header richiesta**:
- Content-Type: application/json

**Body richiesta**: Rappresentazione in formato JSON dei dati della prenotazione. Il body deve contenere i seguenti campi:
- `film` (stringa): il titolo del film prenotato.
- `key` (stringa): la chiave identificativa della prenotazione.
- `posti` (array di stringhe): l'elenco dei posti prenotati.

**Risposta**: In caso di successo, la prenotazione viene creata correttamente e viene restituito lo stato `201 Created` con un body vuoto. In caso di errori, vengono restituiti i seguenti stati:
- `400 Bad Request`: la richiesta è malformata o mancano alcuni campi obbligatori.
- `409 Conflict`: la prenotazione con la stessa chiave identificativa esiste già.

**Codici di stato restituiti**:
- 201 Created
- 400 Bad Request
- 409 Conflict


## `/prenota/elimina`

Questa risorsa consente di eliminare una prenotazione esistente.


### POST

**Descrizione**: Elimina una prenotazione.

**Parametri**: Non sono previsti parametri nel percorso o nella richiesta.

**Header richiesta**:
- Content-Type: application/json

**Body richiesta**: Rappresentazione in formato JSON dei dati della prenotazione da eliminare. Il body deve contenere i seguenti campi:
- `film` (stringa): il titolo del film della prenotazione da eliminare.
- `key` (stringa): la chiave identificativa della prenotazione da eliminare.

**Risposta**: In caso di successo, la prenotazione viene eliminata correttamente e viene restituito lo stato `200 OK` con un body vuoto. In caso di errori, vengono restituiti i seguenti stati:
- `400 Bad Request`: la richiesta è malformata o mancano alcuni campi obbligatori.
- `404 Not Found`: la prenotazione da eliminare non è stata trovata.

**Codici di stato restituiti**:
- 200 OK
- 400 Bad Request
- 404 Not Found


## `/prenota/aggiorna`

Questa risorsa consente di modificare una prenotazione esistente.


### PUT

**Descrizione**: Modifica una prenotazione.

**Parametri**: Non sono previsti parametri nel percorso o nella richiesta.

**Header richiesta**:
- Content-Type: application/json

**Body richiesta**: Rappresentazione in formato JSON dei dati della prenotazione da modificare. Il body deve contenere i seguenti campi:
- `film` (stringa): il titolo del film della prenotazione da modificare.
- `key` (stringa): la chiave identificativa della prenotazione da modificare.
- `posti` (array di stringhe): l'elenco aggiornato dei posti prenotati.

**Risposta**: In caso di successo, la prenotazione viene modificata correttamente e viene restituito lo stato `201 Created` con un body vuoto. In caso di errori, vengono restituiti i seguenti stati:
- `400 Bad Request`: la richiesta è malformata o mancano alcuni campi obbligatori.
- `409 Conflict`: la prenotazione non può essere modificata a causa di conflitti o inconsistenze.

**Codici di stato restituiti**:
- 201 Created
- 400 Bad Request
- 409 Conflict


Si prega di notare che l'API fa riferimento al server "localhost" sulla porta 3030. Assicurarsi che il server sia in esecuzione e accessibile correttamente prima di utilizzare l'API.
