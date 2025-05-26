# Exam API REST - Task Management API

Ce projet est une API RESTful pour la gestion de tâches (**Task Management API**) en Java avec **Jetty embarqué**. Il fournit les opérations complètes du **CRUD** (Create, Read, Update, Delete) pour l'entité `Task`.

---

## ⚙️ Technologies et outils

- Java 21
- Hibernate ORM
- Jersey (JAX-RS) pour l'API REST
- Jetty Server (serveur embarqué)
- MySQL (en local ou via Docker)

---

## 🗂️ Arborescence

```
exam-api-rest/
│   .gitignore
│   pom.xml
│   README.md
│   tree.txt
│   
├───.idea
│       .gitignore
│
├───.mvn
├───annexes
│       Cahier des charges.md
│
├───docker
│       docker-compose.yml
│       Dockerfile
│       wait-for-it.sh
│
├───requests
│       TaskRequets.http
│
└───src
    └───main
        ├───java
        │   └───com
        │       └───humanbooster
        │           │   App.java
        │           │
        │           ├───config
        │           │       HibernateConfig.java
        │           │       ObjectMapperProvider.java
        │           │       ServerConfig.java
        │           │
        │           ├───controller
        │           │       GenericController.java
        │           │       GenericControllerImpl.java
        │           │       TaskController.java
        │           │
        │           ├───dao
        │           │       GenericDao.java
        │           │       GenericDaoImpl.java
        │           │       TaskDao.java
        │           │
        │           ├───factory
        │           │       DataFactory.java
        │           │
        │           ├───model
        │           │       Task.java
        │           │
        │           └───service
        │                   TaskService.java
        │
        └───resources
                hibernate.cfg.xml

```

---

## 🚀 Lancer le projet

### 1️⃣ Via **Docker** (recommandé)

1. Ouvrir un terminal et se positionner dans le dossier `docker` :
   ```bash
   cd docker
   ```

2. Construire et démarrer les conteneurs :
   ```bash
   docker compose up --build app
   ```

> Le port de l’API sera exposé en local sur **[http://localhost:3000/api](http://localhost:3000/api)**.

---

### 2️⃣ En **local** (sans Docker)

Pour utiliser une base de données MySQL locale, modifier la variable suivante dans la classe `App` :

```java
public static final boolean LOCAL_ENVIRONMENT = true;
```

- `true` : Utilisation de la base MySQL locale (127.0.0.1:3306).
- `false` : Utilisation de la base MySQL dans le conteneur Docker (par défaut).

Compilation et exécution de l'application avec Maven :
```bash
mvn clean package
java -jar target/exam-api-rest-1.0-SNAPSHOT.jar
```

---

## 📦 Endpoints principaux

| Méthode | URL                 | Description                    |
| ------- | ------------------- | ------------------------------ |
| GET     | `/api/tasks`        | Liste toutes les tâches        |
| GET     | `/api/tasks/{id}`   | Récupère une tâche spécifique  |
| POST    | `/api/tasks`        | Crée une nouvelle tâche        |
| PUT     | `/api/tasks/{id}`   | Met à jour une tâche existante |
| DELETE  | `/api/tasks/{id}`   | Supprime une tâche             |


---

## 📜 Informations complémentaires

- Le projet initialise 5 tâches par défaut à chaque démarrage.
- Les scripts de requêtes sont disponibles dans le dossier `requests/`.
