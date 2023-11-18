package org.DataFrameFromDataBase;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class App {
    public static void main(String[] args) {
        SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> dataframe=sparkSession.read().format("jdbc")
                .option("driver","com.mysql.jdbc.Driver")
                .option("url","jdbc:mysql://localhost:3306/DB_ECOMMERCE?createDatabaseIfNotExist=true")
//                .option("dbtable","Products")
                .option("query","select * from Products where price > 12000")
                .option("user","root")
                .option("password","")
                .load();
        dataframe.show();
    }
}
