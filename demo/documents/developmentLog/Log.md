# Journal des mises à jour  

> Après chaque mise à jour de git, veuillez indiquer ici la date, ce qui a été modifié et la quantité modifiée. Et le résultat de la revue de code après chaque mise à jour.  
> Tout le français a été traduit par google, donc si quelque chose n'est pas clair, n'hésitez pas à me le faire savoir !


**29/11/2023** Chen  
Création de l'ensemble du projet, achèvement de la première version du document d'exigences et conception de l'architecture du logiciel conformément au document d'exigences.
Réalisation de la première version du diagramme des processus d'entreprise.  

Examen du code : aucun  

**30/11/2023** Chen  
Fini la première version des diagrammes de classe UML et commencé à écrire la première phase des documents de développement, il y a encore des choses inachevées, nous en discuterons ensemble vendredi  

Crée une fenêtre de connexion simple et remplace l'ID utilisateur par un entier au lieu d'un Long.

Examen du code : aucun  

**02/12/2023** Chen  
Une démo MVC complète pour la connexion ou l'enregistrement d'un utilisateur, qui permet de comprendre le fonctionnement interne du code.
Et encapsuler certains des outils qui peuvent être utilisés, rendra le développement ultérieur plus simple.
Changement du framework back-end, pas d'utilisation de spring, à la place de springboot, les changements spécifiques seront reflétés dans la documentation technique.  

Ajout de quelques ToDos, veuillez utiliser la fonction de recherche de l'IDE pour trouver les ToDos et les lire.  

Ajout de documents technique, merci de le voir  

Examen du code : aucun

**04/12/2023** Chen  
Fini UML et écrire un document de conception détaillée.  

Examen du code : aucun  

**07/12/2023** Rémi  
Mis à jour le V1.0 UI  
Examen du code : aucun  

**12/12/2023** Chen  
Ajout le serviec et repository de portfolio. Au lieu d'utiliser la réflexion pour créer un nouvel objet au début, changer à le modèle de la fabrique.  

Examen du code : Opérations de découplage sur les repository.

**15/12/2023** Chen
Fini les tests unitair de AssetService et PortfolioService  

Examen du code : aucun

**16/12/2023** Chen
Fini les tests unitair de factory

Examen du code : aucun

**22/12/2023** Chen
Ajouter un userSession pour conserver les données en mémoire  
Cependant, un nouveau problème se pose : les données de la mémoire et du fichier ne sont pas synchronisées. J'ai donc choisi de créer une nouvelle classe de service pour synchroniser les données  

**22/12/2023** Rémi
Début du FXML

**29/12/2023** Rémi
Fxml HomePage, NewPortfolio fini, controller à compléter
**24/12/2023** Chen  
Ajouter un class pour API(pas encore test)  
Ajouter event et lisenter dans ce project pour le synchronisation des données en mémoire et des fichiers  

Examen du code: Passed
