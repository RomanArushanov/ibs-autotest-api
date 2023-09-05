package org.basetest;

import org.junit.jupiter.api.BeforeAll;
import org.specification.Specifications;
import org.springframework.jdbc.core.JdbcTemplate;
import org.utils.DBUtils;

public class BaseTest {
    protected static JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void init() {
        Specifications.installSpecification(Specifications.requestSpecification(Constant.URL));

        jdbcTemplate = new JdbcTemplate(DBUtils.createDataSource());
    }

    public static Object[][] testDataOne() {
        return new Object[][]{
                {"Apple", "FRUIT", true},
                {"Gurke", "VEGETABLE", true},
                {"胡蘿蔔", "VEGETABLE", true},
                {"Абрикос", "FRUIT", true},
                {"موز", "FRUIT", true}
        };
    }
}
