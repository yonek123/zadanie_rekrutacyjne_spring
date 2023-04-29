# Kompilacja i utworzenie pliku .jar

Aby skompilować projekt i utworzyć plik jar należy uruchomić skrypt "compile.cmd"

# Uruchomienie bazy danych:

Do uruchomienia aplikacji potrzebna jest baza danych HSQLDB. Konfiguracja bazy danych:
    typ bazy danych: HSQL Database Engine Server
    Driver: org.hsqldb.jdbc.JDBCDriver
    URL: jdbc:hsqldb:hsql://localhost/xdb
    User: SA
    Password:

Przykładowa komenda uruchamiająca HSQLDB z podanymi ustawieniami z katalogu "hsqldb-2.7.1\hsqldb\data":
    java -cp ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 xdb

# Uruchomienie aplikacji z poziomu cmd:
java -jar zadanie\target\zadanie-0.0.1-SNAPSHOT.jar

# Przykładowe wywołania usług przy pomocy polecenia curl
## Utworzenie reserwacji
curl -v -d "{ \"reservationStart\": \"2099-05-01\", \"reservationEnd\": \"2099-12-01\", \"ownerId\": 1, \"lesseeId\": 2, \"reservedFacilityId\": 1 }" -H "Content-Type: application/json" http://localhost:8080/api/reservation

## Edycja reserwacji
curl -v -d "{ \"reservationId\": 1, \"reservationStart\": \"2121-05-01\", \"reservationEnd\": \"2121-12-01\", \"ownerId\": 3, \"lesseeId\": 1, \"reservedFacilityId\": 1 }" -H "Content-Type: application/json" -X PUT http://localhost:8080/api/reservation/1

## Pobranie listy rezerwacji dla zadanego najemcę (nazwa)
curl -v http://localhost:8080/api/reservation/lessee/bob

## Pobranie listy rezerwacji dla danego obiektu
curl -v http://localhost:8080/api/reservation/facility/budynek2