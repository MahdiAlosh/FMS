# Flottenmanagementsystem (FMS) für UGVs

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green)

Ein Backend-System zur Verwaltung und Steuerung einer Flotte unbemannter Fahrzeuge (UGVs).

## 1. Modul- und Projektbeschreibung

### Modul: Programmierung von Komponentenarchitekturen (I267)
Dieses Projekt wurde im Rahmen des Moduls "Programmierung von Komponentenarchitekturen" unter der Leitung von Prof. Dr. Mario Neugebauer und Dipl.-Medieninf. Alexander Wülfing entwickelt. Das Modul vermittelte fortgeschrittene Konzepte der Softwarearchitektur mit Fokus auf:

- Komponentenorientierung
- Schnittstellendesign
- Moderne Architekturmuster
- [Anforderungen](src/main/java/01-Anforderungen.pdf)

### Projekt: Flottenmanagementsystem für UGVs
Das Flottenmanagementsystem (FMS) ist eine Backend-Lösung zur Verwaltung und Steuerung einer Flotte unbemannter Fahrzeuge (Unmanned Guided Vehicles - UGVs). 

**Kernfunktionen:**
- Echtzeit-Verfolgung von Fahrzeugpositionen
- Verwaltung der UGV-Flotte (Hinzufügen/Entfernen von Fahrzeugen)
- Routenplanung und dynamische Aufgabenzuweisung
- Kommunikation und Koordination zwischen UGVs
- Leistungsüberwachung und Diagnose
- Sicherheitsmechanismen gegen unbefugten Zugriff
- Skalierbarkeit für wachsende Flotten

## 2. Angewandte Technologien

### Backend-Stack
- **Spring Boot** (Framework für Java-basierte Microservices)
- **Spring Data JPA** (Datenzugriffsschicht)
- **Spring Security mit OAuth 2.0** (Authentifizierung/Autorisierung)
- **Spring Web** (RESTful Web Services)
- **Hibernate** (ORM-Implementierung)

### Datenbank
- **H2 Database** (für Entwicklung und Tests)

### Architekturkonzepte
- **RESTful API** (HTTP/JSON-Schnittstellen)
- **Event Sourcing** (Ereignisbasierte Architektur)
- **CQRS** (Trennung von Lese- und Schreiboperationen)
- **Domain-Driven Design** (Strukturierung der Geschäftslogik)

### Sicherheit
- **OAuth 2.0 mit JWT** (JSON Web Tokens)
- **Role-based Access Control (RBAC)**

## 3. Installation und Betrieb

### Voraussetzungen
- Java 17 JDK
- Maven 3.8+

