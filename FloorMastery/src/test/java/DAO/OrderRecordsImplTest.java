/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Order;
import ServicePackage.InvaildOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class OrderRecordsImplTest {

    OrderRecordsImpl orderDao = new OrderRecordsImpl();

    public OrderRecordsImplTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        List<Order> orders = orderDao.displayOrders();
        for (Order order : orders) {
            orderDao.removeOrder(order.getOrderId(), order.getOrderDate());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOrder method, of class OrderRecordsImpl.
     */
    @Test
    public void testGetOrder() {
        Order order = new Order(1);
        order.setCustomerName("Lamar");
        order.setOrderDate(LocalDate.now());
        order.setProductType("Carpet");
        order.setMaterialCost(new BigDecimal(10));
        order.setLaborCostPersqFt(new BigDecimal(15.5));
        order.setState("KY");
        order.setTaxRate(new BigDecimal(6));
        try {

            orderDao.addOrder(order.getOrderId(), order);
            Order fromOrderDao = orderDao.getOrder(order.getOrderId(), order.getOrderDate());
            
            Assert.assertEquals(fromOrderDao, order);
        } catch (InvaildOrderException e) {

        }
    }

    /**
     * Test of displayOrders method, of class OrderRecordsImpl.
     */
    @Test
    public void testDisplayOrders() throws Exception {
        Order order = new Order(1);
        order.setCustomerName("Lamar");
        order.setOrderDate(LocalDate.now());
        order.setProductType("Carpet");
        order.setMaterialCost(new BigDecimal(10));
        order.setLaborCostPersqFt(new BigDecimal(15.5));
        order.setState("KY");
        order.setTaxRate(new BigDecimal(6));

        orderDao.addOrder(order.getOrderId(), order);

        Assert.assertEquals(1, orderDao.displayOrders().size());
    }

    /**
     * Test of addOrder method, of class OrderRecordsImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order(1);
        order.setCustomerName("Lamar");
        order.setOrderDate(LocalDate.now());
        order.setProductType("Carpet");
        order.setMaterialCost(new BigDecimal(10));
        order.setLaborCostPersqFt(new BigDecimal(15.5));
        order.setState("KY");
        order.setTaxRate(new BigDecimal(6));

        orderDao.addOrder(order.getOrderId(), order);

        Order fromDao = orderDao.getOrder(1, order.getOrderDate());

        Assert.assertEquals(order, fromDao);

    }

    /**
     * Test of removeOrder method, of class OrderRecordsImpl.
     */
    @Test

    public void testRemoveOrder() {
        Order order = new Order(1);
        order.setCustomerName("Lamar");
        order.setOrderDate(LocalDate.now());
        order.setProductType("Carpet");
        order.setMaterialCost(new BigDecimal(10));
        order.setLaborCostPersqFt(new BigDecimal(15.5));
        order.setState("KY");
        order.setTaxRate(new BigDecimal(6));
        try {
            orderDao.addOrder(order.getOrderId(), order);
            Order fromDao = orderDao.getOrder(order.getOrderId(), order.getOrderDate());
            Assert.assertEquals(order, fromDao);

            orderDao.removeOrder(order.getOrderId(), order.getOrderDate());
            Assert.assertEquals(0, orderDao.displayOrders().size());
        } catch (InvaildOrderException e) {

        }

    }

    /**
     * Test of generateOrderNumber method, of class OrderRecordsImpl.
     */
    @Test
    public void testGenerateOrderNumber() {
    }

    /**
     * Test of loadOrders method, of class OrderRecordsImpl.
     */
    @Test
    public void testLoadOrders() throws Exception {
        orderDao.loadOrders();
        List<Order> orderList = orderDao.displayOrders();

    }

    /**
     * Test of writeOrders method, of class OrderRecordsImpl.
     */
    @Test
    public void testWriteOrders() throws Exception {
    }

}
