# BeaconsDemo
Une application basée sur les Beacons, par Guillaume Vrilliaux.


## Origine du projet
Ce projet à été originalement développé pour l'école CS2I Bourgogne, pour mon projet d'étude de Master 1.
Souhaitant versionner le projet pour de futures modifications, j'ai remplacé le contenu qui pouvait représenter l'école par un contenu neutre. 


## Description du projet
- Développé en java Android
- Utilise la technologie Beacons de la marque Estimote (et donc leur SDK), basé sur le kit de développement de base contenant 3 beacons (Icy Marshmallow, Bluebrry Pie, Mint Cocktail)
- Vous devez disposer des 3 Beacons de base du kit de développement et vous devez vous assurer de leur configuration:
	- Icy Marshmallow: Major = 5526; Minor = 19125; placez votre téléphone à plus de 2 mètres du beacon (longue distance)
	- Mint Cocktail: Major = 1857; Minor = 60524; placez votre téléphone à environ 2 mètres du beacon (moyenne distance)
	- Blueberry Pie: Major = 17828; Minor = 47111; placez votre téléphone au plus prêt du beacon (courte distance)
- Nécessite l'activiation du bluetooth sur le récepteur (smartphone ou tablette)
- Version d'Android recquise: 4.3+ (support du BLE [Bluetooth Low Energy] natif)
- Testé sur Samsung Galaxy S4, avec Android 5.0.1 (Lollipop)


## Installation de l'application sur le téléphone
Deux solutions, soit en passant par Android Studio (ou Eclipse, mais n'utilisant pas cet IDE je ne détaillerai pas sa procédure), soit via l'APK.

1 Avec l'APK:

- Téléchargez et décompressez le ZIP
- Récupérez le dossier /app, copiez le fichier "app-release.apk"
- Collez-le dans votre téléphone dans le répertoire de votre choix (à la racine de votre carte SD par exemple ou dans un dossier "ApplicationsPersos", etc.)
- Sur votre téléphone, dans votre explorateur de fichier, allez à l'emplacement de l'APK et cliquez dessus, faites "installer"
- Il peut vous être demandé d'autoriser l'installation d'applications externes, accepter d'installer cette fois

2 Via Android Studio:

- Récupérez le ZIP et dézippez le dossier du projet
- Ouvrez Android Studio et faites "New > Project > Import Project"
- Sélectionnez le projet à l'emplacement du dossier précédemment dézippé
- Laissez ensuite Android Studio reconstruire le projet grâce au Gradle
- Faites ensuite "Build > Build Project" pour vérifier qu'il n'y ait pas d'erreurs
- Lancez ensuite le projet (flèche verte "Build and Run", sélectionnez votre Device (téléphone ou émulateur))
- Si vous lancez sur votre téléphone/tablette, vérifiez que les options de développeurs sont activées sur celui/celle-ci


## Fonctionnement de l'application

- L'application est composée de 2 activity qui contiennent chacune des fragments.
	- L'activity principale (MainActivity) contient les 3 fragments de base de l'application (accueil, présentation, fonctionnement), et un menu.
	Elle contient aussi un listener qui scanne périodiquement (toutes les 5 secondes dans mon cas) les beacons alentours, et un gestionnaire (classe spécifique que j'ai développée) qui permet de controler l'affichage des 
	notifications selon le temps qui s'écoule en attribuant un timer à chaque beacon (évite les spams pour l'utilisateur). 
	- L'activity secondaire (BeaconActivity) contient les fragments liés aux beacons.
- Selon les beacons détectés, on regarde lequel est le plus proche selon la puissance de son signal, et on récupère son identifiant (UUID, Major, Minor).
- Selon cet identifiant, on vérifie la distance avec le récepteur (sommes-nous dans la zone souhaitée?), on vérifie que l'on ne spam pas l'utilisateur (via le gestionnaire et un timer spécifique).
- On lance la seconde activity qui reçoit l'identifiant du beacon, et affiche alors le fragment correspondant. 


## Améliorations possibles

- Ajouter des commentaires dans le code (La maladie du développeur, désolé ^^)
- Améliorer le code en lui-même
- Ajouter des fonctionnalités (avec 1 beacon de plus, il peut être intéressant de tester l'indoor location, via le SDK d'Estimote)

C'est tout pour le moment :p


Guillaume Vrilliaux
