# Projet Vlille

## Auteurs
Signouret Nathan
Potel Nicolas

## Introduction
Ce répertoire constitue notre rendu pour le projet d'implémentation d'une station de véhicules. Pour la suite, toutes les commandes citées seront à effectuer dans le répertoire courant du projet, sauf indication contraire.
Les tests ont été réalisés à l'aide de Junit 5.

## Présentation
Durant ce projet, beaucoup de choix de conception ont dû être effectués, certains seront détaillés dans cette partie.
Notamment, il a été décidé que les stations de véhicules peuvent accueillir tout type de véhicule, et ce faisant, que chacune disposait d'un équipement adapté, pour mettre en charge des vélos électriques, par exemple.

Ensuite, il a été précisé dans le sujet que le centre de contrôle est unique et dirige l'ensemble des professionnels et stations, ce choix a été respecté.

Nous avons mis en place au cours du développement un système utilisant le domaine de la réflexivité en java, domaine que nous n'avons pas encore abordé en cours. Des problèmes ont été rencontrés liés à l'utilisation de cette notion, sans solution concrète y remédier. Ce faisant, nous avons dû retirer la majorité du code qui utilisait cette notion du fait du manque de temps pour se documenter correctement sur les différentes façons de régler le problème.

Ensuite, nous avons décidé d'ajouter les vélos électriques et peintres dans notre code, les vélos électriques sont gérés, déchargés et rechargés selon leur utilisation même si cela n'est pas affiché.
Pour les peintres, ils prennent bien en charge des véhicules mais ne changent nullement leur couleur, si besoin en est, la possibilité pourra être ajoutée du fait de la mise en place permettant ce type d'extension.

De plus, nous avons choisi de partir du principe qu'un professionnel était aussi une personne, partant de ce constat, chaque professionnel (ici les mécaniciens et les peintres) pourra lui aussi réserver un véhicule pour son utilisation personnelle indépendamment du véhicule sur lequel il peut être en train de travailler.

Concernant les stations de véhicules, il est conseillé de générer au moins autant de véhicules que de stations, afin que chaque station ne soit pas toujours vide, sinon des redistributions de véhicules se feront en boucle. Il faut également savoir que les redistributions n'affectent que les véhicules qui ne sont pas utilisés (ni volés, ni utilisés ou pris en charge par un professionnel).

Actuellement, les véhicules et personnes choisis à chaque tour pour effectuer une action ne sont pas choisis aéatoirement, mais au premier venu suivant leur disponibilité, la possiblité a été laissée de pouvoir modifier cela facilement via la modification de la méthode "getAvailablePerson" ou "getAvailableVehicle" dans la classe ControlCenter.

## UML
https://lucid.app/lucidchart/3468b143-b805-4440-9049-433330d73780/edit?view_items=bNaxWuv_d8-_&invitationId=inv_f088cb92-61b0-4470-84d2-1ca0cd066ebc

## Compilation
Pour compiler la totalité du code, effectuer la commande ```make compile```.
Pour compiler la totalité des tests, effectuer la commande ```make compileTest```.
Si vous souhaitez compiler un package en particulier, effectuer la commande ```make <package>``` et remplacer ```<package>``` par le nom du package à compiler.
Le fichier jar permettant la compilation et l'exécution des tests vous est fourni dans le dossier jars.

## Documentation
Pour générer la documentation du code, effectuer la commande ```make doc```.
Vous pourrez ensuite consulter cette même documentation dans le dossier ```docs``` qui se trouve dans le répertoire courant, en ouvrant un des fichiers html présents dans les différents packages.

## Archive
Pour générer une archive exécutable .jar du projet, effectuer la commande ```make jar```.

## Exécution
Pour exécuter le main du projet, effectuer la commande ```make``` qui compilera l'entièreté du code, générera la documentation et lancera l'exécution du main.
Pour exécuter les tests du projet, effectuer la commande ```make test``` qui compilera ces mêmes tests avant de les exécuter.
