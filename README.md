<h1>Projet de programmation répartie </h1>
<h2>Serveur de jeux en réseau </h2>

------
<h3> :palm_tree:Jeux disponibles</h3>
Sur le serveur, il y a 3 jeux disponibles : 

- le pendu réalisé par ZANNIER Chloé
- le jeu des Alumettes réalisé par CELINDANO Adrien
- le Tic Tac Toe réalisé par GAO Chang

:mega:_Règles de chaque jeu_
 - **Pendu :** le but est de trouver le mot, issu d'un thème choisi par vos propres soins proposé par le serveur. Vous saisissez une lettre dans la zone de saisie et vous validez avec la touche "Entrée" ou le bouton "valider". Vous pouvez jouer tant qu'il vous reste des vies et que le pendu n'est pas dessiné complètement.
 - **Allumettes :** dans ce jeu, vous jouez face à un ordinateur. Devant vous se trouve un tas d'allumettes. Tour à tour, vous devez retirer une ou deux allumettes parmi le tas. La partie s'arrête quand le tas d'allumettes est vide. Le gagnant de la partie est celui qui aura retiré un nombre impair d'allumettes.
 - **Tic tac toe :** il se joue à 2 joueurs. L'un après l'autre, vous allez devoir placer votre marque (O ou X) sur une case disponible. Le gagnant est celui qui aura réussi à aligner 3 marques horizontalement, verticallement ou en diagonale.
 

------------
<h3>:bulb:Lancer le programme</h3>
Pour lancer l'application, il vous faut lancer le `serveur.java` en premier.  

Ensuite, il vous faut lancer le `client.java` pour avoir l'interface d'accueil.
Celle-ci vous permettera de naviguer d'un jeu à l'autre et de se connecter au serveur. Pour retourner quitter un jeu et retourner à la page précédente, vous avez un bouton `Quitter` qui vous est accessible tout au long d'une partie.

<h3>:bell:Avant lancer le programme, si vous avez une erreur</h3>  
Ajouter Javafx dans Java Build Path    

Si vous avez une version différente de Java 8 il vous faut ajouter la ligne suivante dans : `Run configuration -> Argument` pour le fichier client.java 


`--module-path "\path to javafx\lib" --add-modules javafx.controls,javafx.fxml`  
il vous faut remplacer `"path to javafx\lib"` par votre chemin menant à votre JavaFx
 ![9f1a019cd4210f12cc89a162031d746c](https://user-images.githubusercontent.com/56112613/112481932-cfb06280-8d77-11eb-8c62-95a344b26b49.png)
 
<h3> Pour passer Eclispe en UTF-8 et avoir les accents : </h3>
Window > Preferences > General > Workspace, set Text file encoding to Other : UTF-8
