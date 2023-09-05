package org.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.basetest.BaseTest;
import org.product.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtils {
    public static DataSource createDataSource() {
        Properties dataBaseProperties = new Properties();

        try {
            InputStream resourceAsStream = BaseTest.class.getClassLoader()
                    .getResourceAsStream("database.properties");
            if (resourceAsStream != null) {
                dataBaseProperties.load(resourceAsStream);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Не удалось загрузить файл database.properties", e);
        }

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(dataBaseProperties.getProperty("db.url"));
        dataSource.setUsername(dataBaseProperties.getProperty("db.user"));
        dataSource.setPassword(dataBaseProperties.getProperty("db.password"));

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);
        return dataSource;
    }

    public static Product selectProduct(Product product, JdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM FOOD WHERE  FOOD_NAME = ? AND FOOD_TYPE = ? AND FOOD_EXOTIC = ?";
        RowMapper<Product> rowMapper = (rs, rowNum) -> {
            Product resultProduct = new Product();
            resultProduct.setName(rs.getString("FOOD_NAME"));
            resultProduct.setType(rs.getString("FOOD_TYPE"));
            resultProduct.setExotic(rs.getBoolean("FOOD_EXOTIC"));
            return resultProduct;
        };
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, product.getName(), product.getType(), product.getExotic());
        } catch (Exception ex) {
            return null;
        }
    }
}
