Для развертывания локальной базы данных можно использовать команду:

    docker run -p 8432:5432 -e "POSTGRES_DB=taskmanager_db" 
    -e "POSTGRES_USER=taskmanager_user" -e "POSTGRES_PASSWORD=123" 
    postgres:15.7-alpine

