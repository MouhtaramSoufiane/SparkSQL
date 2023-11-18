package org.DataFrameFromDataBase;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.entities.Product;

import java.util.List;

public class App2 {
    public static void main(String[] args) {
        SparkSession sparkSession= SparkSession.builder()
                .appName("SPARK SQL")
                .master("local[*]")
                .getOrCreate();
        List<Product> products=List.of(new Product("Dell",12000,4),
                new Product("HP",20000,2),
                new Product("Lenovo",20000,2));
        Encoder<Product> productEncoder= Encoders.bean(Product.class);
        Dataset<Product> dataset=sparkSession.createDataset(products,productEncoder);
//        dataset.filter(new FilterFunction<Product>() {
//            @Override
//            public boolean call(Product product) throws Exception {
//                return product.getPrice()>17000;
//            }
//        }).show();
        dataset.filter((FilterFunction<Product>)  product ->
            product.getPrice()>17000
        ).show();

//        dataset.show();

    }

}
