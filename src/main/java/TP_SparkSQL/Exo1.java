package TP_SparkSQL;

import TP_SparkSQL.Entities.Incident;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.col;

public class Exo1 {
    public static void main(String[] args) {
        SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL EXO1")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataset = sparkSession.read().option("header", true).csv("incidents.csv");
//        dataset.groupBy(col("service")).count().show();

        dataset.groupBy(col("date")).count().limit(2).select(col("date").as("JOUR"),col("count").as("NOMBRE DE INCIDENTS")).show();
    }
}
