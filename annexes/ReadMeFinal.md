# 📦 TWIC 2025 - Projet de Rattrapage (Yasmina Alidou)

Ce projet est une application web fullstack de gestion de commandes client, réalisée dans le cadre du module **TWIC 2025**.

## ✨ Fonctionnalités principales

- 🔍 Visualisation des clients et de leurs commandes
- ➕ Création et modification de commandes
- 📦 Consultation des détails de commande
- 📊 Total de commande calculé automatiquement
- 📝 Touche personnelle : système de **"Pop Advice"** côté frontend (données externes)

## ⚙️ Technologies utilisées

### Frontend
- **Angular 20**
- ViteJS + TypeScript


### Backend
- **Spring Boot 3**
- API REST (documentation OpenAPI disponible)
- Controller, DTOs, Services, etc.

### Base de données
- **MariaDB**
- Accès via Spring Data JPA

### CI/CD & Qualimétrie
- Jenkins (pipeline via `Jenkinsfile`)
- SonarQube (analyse statique)
- OpenAPI (`api-docs.json` fourni)

## 🚀 Lancement rapide

1. Lancer la base de données (MariaDB)
2. Démarrer le backend : `mvn spring-boot:run` depuis le dossier `server`
3. Démarrer le frontend :  `npm install` puis `ng serve` dans le dossier `client`

## 🗂️ Structure
- client → Application Angular
- server → API Spring Boot
- Jenkinsfile
- annexes →  OpenAPI, captures SonarQube, etc.

## 🙋‍♀️ Réalisé par
**Yasmina Alidou** – TWIC 2025 – ESEO

