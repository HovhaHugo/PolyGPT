# Decrypt'Excel

## Contexte
Le projet a pour but de traduire un fichier excel contenant
la maquette des années scolaires de Polytech Tours pour le stocker
dans un fichier texte. Une fois ceci fait, le fichier texte servira de
base pour faire apprendre la maquette à une IA de style ChatGPT.

## Comment l'utiliser :
Il est possible d'utiliser l'application dans l'immédiat. \
Pour cela :
- télécharger le .jar nommé PolyGPT.jar
- lancer une interface de commande
- écrire la commande si dessous pour lancer le programme
>  java -jar DecryptExcel.jar
- vous êtes arrivé maintenant dans le programme

Une fois dans le programme, l'utilisateur est guidé par les saisies, mais
voici le déroulement de l'application :
- Commencer par renseigner le chemin ainsi que le nom du fichier
    - exemple : */user/downloads/maquette_2023*
    - **Attention** : ne saisissez pas l'extension du fichier.
- Saisissez le nombre d'années renseigné dans le fichier (1, 2 ou 3 années)
    - si vous ne saisissez pas 3, le programme vous demandera les noms de l'année que vous voulez traduire (3A, 4A ou 5A)
- Saisissez le niveau de TOEIC attendu à la fin de la/des années
- Le fichier.txt devrait être créé et se trouver dans le même dossier que le programme
    - exemple : Si vous lancez le programme dans le dossier */user/downloads* le fichier .txt sera dans le dossier /user/downloads

## Année de creation, version, équipe et encadrant
### Année de création
2023/2024 par des étudiants en 4ᵉ année à Polytech Tours

### Version
1.9

### Equipe et encadrant
Equipe : Caroline Petit et Hugo Hovhannessian \
Encadrant : Mr Nicolas Ragot
