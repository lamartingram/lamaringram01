/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ProductInventoryImplTest {

    Product product;
    ProductInventoryImpl productDao;

    public ProductInventoryImplTest() {
        productDao = new ProductInventoryImpl();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Product> products = productDao.displayProducts();
        for (Product product : products) {
            productDao.removeProduct(product.getProductType());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getProduct method, of class ProductInventoryImpl.
     */
    @Test
    public void testGetProduct() {
        Product product = new Product("Tile");
        product.setCostPerSqFoot(new BigDecimal(1.0));
        product.setLaborCostPerSqFoot(new BigDecimal(2.0));

        productDao.addProduct(product.getProductType(), product);

        Product productFromDao = productDao.getProduct(product.getProductType());

        Assert.assertEquals(productFromDao, product);
    }

    /**
     * Test of displayProducts method, of class ProductInventoryImpl.
     */
    @Test
    public void testDisplayProducts() {
        Product product = new Product("Tile");
        product.setCostPerSqFoot(new BigDecimal(1.0));
        product.setLaborCostPerSqFoot(new BigDecimal(2.0));

        productDao.addProduct(product.getProductType(), product);

        Assert.assertEquals(1, productDao.displayProducts().size());
    }

    /**
     * Test of removeProduct method, of class ProductInventoryImpl.
     */
    @Test
    public void testRemoveProduct() {
        Product product = new Product("Tile");
        product.setCostPerSqFoot(new BigDecimal(1.0));
        product.setLaborCostPerSqFoot(new BigDecimal(2.0));

        productDao.addProduct(product.getProductType(), product);

        Assert.assertEquals(1, productDao.displayProducts().size());

        productDao.removeProduct(product.getProductType());
        Assert.assertEquals(0, productDao.displayProducts().size());
    }

    /**
     * Test of addProduct method, of class ProductInventoryImpl.
     */
    @Test
    public void testAddProduct() {
        Product product = new Product("Tile");
        product.setCostPerSqFoot(new BigDecimal(1.0));
        product.setLaborCostPerSqFoot(new BigDecimal(2.0));

        productDao.addProduct(product.getProductType(), product);

        Assert.assertEquals(1, productDao.displayProducts().size());

    }

    /**
     * Test of loadProducts method, of class ProductInventoryImpl.
     */
    @Test
    public void testLoadProducts() throws Exception {
        
    }

}
