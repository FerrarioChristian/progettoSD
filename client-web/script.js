// Dichiarazione di una variabile globale per memorizzare i posti selezionati
var selectedSeats = [];
var proiezione = "";

function showSeats(film) {
  var seats = [
    ['A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'A7', 'A8'],
    ['B1', 'B2', 'B3', 'B4', 'B5', 'B6', 'B7', 'B8'],
    ['C1', 'C2', 'C3', 'C4', 'C5', 'C6', 'C7', 'C8'],
    ['D1', 'D2', 'D3', 'D4', 'D5', 'D6', 'D7', 'D8'],
    ['E1', 'E2', 'E3', 'E4', 'E5', 'E6', 'E7', 'E8'],
    ['F1', 'F2', 'F3', 'F4', 'F5', 'F6', 'F7', 'F8'],
    ['G1', 'G2', 'G3', 'G4', 'G5', 'G6', 'G7', 'G8'],
    ['H1', 'H2', 'H3', 'H4', 'H5', 'H6', 'H7', 'H8'],
  ];

  var table = document.getElementById('seat-map');
  table.innerHTML = '';
  var nome = document.getElementById('film-name');
  nome.innerHTML = film;

  for (var i = 0; i < seats.length; i++) {
    var row = document.createElement('tr');
    for (var j = 0; j < seats[i].length; j++) {
      var seat = document.createElement('td');
      seat.textContent = seats[i][j];
      seat.addEventListener('click', function() {
        toggleSeatSelection(this);
      });
      row.appendChild(seat);
    }
    table.appendChild(row);
  }
  proiezione = film;
  selectedSeats = [];
}

function toggleSeatSelection(seat) {
  seat.classList.toggle('selected');
  var seatId = seat.textContent;

  // Aggiungi o rimuovi il posto selezionato dalla lista
  if (selectedSeats.includes(seatId)) {
    selectedSeats = selectedSeats.filter(function(item) {
      return item !== seatId;
    });
  } else {
    selectedSeats.push(seatId);
  }
}

function generateBookingKey() {
  var timestamp = Date.now();
  var random = Math.random().toString(36).substring(2, 15);
  var bookingKey = timestamp + '_' + random;

  // Imposta il cookie con la chiave generata
  document.cookie = 'bookingKey=' + bookingKey + '; path=/; SameSite=None; Secure';
}

function getBookingKey() {
  var name = 'bookingKey=';
  var decodedCookie = decodeURIComponent(document.cookie);
  var cookieArray = decodedCookie.split(';');

  for (var i = 0; i < cookieArray.length; i++) {
    var cookie = cookieArray[i].trim();
    if (cookie.indexOf(name) === 0) {
      return cookie.substring(name.length);
    }
  }

  return '';
}

function prenotaPosti() {
   var xhr = new XMLHttpRequest();
  var url = 'http://localhost:8080/prenota';
  var data = JSON.stringify({ film: proiezione, key: proiezione + getBookingKey(), posti: selectedSeats });

  xhr.open('POST', url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 201) {
        alert('Prenotazione effettuata con successo!');
      } else if (xhr.status === 400){
		alert('Nessun posto selezionato!');
	  } else {
        alert('I posti sono gia prenotati oppure hai gia una prenotazione attiva, per favore utilizzare il tasto aggiorna prenotazione!');
      }
    }
  };

  xhr.send(data);
}

function aggiornaPrenotazione() {
   var xhr = new XMLHttpRequest();
  var url = 'http://localhost:8080/prenota/aggiorna';
  var data = JSON.stringify({ film: proiezione, key: proiezione + getBookingKey(), posti: selectedSeats });

  xhr.open('PUT', url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 201) {
        alert('Prenotazione effettuata con successo!');
      } else {
        alert('Nessuna prenotazione da aggiornare.');
      }
    }
  };

  xhr.send(data);
}

function eliminaPrenotazione() {
   var xhr = new XMLHttpRequest();
  var url = 'http://localhost:8080/prenota/elimina';
  var data = JSON.stringify({ film: proiezione, key: proiezione + getBookingKey() });

  xhr.open('POST', url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        alert('Prenotazione eliminata con successo!');
      } else {
        alert('Nessuna prenotazione presente.');
      }
    }
  };

  xhr.send(data);
}




// Genera la chiave e memorizzala come cookie al caricamento della pagina
window.onload = function() {
  var bookingKey = getBookingKey();
  if (!bookingKey) {
    generateBookingKey();
  }
  
  var xhr = new XMLHttpRequest();
  var url = 'http://localhost:8080/elenco_proiezioni';
    
	xhr.open('GET', url , true);
	
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var response = JSON.parse(xhr.responseText);
        var JSONResponse = JSON.parse(response);
		var proiezioni = JSONResponse.proiezioni;

        // Popola i nomi dei bottoni con i dati del JSON
        var filmList = document.getElementById('film-list');
        for (var i = 0; i < proiezioni.length; i++) {
          var film = proiezioni[i].film;
          var button = document.createElement('button');
          button.textContent = film;
          button.setAttribute('onclick', "showSeats('" + film + "')");
          var listItem = document.createElement('li');
          listItem.appendChild(button);
          filmList.appendChild(listItem);
        }
      }
    };
    xhr.send();
};