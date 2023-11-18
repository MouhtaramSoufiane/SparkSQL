package org.DataFrameFromCSVandJSON;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;

public class AppDFJson {
    public static void main(String[] args) throws AnalysisException {
        SparkSession  ss= SparkSession.builder()
                .appName("Spark SQL")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataFrame=ss.read()
                .option("multiline",true)
                .json("products.json");
//        dataFrame.show();
        // DtaFrame est un Resilient Distributed DataSet (RDD) + Schema
//        dataFrame.printSchema();
        // Operations
//        dataFrame.select(col("name").as("Name of product")).show();
//        dataFrame.orderBy(col("name").asc()).show();
//        dataFrame.groupBy(col("name")).count().show();
//          dataFrame.limit(3).show();
//        dataFrame.filter(col("price").gt(17000)).show();
//        dataFrame.filter(col("name").equalTo("Lenovo").and(col("price").gt(17000))).show();
//        dataFrame.filter("name like 'HP' and price > 17000").show();
        // Create a View, and then you can start to use SQL
//        dataFrame.createOrReplaceTempView("products");
//
//        ss.sql("select * from products where name like 'HP'").show();
        // CSV File
        Dataset<Row> dataFrame1=ss.read().option("header",true).option("inferSchema",true).csv("products.csv");
        dataFrame1.show();
        dataFrame1.printSchema();



    }
}
