#!/bin/bash
set -e

# Attendre que MongoDB soit disponible
until nc -z -v -w30 mongodb 27017; do
    echo "En attente de MongoDB..."
    sleep 5
done

echo "MongoDB est disponible !"

# Démarrer l'application
java -jar /app/iot-management-api.jar
