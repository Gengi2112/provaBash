#!/bin/bash

create_folder() {
  folder_name="$1"
  if [ -d "$folder_name" ]; then
    rm -rf "$folder_name"
  fi
  mkdir "$folder_name"
}

create_folder "Pari"
create_folder "Dispari"

touch "Pari/min50.yaml"
touch "Pari/mag50.yaml"

for i in $(seq 2 2 48); do
  echo "$i" >> "Pari/min50.yaml"
done

for i in $(seq 52 2 100); do
  echo "$i" >> "Pari/mag50.yaml"
done

touch "Dispari/min50.yaml"
touch "Dispari/mag50.yaml"

for i in $(seq 1 2 49); do
  echo "$i" >> "Dispari/min50.yaml"
done

for i in $(seq 51 2 99); do
  echo "$i" >> "Dispari/mag50.yaml"
done

echo "Script completato."