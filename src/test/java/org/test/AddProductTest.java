package org.test;

import org.basetest.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.product.Product;
import org.utils.APIUtils;
import org.utils.DBUtils;

public class AddProductTest extends BaseTest {

    @DisplayName("Добавление товара через API")
    @Tag("Test_API")
    @ParameterizedTest
    @MethodSource("testDataOne")
    public void addProductTest(String name, String type, boolean exotic) {
        Product newProduct = new Product(name, type, exotic);

        //Метод проверят запрос GET
        APIUtils.getProductResponse();

        //Добавляем новый продукт через API
        APIUtils.postProduct(newProduct);

        //Проверяем, что продукт добавился через API
        Assertions.assertEquals(newProduct, APIUtils.getLastProduct(), "Новый продукт не добавился");

        //Проверяем, что продукт добавленный через API совпадает с продуктом в DB
        Assertions.assertEquals(newProduct, DBUtils.selectProduct(newProduct, jdbcTemplate),
                "Продукты не одинаковые");

        //Удаляем продукт через API
        APIUtils.resetProduct();

        //Проверяем, что продукт удалился из DB
        Assertions.assertNull(DBUtils.selectProduct(newProduct, jdbcTemplate), "Продукт не удалился");

        //Проверяем, что продукт удалился через API
        Assertions.assertNotEquals(newProduct, APIUtils.getLastProduct(), "Продукт не удалился");
    }

}
