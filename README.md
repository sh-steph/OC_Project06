<details>
  <summary>Français</summary>

# Projet Monde de Dév (MDD)

Ce projet a été développé dans le cadre d'une formation où l'étudiant doit développer l'entièreté de l'application (front-end et back-end) sur Angular et Springboot.

Le but du projet est développer un **MVP (Minimal Viable Product)** qui est une version fonctionnelle et élémentaire du produit ayant pour but de présenter sa version la plus basique aux consommateurs pour l’améliorer petit à petit.

Le projet **MDD** est réseau social dédié aux développeurs permettant de les aider à trouver un travail via la mise en relation entre les développeurs et donc les aider à élargir leurs réseaux professionnels mais également à contruire une espace communautaire.

## Par où commencer ?

Pour la partie back du projet, il vous faudra tout d'abord exécuter la commande suivante `docker-compose up` à la racine du projet afin de générer la base de donnée à l'aide de docker, puis importer le dossier **back-end** dans votre IDE dédié (IntelliJ, Eclipse...).

Avant de `build` et `run` l'application, veuillez tout d'abord paramétrer les **variables d'environnements** de votre IDE afin que l'application puisse interagir avec la **base de données** dont les variables en question se situent dans le fichier **application.properties** (les valeurs sont paramétrées aux préalables dans le **docker-compose.yml**).

DB_USER=`oc_user`
DB_PASSWORD=`oc_pwd`

Pour la partie front du projet, vous allez dans le dossier **front-end** pour générer le **node_module** en exécutant la commande suivante `npm install`.
Une fois l'installation complète, executer la commande `npm start` pour exécuter l'application et naviguer sur l'URL fourni (l'URL par défaut `http://localhost:4200/`).

<details>
  <summary>Organisation de développement</summary>

## Kanban

<img src='/resources/images/kanban.png' width='500'/>

Suite à une lecture des spécifications, chaque **issue** (ticket) correspond à une fonctionnalité de l'application et donc à une branche qui lui est spécifique dont le premier numéro du ticket correspond à une partie de l'application.

Bien entendu, le nombre de tickets dépendent du développement en question et de son avancement (nombre de fonctionnalité additionnelle nécessaire, bug rencontré...).

Ce qui résulte à l'historique suivant à travers les différents commit détaillant brièvement les modifications apportées.

<img src='/resources/images/branch-git.png' width='500'/>

</details>

<details>
  <summary>Développement</summary>

## Back-end

Dans le cadre du projet, l'étudiant doit mettre en place une base de données permettant de répondre aux besoins de l'application, également mettre en place l'ensemble des API nécessaires permettant d'enrichir la base de données, ainsi que de la sécuriser en implantant l'authentification via par le **JWT (JSON Web Token)**.

- `GET` : Mapping permettant de recevoir les données.
- `POST` : Mapping permettant de d'enrichir la base de données.
- `PUT` : Mapping permettant de mettre à jour une donnée.
- `DELETE` : Mapping permettant de supprimer une donnée.

### Base de donnée (MySQL)

| Tables        | Description                                                   |
| ------------- | ------------------------------------------------------------- |
| Users         | Donnée des utilisateurs pour l'authentification et le profil. |
| Posts         | Contenu des articles abordant un thème spécifique.            |
| Themes        | Thème du sujet à aborder.                                     |
| Comments      | Contenu des commentaires d'un utilisateur liés à un article.  |
| Subscriptions | les abonnements aux différents thèmes d'un utilisateur.       |

### API REST

Vous trouverez l'ensemble des API dans un fichier `postman` se trouvant à la racine du projet dans le dossier `/ressources/postman`.

<img src='/resources/images/postman.png' width='500'/>

Le choix des url pour les API ont été conçues en essayant de prendre en compte le projet sur le long terme afin d'obtenir les informations voulues en ayant le temps de réponse le plus court possible.

En prenant l'exemple de la requête **get all comments from a post** ayant pour url `/api/comment/post/1`, la requête cherchera uniquement les commentaires liés à l'article dont l'id vaut 1 au lieu de chercher tous les commentaires existants afin d'écourter le temps de réponse.

## Front-end

Dans le cadre du projet, l'étudiant doit développer l'ensemble du front-end en suivant une maquette fournie par les formateurs exigeant donc à suivre plusieurs **spécificités fonctionnelles** tel que les codes couleurs, la conception des différentes pages en fonction de la taille de l'écran (mobile ou ordinateur) et donc d'être **responsive**.

### Librairie de composants visuels

- `Bootstrap` est l’une des meilleures librairies visuelles dans un écosystème du développement dû à sa popularité auprès de la communauté des développeurs, de sa flexibilité sur ses possibilités de personnalisation et de mise en page, ainsi que de sa compatibilité sur la majorité des navigateurs existants.

- `PrimeNG` pour sa simplicité de la mise en place du `side bar` et de son usage intuitif répondant au besoin du projet.

## Résumé

Étant un **MVP**, le but est de développer une application en partant d'aucune base où le développeur doit mettre en place un back-end sécurisé à l'aide de `Spring security` pour accéder et enrichir la base de données à l'aide des différents API à mettre à disposition afin d'établir l'interconnexion entre le front-end et le back-end.

Une fois le back-end mise à disposition, pouvoir être capable d'exploiter les données à l'aide de l'interconnexion afin pouvoir développer cette fois-ci l'interface web du projet avec toutes les fonctionnalités attendues.

</details>

<details>
  <summary>Les dépendances</summary>

| Dépendance      |                                           Lien                                           |
| :-------------- | :--------------------------------------------------------------------------------------: |
| Springboot JPA  |           https://docs.spring.io/spring-data/jpa/docs/current/reference/html/            |
| Spring Security |               https://docs.spring.io/spring-security/reference/index.html                |
| JWT             | https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html |
| Lombok          |                     https://www.baeldung.com/intro-to-project-lombok                     |
| Angular         |                                 https://angular.io/docs                                  |
| Bootstrap       |                            https://getbootstrap.com/docs/5.2                             |
| PrimeNG         |                                   https://primeng.org/                                   |

</details>
</details>
