# Projet_Microservice
## Déploiement du projet à l'aide de Docker

Récupération des sources :

git clone https://github.com/NaimBendjebbour/Projet_Microservice.git

Compilation : 
Pour chacun des services placez vous à la racine du projet et exécutez :
mvn clean install -DskipTests

Construction du conteneur : 
à la racine de chaque projet exécutez :

>docker build -f Dockerfile -t serviceoperation .

>docker build -f Dockerfile -t servicecompte .

Lancement du conteneur :

>docker run -p 8010:8010 serviceoperation

>docker run -p 8011:8011 servicecompte

Mapping service Compte et Operation : 

METHODE	URL	DESCRIPTION

>GET	/compte/{id}	Récupérer un compte

>GET	/compte/all	Récupérer liste des compte

>POST	/compte/add	Ajouter un compte

>Put	/compte/update/{id}	Mise à jour du compte

>Delete	/compte/delete/{id}	Supprimer un compte



>GET	/operation/{id}	Récupérer une operation

>GET	/operation/all	Récupérer liste des operations

>POST	/operation/add	Ajouter une operation

>Put	/operation/update/{id}	Mise à jour d’une operation

>Delete	/operation/delete/{id}	Supprimer une operation
