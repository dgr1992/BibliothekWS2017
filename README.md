Projekt zu Enterprise Computing

Thema:
Computergestützte Bibliotheksverwaltung

Allgemeines:
Dokumentieren Sie die von Ihnen entwickelten Lösungen zu den einzelnen Teilaufgaben durch entspr. Analyse-, Design-, Implementierungs- und Testdokumente. Halten Sie auch fest, was ggf. nicht realisiert wurde, aber für eine vollständige Umsetzung der Problemstellung noch erforderlich wäre. Begründen Sie dabei Ihre Entscheidungen und bewerten Sie diese.
Die Ergebnisse eines Sprints sind in dem darauf folgenden Coaching-Termin von den Team-Mitgliedern zu präsentieren.

1. Implementieren der Serverseite
Programmieren Sie das Backend zu einem Bibliothekssystem in der Programmiersprache Java. Das Backend umfasst eine Datenbank für

   -Ausleihobjekte, Medien (z.B. Bücher, Zeitschriften, CDs, DVDs, Spiele)
   -Themenbasiertes Ordnungssystem, das als Index für Ausleihobjekte dient und den Standort markiert (z.B. Sachbuch, Belletristik, Informatik, Mathematik, Lyrik),
   -Schlagworte,
   -Verlage,
   -Autoren (z.B. Name, Künstlername, Beruf, Lebensdaten),
   -Ausleihdaten (z.B. unterschiedliche Leihfristen, Beschränkungen in der Ausleihe, Verlängerungsmöglichkeiten),
   -Kundendaten,
   -etc.

sowie eine Programmierschnittstelle (API), über die aus Java heraus auf die Daten zugegriffen werden kann.
Überlegen Sie sich, in Absprache mit dem Product Owner, geeignete User-Stories für die Benutzer eines Bibliothekssystems und definieren Sie entsprechende Methoden, mit denen diese User-Stories umgesetzt werden können. Der Schwerpunkt liegt dabei auf dem Umgang mit Büchern. Als zentrale User-Stories können angesehen werden:

   -Suchen von Ausleihobjekten,
   -Ausleihen von Ausleihobjekten,
   -Verlängern von ausgeliehenen Objekten
   -Reservieren von Ausleihobjekten
   -Fernleihe von eigenen Ausleihobjekten durch andere Bibliotheken


Weitere Funktionalitäten, die im Rahmen der zu entwickelnden Applikation vorgesehen sind und als Usee Story beschrieben werden sollten:

   -Authentifizierung

Zu achten ist unter Anderem auf eine gewisse Kompatibilität zu anderen Bibliothekssystemen, um ggf. in zukünftigen Versionen auch Daten von diesen beziehen zu können (nicht Bestandteil des Projekts). Berücksichtigen Sie daher existierende Standards: Recherchieren Sie, welche Normen es für Bibliotheksdatensätze gibt. Als erste Beispiele hierfür seien genannt:

   -Personennamendatei (PND)
   -Gemeinsame Körperschaftsdatei (GKD)
   -Schlagwortnormdatei (SWD)

Diskutieren Sie, welche (standardisierten) Informationen für Ihr System sinnvoll sein könnten und lassen Sie die entsprechenden Daten in Ihr Modell einfließen. Dokumentieren Sie Ihre Ergebnisse.
Füllen Sie die Datenbank mit Testdaten und implementieren Sie einfache Test-Clients, um die Server-seitigen Programmierschnittstellen zu testen.
2. Anbindung an RMI Client
Für die erste Ausbauphase genügt es, eine kleine Zahl an Mitarbeiter-Arbeitsplätzen sowie eine Reihe von PCs, auf denen die Bibliotheksnutzer nach Ausleihobjekten suchen können, lokal in der Bibliothek zur Verfügung zu stellen. Entwickeln Sie dafür einen (GUI) Java-Client, z.B. in JavaFX, der über RMI mit dem Backend kommuniziert. Der Client soll mindestens folgende Funktionalitäten unterstützen:

   -Suchmöglichkeiten nach Ausleihobjekten (z.B. über Autor, Titel, Schlagwort, ISBN und jeweils Teilen und Kombinationen davon)
   -Ausgabe aller relevanten Informationen am Bildschirm, z.B. Buchsignatur, Ausleihinformation
   -Ausleihe eines Ausleihobjekts
   -Rückgabe eines Ausleihobjekts
   -Reservierung eines Ausleihobjekts
   -Verlängern des aktuellen Ausleihzeitraums eines Ausleihobjekts

Um offen zu sein für unterschiedliche Client-Technologien sowie für Änderungen oder Erweiterungen der Funktionalität auf der Client-Seite, ist auf eine lose Kopplung zwischen Client und Server zu achten. Überlegen Sie, welche Auswirkungen diese Anforderung auf das Design Ihrer Applikation, insbesondere auf die Schnittstellen, hat. Ein hilfreiches Pattern könnte in diesem Zusammenhang (Data) Transfer Object (DTO) sein. Dokumentieren Sie die Gründe für Ihre Entscheidungen.
Berücksichtigen Sie bei Ihrer Implementierung Mulit-User Zugriffe!

3. Authentifizierung über LDAP + Autorisierung
Aus Sicherheitsgründen müssen sich die Nutzer des Systems authentifizieren. Implementieren Sie dazu einen entspr. Logon-Screen, der vor der Nutzung des Systems Username und Passwort des Benutzers abfragt und prüft. Die Prüfung auf Korrektheit des Passworts soll über LDAP erfolgen. Da als Benutzer des Systems (in der Testphase) nur Angehörige der Fachhochschule Vorarlberg vorgesehen sind, kann für die Authentifizierung der LDAP-Server der FHV verwendet werden.
Alle Mitglieder aus Ihrem Team sollten im System mit ihrer FH-Benutzerkennung eingetragen sein und entsprechend Zugriff auf das System erhalten.
Die Benutzer des Systems sollen unterschiedlichen Rollen und damit verbundenen Rechten zugewiesen werden (z.B. Admin, Datenpfleger, Ausleihe, Bibliotheksnutzer). Erweitern Sie Ihr System um eine entsprechende Rollenverwaltung. Bei jeder Aktion soll geprüft werden, ob der Nutzer über die notwendigen Rechte verfügt. Die Suche nach Ausleihobjekten soll allen Bibliotheksnutzern ohne Anmeldung offenstehen. Alle anderen Funktionalitäten des Systems sind nur entsprechend autorisierten Bibliotheksmitarbeitern nach Authentisierung möglich. Sehen Sie einen Benutzer mit Username tf-test und (FHV-)LDAP DN "uid=tf-test,ou=special,o=fhv.at" vor, der alle Rechte hat.
Achten Sie beim Design ihrer Lösung darauf, dass die Authentifizierung Ihres Systems nicht durch triviale Angriffe übergangen werden kann und damit eine freie Nutzung des Systems möglich wird.
Implementieren Sie darüber hinaus die User-Story "Stammdaten-Pflege", bei dem (ausschließlich) der Administrator die Möglichkeit hat, Änderungen an zentralen Daten, wie Ausleihfristen, Reservierungsdauer, Höhe des Jahresbeitrags, Höhe der Mahngebühren, vorzunehmen.

4. Weitergabe von Informationen an Messaging-Clients
Im vorliegenden Bibliothekssystem gibt es eine Reihe von Ereignissen, die Aktionen eines Bibliotheksmitarbeiters nach sich ziehen:

   -Eingebuchte Fernleihen müssen von einem Mitarbeiter bearbeitet werden (Ausleihobjekt aus dem Bestand holen und versenden)
   -Nicht zurückgegebene Ausleihobjekte, deren Ausleihfrist verstrichen ist, müssen behandelt werden (Mahnung an Ausleihenden - mehrstufiger Prozess)
   -Rückgabe eines reservierten Ausleihobjekts (Verständigung des Reservierenden)

Bei Eintreten dieser Ereignisse ist vom System an einen Mitarbeiter mit der Rolle "Operator" eine Nachricht zu senden, die von einem Reader im Frontend der Client-Applikation angezeigt wird. Nach Abarbeiten der Nachricht kann diese vom Operator an eine Logging-Queue weitergeleitet werden, die aus Dokumentationszwecken die Ereignisse und deren Bearbeitung archiviert.
Es muss sichergestellt werden, dass die abgesandten Nachrichten den Operator erreichen. Dazu sollen die Nachrichten von einem JMS-Provider verwaltet werden. Der Message-Producer wird über die entsprechenden Ereignisse angestoßen. Der Message-Consumer verbindet sich beim Aktivieren der entsprechenden Client-Applikation automatisch mit der Destination des JMS-Providers.
Passen Sie die zugehörigen User Stories für Ausleihen (Fernleihe), Mahnungverwaltung und Reservierung an und implementieren Sie die entsprechenden Funktionalitäten. Integrieren Sie die erforderlichen Messaging Clients in Ihr System, so dass es ausschließlich für die internen Angestellten mit der Rolle "Operator" zur Verfügung steht. Stellen Sie sicher, dass eine Nachricht immer nur von einem einzigen Mitarbeiter (Operator) bearbeitet wird, auch wenn mehrere Mitarbeiter mit der Rolle "Operator" das System gleichzeitig nutzen.
Diskutieren Sie, welches Messaging-Modell für die Problemstellung am geeignetsten ist und dokumentieren Sie die Vor- und Nachteile sowie Ihre Entscheidung. Dokumentieren Sie darüber hinaus, welche Auswirkungen die Änderungen auf Ihr System haben.

5. Umstellung auf EJB
Da mit dem zunehmendem Zugriff auf das Bibliotheks-Systems über die Fernleihe die Kapazitäten des Servers immer stärker ausgelastet werden und in naher Zukunft auch ein Direktzugriff von Bibliotheksnutzern über das Web vorgesehen ist, soll eine flexiblere und skalierbare Lösung entwickelt werden. Dafür soll die bestehende Applikation auf JavaEE umgestellt werden und die aktuellen serverseitigen Module in entsprechende EJBs umgewandelt werden. Oberste Priorität bei der Umstellung hat die Suchen-, Ausleihen, Verlängern- und Reservieren-User-Story. Diskutieren Sie, ob eine Umstellung der JMS-Teile in Message-Driven Beans sinnvoll ist.
Stellen Sie die vorhandenen Client-Komponenten so um, dass diese aus dem App-Client-Container heraus über den JavaEE-Server mit den EJBs kommunizieren.
Erarbeiten Sie weiterführende Aufgaben des Containers, z.B. im Bereich Security, und untersuchen Sie, inwieweit bestehende Funktionalitäten Ihrer Applikation durch den Container übernommen werden können.
Erstellen Sie entsprechende Archive für Server, Client und Interface-Lib, die unter einem JavaEE Server deployt werden können. Testen Sie Ihre Applikation auf verteilten Hosts für Client und Server und dokumentieren Sie die Installation für PCs unter Windows, wobei sämtliche Voraussetzungen für Installation und Betrieb auch unter verteilten Hosts beschrieben werden.
Erstellen und dokumentieren Sie geeignete Testfälle für Ihr System. Führen Sie die Tests aus und dokumentieren Sie die Ergebnisse.

6. Bereitstellen eines Web-Front-Ends für EJB-Komponenten
Nachdem die Umstellung auf eine EJB-Architektur erfolgreich abgeschlossen wurde, soll das Systems um einen Direktzugang für die Bibliotheksnutzer erweitert werden, so dass durch eine Web-Applikation Ausleihobjekte gesucht werden können. Die wesentlichen Funktionalitäten der Suchen User-Story sollen in die Web-Applikation portiert werden. Eine Implementierung der Verlängern- und Reservieren-User-Story, für die eine Authentisierung erforderlich wäre, ist nicht erforderlich.
Die Umsetzung der Model-2 Architektur kann auf Basis von JSF oder JSP (mit Servlets) erfolgen. Die im App-Client-Container laufende Client-Komponente für die eigene Bibliothek soll intern weiterhin uneingeschränkt funktionieren.

7. Implementieren eines Web Services
Andere Bibliotheken haben Interesse bekundet, die Bestände Ihrer Bibliothek über die Fernleihe zu nutzen. Daher soll der Zugriff auf das Backend Ihres Bibliothek-Systems für andere Bibliotheken geöffnet werden.
Da die Fernleihe mit besonderen Aufwänden und Gebühren verbunden ist, sind Ausleihen über Objekte, die über die Fernleihe verliehen werden, in der Datenbank besonders zu kennzeichnen. Die Fernleihe kann nur von anderen (registrierten) Bibliotheken angefordert werden, d.h. als Entleiher ist die entspr. Bibliothek im System einzutragen und nicht der Name ihres Kunden, in dessen Auftrag die Fernleihe durchgeführt wird. Auf der anderen Seite hat jede registrierte Bibliothek einen eigenen Benutzer-Account mit zugehöriger Rolle, unter der sämtliche Aktionen ausgeführt werden, d.h. eine Authentifizierung ist auch für Biblitheken erforderlich. Zu Abrechnungszwecken muss bei Bibliotheken der Bibliotheksnutzer-Eintrag der Datenbank eine gültige Kontoverbindung aufweisen.
Um die Fernleihe für möglichst viele Bibliotheken anbieten zu können, soll eine entspr. Web-Service-Schnittstelle entwickelt werden, die die anderen Bibliotheken in ihre Anwendung integrieren können. Da die Services vor allem in professionelle Business-Applikationen eingebunden werden sollen, soll der Web-Service auf Basis von SOAP entwickelt werden. Dazu muss die Serverseite auf das entsprechende Protokoll (SOAP) umgestellt werden und eine WSDL-Datei für die zu entwickelnden Clients des Dienstleisters bereitgestellt werden. Die WSDL-Datei soll alle Funktionalitäten für die Suche, das Reservieren und die Ausleihe von Ausleihobjekten beschreiben. Übergeben Sie den anderen Bibliotheken eine Dokumentation mit allen Informationen, die sie benötigen, um einen Web-Service-Client zu entwickeln und zu nutzen.
Für die externen Bibliotheken sollen lediglich die Funktionalitäten für die Fernleihe bereitgestellt werden. Zu implementieren sind daher die User-Stories für die Suche, das Reservieren und die Ausleihe von Ausleihobjekten.
Verwenden Sie JAX-WS für die Realisierung der Web-Services. Berücksichtigen Sie, dass Web-Services zustandslos sind und entwerfen Sie eine geeignete Folge von Web-Services (mit den entsprechenden Parametern), die die User-Stories für die Suche, das Reservieren und die Ausleihe von Ausleihobjekten abdecken.
Überlegen Sie sich entsprechende Test-Szenarien und testen Sie Ihre Services anhand einfacher Test-Clients.
Implementieren Sie neben Java Test-Clients auch Test-Clients, die nicht in Java programmiert sind.
Die Web-Applikation mit dem Direktzugang für die Bibliotheksnutzer und die im App-Client-Container laufende Client-Komponente für die eigene Bibliothek sollen intern weiterhin verwendet werden können.
