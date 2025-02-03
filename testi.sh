#!/bin/bash

# Chiedi all'utente il nome della cartella
read -p "Inserisci il nome della cartella: " nome_cartella

# Verifica se la cartella esiste già
if [ -d "$nome_cartella" ]; then
  echo "La cartella '$nome_cartella' esiste già."
else
  # Crea la cartella
  mkdir "$nome_cartella"
  echo "Cartella '$nome_cartella' creata con successo."
fi

touch "$nome_cartella/$nome_file1"
touch "$nome_cartella/$nome_file2"

# Inserisci del testo nei file (opzionale)
echo "Testo per $nome_file1" > "$nome_cartella/$nome_file1"
echo "Testo per $nome_file2" > "$nome_cartella/$nome_file2"

echo "File '$nome_file1' e '$nome_file2' creati nella cartella '$nome_cartella'."

# (Opzionale) Mostra il contenuto dei file
cat "$nome_cartella/$nome_file1"
echo ""
cat "$nome_cartella/$nome_file2"
