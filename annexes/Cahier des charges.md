# Cahier des charges – TP : API REST Java avec CRUD complet via Jetty

## **1. Contexte**

Dans le cadre d’un développement interne, vous devez créer une **API RESTful** pour la gestion de tâches (*Task Management API*) intégrée dans un serveur embarqué. L’objectif est de livrer une version fonctionnelle et bien structurée permettant la gestion complète des entités `Task`.

L’API devra exposer toutes les opérations du **CRUD** (Create, Read, Update, Delete), et respecter les conventions REST.

---

## **2. Objectifs pédagogiques**

- Maîtriser la création d’un projet Maven.
- Utiliser **Jetty embarqué** comme serveur HTTP.
- Structurer une API REST proprement avec Java.
- Implémenter un **CRUD complet** via des Servlets.
- Sérialiser/désérialiser en JSON avec **Jackson**.
- Gérer proprement les erreurs HTTP et les statuts.

---

## **3. Spécifications fonctionnelles**

### 3.1. Entité métier – `Task`

| Champ | Type | Règle de gestion |
| --- | --- | --- |
| `id` | `int` | Généré automatiquement (auto-incrément) |
| `title` | `String` | Obligatoire, non vide |
| `description` | `String` | Optionnelle |
| `done` | `boolean` | `false` par défaut |
| `createdAt` | `LocalDateTime` | Généré à la création |
| `updatedAt` | `LocalDateTime` | Mis à jour lors d’un `PUT` |

---

## **4. Endpoints requis**

| Méthode | URI | Description | Corps attendu | Réponse attendue |
| --- | --- | --- | --- | --- |
| GET | `/tasks` | Liste toutes les tâches | - | 200 + liste JSON |
| GET | `/tasks/{id}` | Retourne une tâche spécifique | - | 200 ou 404 |
| POST | `/tasks` | Crée une nouvelle tâche | JSON (sans id) | 201 + tâche créée |
| PUT | `/tasks/{id}` | Met à jour une tâche existante | JSON complet | 200 ou 404 |
| DELETE | `/tasks/{id}` | Supprime une tâche | - | 204 ou 404 |

### ❗ Notes :

- **Status 404** si la tâche n'existe pas.
- **Status 400** si `title` est vide ou manquant.
- **Status 201** après création.
- Utiliser des en-têtes `Content-Type: application/json`.

---

## **5. Contraintes techniques**

- Utiliser **Jetty** lancé depuis une classe `Main`.
- Pas de frameworks tiers (pas de Spring, JAX-RS, etc.).
- Les identifiants doivent être **générés automatiquement**.
- L’API doit gérer les erreurs HTTP de manière explicite.
- Code structuré, clair, bien découpé.