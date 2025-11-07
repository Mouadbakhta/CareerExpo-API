
# README - API CareerExpo

## 📋 Description
API REST pour la gestion de Career Expo, développée avec Spring Boot et déployée avec Docker.

---

## 🛠️ Technologies Utilisées

- **Java 21**
- **Spring Boot 3.x**
- **Maven**
- **Docker & Docker Compose**
- **MySQL**

---

## 🚀 Démarrage Rapide

### Prérequis

- Java 21 JDK
- Maven 3.6+
- Docker & Docker Compose
- MySQL (si exécution locale sans Docker)

### Installation et Déploiement

#### 1. Compilation de l'Application
```bash
mvn clean package
```

Cette commande génère le fichier JAR exécutable : `target/careerexpo-API-0.0.1-SNAPSHOT.jar`

#### 2. Construction de l'Image Docker
```bash
docker build -t careerexpo-api:v1 .
```

#### 3. Lancement du Conteneur
```bash
docker run -d -p 8080:8080 --name careerexpo-app careerexpo-api:v1
```

#### 4. Vérification
```bash
docker ps
```

L'API est maintenant accessible sur : `http://localhost:8080`

---

## Configuration Docker

### Dockerfile
```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/careerexpo-API-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

** Important :** Assurez-vous que la version Java du Dockerfile correspond à celle utilisée pour la compilation (Java 21).

### Docker Compose

Pour lancer l'application avec la base de données :
```bash
docker-compose up -d
```

Pour arrêter et supprimer les conteneurs :
```bash
docker-compose down
```


## 🔍 Commandes Docker Utiles

| Commande | Description |
|----------|-------------|
| `docker ps` | Liste les conteneurs actifs |
| `docker ps -a` | Liste tous les conteneurs (actifs et arrêtés) |
| `docker logs careerexpo-app` | Affiche les logs de l'application |
| `docker logs careerexpo_mysql` | Affiche les logs de MySQL |
| `docker rm careerexpo-app` | Supprime le conteneur |
| `docker-compose up -d` | Lance tous les services en arrière-plan |
| `docker-compose down` | Arrête et supprime tous les conteneurs |

---

## 🛠️ Résolution de Problèmes

### Le conteneur s'arrête immédiatement

**Problème :** Incompatibilité de version Java

**Solution :**
1. Vérifier les logs :
```bash
   docker logs careerexpo-app
```

2. S'assurer que le Dockerfile utilise Java 21 :
```dockerfile
   FROM eclipse-temurin:21-jre-alpine
```

3. Reconstruire l'image :
```bash
   docker rm careerexpo-app
   docker build -t careerexpo-api:v1 .
   docker run -d -p 8080:8080 --name careerexpo-app careerexpo-api:v1
```

### Problèmes de connexion à la base de données

1. Vérifier les logs MySQL :
```bash
   docker logs careerexpo_mysql
```

2. Redémarrer les services :
```bash
   docker-compose down
   docker-compose up -d
```

---

## 📁 Structure du Projet
```
careerexpo-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/careerexpo/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── target/
│   └── careerexpo-API-0.0.1-SNAPSHOT.jar
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---
## ✅ Checklist de Déploiement

- [ ] Java 21 installé
- [ ] Maven installé
- [ ] Docker installé
- [ ] Compilation réussie (`mvn clean package`)
- [ ] Image Docker construite
- [ ] Conteneur lancé avec succès
- [ ] API accessible sur `http://localhost:8080`
- [ ] Base de données connectée
- [ ] Endpoints testés

---
