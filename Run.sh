#!/bin/sh
#Waiting for Mysql
while ! nc -z $DB_HOST $DB_PORT; do
  echo "Esperando a que MySQL se inicie en $DB_HOST:$DB_PORT..."
  sleep 3
done

echo "Mysql iniciado correctamente, iniciando la aplicacion"
java -jar app.jar
