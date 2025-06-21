# Touche personnelle – Fonction "Pop Advice"

## Description
J’ai ajouté une fonctionnalité bonus au projet : l’affichage d’un **"Pop Advice"** provenant d’une source de données externe.

Cette fonctionnalité permet d’enrichir l’interface utilisateur avec une citation ou un conseil original à chaque visite sur la page search.

## Détails techniques

### Backend :
- Un contrôleur REST `AdviceController` expose un endpoint `GET /api/v1/advice`.
- L’advice est récupéré dynamiquement depuis un fichier ou généré aléatoirement.

### Frontend :
- Une carte spéciale s’affiche sur la page d’accueil avec l’advice du moment.

## Technologies utilisées
- Spring Boot + REST Controller
- Angular HttpClient pour requêter le backend
