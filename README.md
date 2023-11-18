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

```bash
SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL EXO1")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession.read().option("header", true).csv("incidents.csv");
        dataset.groupBy(col("service")).count().show();

        

```

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/04ae28b4-276c-4be4-876b-17f1bfba90d2)

`2. Afficher les deux années où il a y avait plus d’incidents.`

```bash
SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL EXO1")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession.read().option("header", true).csv("incidents.csv");
        dataset.groupBy(col("date")).count().limit(2).select(col("date").as("JOUR"),col("count").as("NOMBRE DE INCIDENTS")).show();
```


![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/7ef02859-259d-4b64-8e85-26a2816fe0f2)

## Exercice 2

L’hôpital national souhaite traiter ces données au moyen d’une application Spark d’une
manière parallèle est distribuée. L’hôpital possède des données stockées dans une base de
données relationnel et des fichiers csv. L’objectif est de traiter ces données en utilisant Spark
SQL à travers les APIs DataFrame et Dataset pour extraire des informations utiles afin de
prendre des décisions.

Creation des trois tables dans la base de donnes relationnelles `MYSQL`


### Structure de `DB_HOSPITAL`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/b664efa6-980b-459c-91d5-b781e8c7babc)

#### `TABLE PATIENTS`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/dfb200c2-05e3-4a15-ae35-8f186fda1d6b)

#### `TABLE MEDECINS`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/342570fc-d458-4380-89cd-efacbda9f7f4)

#### `TABLE CONSULTATIONS`

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/fb6e13e5-417c-47c7-ac6d-1118326d3053)

#### Travail a faire
`1- Afficher le nombre de consultations par jour.`

```bash
SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL")
                .master("local[*]")
                .getOrCreate();

        Map<String, String> options = Map.of("driver", "com.mysql.jdbc.Driver",
                "url", "jdbc:mysql://localhost:3306/DB_HOSPITAL?createDatabaseIfNotExist=true",
                "user","root",
                "password","");
Dataset<Row> dataframe=sparkSession.read().format("jdbc")
                .options(options)
                .option("query","select * from consultations")
                .load();
dataframe.groupBy(col("date_consultation").as("JOUR")).count().show();

```

`Afficher le nombre de consultation par médecin.`
` NOM | PRENOM | NOMBRE DE CONSULTATION `
```bash
SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL")
                .master("local[*]")
                .getOrCreate();

        Map<String, String> options = Map.of("driver", "com.mysql.jdbc.Driver",
                "url", "jdbc:mysql://localhost:3306/DB_HOSPITAL?createDatabaseIfNotExist=true",
                "user","root",
                "password","");


        Dataset<Row> dfConsultations=sparkSession.read().format("jdbc")
                .options(options)
                .option("query","select * from consultations")
                .load();

        Dataset<Row> dfPatients=sparkSession.read().format("jdbc")
                .options(options)
                .option("query","select * from patients")
                .load();

        Dataset<Row> dfMedecins=sparkSession.read().format("jdbc")
                .options(options)
                .option("query","select * from medecins")
                .load();

        // This tables contient two Columns

        // ID_MEDECIN | COUNT

        Dataset<Row> table1 = dfConsultations.groupBy(col("id_medecin")).count();

        // ID | NOM | PRENOM
        Dataset<Row> table2 = dfMedecins.select("ID","NOM","PRENOM");

        Dataset<Row> joinTables = table2.join(table1, table1.col("id_medecin").equalTo(table2.col("id")), "inner");

         joinTables.select(col("NOM"),col("PRENOM"),col("count").as("NOMBRE DE CONSULTATION")).show();

```

![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/4c75d29e-ac63-4db4-9849-c1a3c7828c3f)


`Afficher pour chaque médecin, le nombre de patients qu’il a assisté.`
```bash
dataframe.groupBy(col("id_medecin").as("Num de MEDICINE")).count().show();
```
![image](https://github.com/MouhtaramSoufiane/SparkSQL/assets/104082651/72077946-4a19-4b3d-92af-44f658d1cb6a)













