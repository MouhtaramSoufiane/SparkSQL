# Spark SQL

Spark SQL est un module Apache Spark qui offre une interface de programmation pour le traitement de données structurées à l'aide du langage SQL (Structured Query Language). Il permet aux utilisateurs d'exécuter des requêtes SQL sur des données stockées dans des formats variés tels que JSON, CSV et d'autres, ainsi que dans des sources de données externes telles que des bases de données relationnelles.

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/5157d255-c327-490f-8ec7-b43620ccfa09)

## Structure de project
La structure de projet :

```bash
  C:.                              
├───.idea                        
├───spark-warehouse              
├───src
│   ├───main
    ├───classes
    │   ├───org
    │   │   ├───DataFrameFromCSVandJSON
    │   │   ├───DataFrameFromDataBase
    │   │   └───entities
    │   └───TP_SparkSQL
    │       └───Entities
    └───generated-sources
        └───annotations

```

## Exercice 1

On souhaite développer pour une entreprise industrielle une application Spark qui traite les
incidents de chaque service. Les incidents sont stockés dans un fichier csv.
Voir le fichier en format CSV  `incidents.csv` dans le project.

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/08209eb2-2e66-4491-8546-445e73bfdb30)


`1. Afficher le nombre d’incidents par service.`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/04ae28b4-276c-4be4-876b-17f1bfba90d2)

`2. Afficher les deux années où il a y avait plus d’incidents.`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/7ef02859-259d-4b64-8e85-26a2816fe0f2)




