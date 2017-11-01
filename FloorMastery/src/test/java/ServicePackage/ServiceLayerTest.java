/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicePackage;

import DAO.OrderRecords;
import DAO.ProductInventory;
import DAO.TaxDataDAO;
import Model.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class ServiceLayerTest {

    private ServiceLayer service;
    private TaxDataDAO taxDao;
    private ProductInventory productDao;
//    private OrderRecords orderDao;
//    private Order order;

    public ServiceLayerTest() {
//        orderDao = new OrderRecordsStub();
//        productDao = new ProductInventoryStub();
//        taxDao = new TaxDataStub();
//
//        ModeDAO modeDao = new ModeDAOStub();
//
//        service = new ServiceLayerImpl(orderDao, productDao, taxDao, modeDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("textApplicationContext.xml");
        service = ctx.getBean(" serviceLayer", ServiceLayer.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class ServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {

        Order newOrder = new Order("");
        String state = "";
        String product = "";

        newOrder.setArea(new BigDecimal("0"));
        try {
            service.addOrder(newOrder.getOrderId(), newOrder);

            fail("Expected InvalidOrderException was not thrown.");

        } catch (InvaildOrderException e) {

        }

    }

    /**
     * Test of getOneOrder method, of class ServiceLayer.
     */
    @Test
    public void testGetOneOrder() throws Exception {
        Order fromService = service.getOneOrder(1, LocalDate.now());
        Assert.assertNotNull(fromService);
    }

    /**
     * Test of displayOrders method, of class ServiceLayer.
     */
    @Test
    public void testDisplayOrders() throws Exception {
        Assert.assertEquals(1, service.displayOrders().size());
    }

    /**
     * Test of removeOrder method, of class ServiceLayer.
     */
    @Test
    public void testRemoveOrder() {
        Order order = new Order();
        try {
            order = service.getOneOrder(1, LocalDate.now());
        } catch (FMNoOrdersOnDateException e) {
            Assert.fail("Should return a order");
        }
        try {
            Order removeOrder = service.removeOrder(order.getOrderId(), order.getOrderDate());
        } catch (InvaildOrderException e) {
            Assert.fail("Should remove a Order");
        }

    }

    /**
     * Test of displayAllOrdersByDate method, of class ServiceLayer.
     */
    @Test
    public void testDisplayAllOrdersByDate() throws Exception {
        Assert.assertNull("1", service.displayAllOrdersByDate(LocalDate.now()).size());
    }

//    /**
//     * Test of getAllTax method, of class ServiceLayer.
//     */
    @Test
    public void testGetAllTax() throws Exception {
        Assert.assertEquals(1, taxDao.displayTax().size());

    }

    /**
     * Test of getAllProducts method, of class ServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        Assert.assertEquals(1, productDao.displayProducts().size());
    }

}
