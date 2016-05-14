# BeaconsDemo
©2016 Guillaume Vrilliaux


## Origine du projet
Ce projet à été originalement développé pour l'école CS2I Bourgogne, lors du projet étudiant dirigé par moi-même (Guillaume vrilliaux), en Master 1.
Souhaitant partager mon projet et le versionner dans le cas de modifications futures, j'ai donc remplacer le contenu qui pouvait représenter l'école par un contenu neutre pour éviter tout plagia. 


## Description du projet
- Développé en java Android
- Utilise la technologie Beacons de la marque estimote (et donc leur SDK) => Basé sur le kit de développement de base contenant 3 beacons (Icy Marshmallow, Bluebrry Pie, Mint Cocktail)
- Vous devez disposez des 3 Beacons de base du kit de développement pour que les notifications spécifiques s'affichent et vous devez vous assurer pour les Beacons suivant:
	- Icy Marshmallow: Major = 5526; Minor = 19125; placez votre téléphone à plus de 2 mètres du beacon (longue distance)
	- Mint Cocktail: Major = 1857; Minor = 60524; placez votre téléphone à environ 2 mètres du beacon (moyenne distance)
	- Blueberry Pie: Major = 17828; Minor = 47111; placez votre téléphone au plus prêt du beacon (courte distance)
- Nécessite l'activiation du bluetooth sur le récepteur (smartphone ou tablette)
- Version d'Android recquise: 4.3+ (support du BLE [Bluetooth Low Energy] natif)
- Testé sur Samsung Galaxy S4, avec Android 5.0.1 (Lollipop)


## Installation de l'application sur le téléphone
Deux solutions, soit en passant par Android Studio (ou Eclipse, mais n'utilisant pas cet IDE je ne détaillerai pas sa procédure), soit en utilisant juste l'APK.

1. Avec l'APK:
- Téléchargez et décompressez le ZIP
- Récupérez le dossier /app, copiez le fichier "app-release.apk"
- Collez-le dans votre téléphone dans le répertoire de votre choix (à la racine de votre carte SD par exemple ou dans un dossier "ApplicationsPersos", etc.)
- Sur votre téléphone, dans votre explorateur de fichier, allez à l'emplacement de l'APK et cliquez dessus, faites "installer"
- Il peut vous être demandé d'autoriser l'installation d'applications externes, accepter d'installer cette fois

2. Via Android Studio:
- Récupérez le ZIP et dézippez le dossier du projet
- Ouvrez Android Studio et faites "New > Project > Import Project"
- Sélectionnez le projet à l'emplacement du dossier précédemment dézippé
- Laissez ensuite Android Studio reconstruire le projet grâce au Gradle
- Faites ensuite "Build > Build Project" pour vérifier qu'iol nj'y ait pas d'erreur
- Lancez ensuite le projet (flèche verte "Build and Run", puis sélectionnez votre Device (téléphone ou émulateur) où vous souhaitez installer le projet)
- Si vous lancez sur votre téléphone, vérifiez que les options de développeurs sont activées sur ce dernier


## Fonctionnement de l'application

- L'application est composée de 2 activity qui contiennent chacune des fragments.
	- L'activity principale (MainActivity) contient les 3 fragments de base de l'application (accueil, présentation, fonctionnement), un menu (en glissant sur l'écran vers la droite, pour qu'il aparaisse).
	Elle contient aussi un listener qui Scan périodiquement (toutes les 5 secondes dans mon cas) les beacons alentours, et un gestionnaire (classe spécifique que j'ai développée) qui permet de controler l'affichage des 
	notifications selon le temps qui s'écoule (évite de spammer l'utilisateur); cela en attribuant un timer à chaque beacon. 
	- L'activity secondaire (BeaconActivity) contient les fragments spécifiques aux beacons dont on dispose.
- Selon les beacons détecté, on regarde lequel est le plus proche selon la puissance de son signal, et on récupère son identifiant (UUID, Major, Minor).
- Selon cet identifiant, on vérifie la distance avec le beacon (sommes-nous dans la zone adéquate prédéfinie?), et on vérifie que l'on ne spam pas l'utilisateur (via le gestionnaire et un timer spécifique).
- On lance la seconde activity qui reçoit l'identifiant du beacon, et affiche alors le fragment correspondant. 


## Améliorations possibles

- Ne pas se contenter du beacon le plus proche, lorsqu'un beacon est détecté et que sa notification a déjà été affichée, il serait intéressant de passer au suivant (ajout d'un incrément sur la liste de beacons)
- Ajouter des commentaires dans le code (La maladie du développeur, désolé ^^)
- Améliorer le code en lui-même (c'est le projet sur lequel j'ai vraiment comencé de travailler sur Android, il y a certainement des choses à optimiser)
- Ajouter des fonctionnalités (avec 1 beacon de plus, il peut être intéressant de faire de l'indoor location, via le SDK d'Estimote, à tester!)

C'est tout pour le moment :p


Guillaume Vrilliaux