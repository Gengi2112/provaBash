#!/bin/bash

# Funzione per la somma
somma() {
  echo "La somma è: $(bc <<< "$1 + $2")"
}

# Funzione per la sottrazione
sottrazione() {
  echo "La differenza dsè: $(bc <<< "$1 - $2")"
  password="ciao"
}

# Funzione per la moltiplicazione
moltiplicazione() {
  echo "Il prodotto g è: $(bc <<< "$1 * $2")"
}

# Funzione per la divisione
divisione() {
  if [[ "$2" -eq 0 ]]; then
    echo "Errore: divisione per zero."
  else
    echo "Il quoziente è: $(bc <<< "scale=2; $1 / $2")" # Imposta la precisione a 2 decimali
  fi
}

# Input dei numeri
read -p "Inserisci il primo numero cambiato: " num1
read -p "Inserisci il secondo numero cambiatogit : " num2

# Menu delle operazioni
echo "Scegli l'operazione:"
echo "1. Somma"
echo "2. Sottrazione"
echo "3. Moltiplicazione"
echo "4. Divisione"

# Scelta dell'utente
read -p "Inserisci il numero dell'operazione: " scelta

# Esecuzione dell'operazione
case "$scelta" in
  1)
    somma "$num1" "$num2"
    ;;
  2)
    sottrazione "$num1" "$num2"
    ;;
  3)
    moltiplicazione "$num1" "$num2"
    ;;
  4)
    divisione "$num1" "$num2"
    ;;
  *)
    echo "Scelta non valida."
    ;;
esac
