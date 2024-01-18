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

**24/12/2023** Chen  
Ajouter un class pour API(pas encore test)  
Ajouter event et lisenter dans ce project pour le synchronisation des données en mémoire et des fichiers  

Examen du code: Passed

**29/12/2023** Rémi
Fxml HomePage, NewPortfolio, ClonePortfolio, PortfolioInformation, AssetInformation, et autres finies, controller à compléter
Le menuController est aussi à compléter
peut etre modifier les pages pour que le menu contienne les boutons sell et buy asset / crypto distinct

**11/01/2024** Rémi
Switch page fait dans App, App extends dans HomeController, BuyAssetController, BuyCryptoController, SellAssetController, 
SellCryptoController et AnalysisController pour les swtch page effectifs.

**12/01/2024** Chen
Essayer de faire la partie de AddAssetController, mais vu quelques
bug. J'ai refait les event

**14/01/2024** Rémi
BuyAsset et SellAssetController faits

**16/01/2024** Chen
Fini les tests de Controleur, et puis toutes les erreurs sont commentées.

1. Les sections suivantes restent aujourd'hui inachevées
2. Nécessité d'informer l'utilisateur lorsqu'une condition limite est déclenchée
3. Représentation de la répartition des actifs sous forme de diagrammes circulaires
4. Données historiques sur les actions via des graphiques linéaires
5. Afficher les détails des actifs sélectionnés dans l'interface utilisateur
Afficher des informations sur l'utilisateur

**17/01/2024** Rémi
Ajout de fonction au controller BuyAsset
changement des pages fxml

**18/01/2024** Rémi
Déconnexion faite
