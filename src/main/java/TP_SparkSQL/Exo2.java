package TP_SparkSQL;

import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;

import static org.apache.spark.sql.functions.col;
public class Exo2 {
    public static void main(String[] args) {
        SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL")
                .master("local[*]")
                .getOrCreate();

        Map<String, String> options = Map.of("driver", "com.mysql.jdbc.Driver", "url", "jdbc:mysql://localhost:3306/DB_HOSPITAL?createDatabaseIfNotExist=true","user","root","password","");

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

//        dfPatients.show();

        // NOM | PRENOM | NOMBRE DE CONSULTATION

        // This tables contient two Columns

        // ID_MEDECIN | COUNT

        Dataset<Row> table1 = dfConsultations.groupBy(col("id_medecin")).count();

        // ID | NOM | PRENOM
        Dataset<Row> table2 = dfMedecins.select("ID","NOM","PRENOM");

//        table2.show();
        Dataset<Row> joinTables = table2.join(table1, table1.col("id_medecin").equalTo(table2.col("id")), "inner");
//         joinTables.show();
         joinTables.select(col("NOM"),col("PRENOM"),col("count").as("NOMBRE DE CONSULTATION")).show();

//        dataframe.groupBy(col("date_consultation").as("JOUR")).count().show();
//        dataframe.groupBy(col("id_medecin").as("Num de MEDICINE")).count().show();

//        dataframe.printSchema();
    }
}
