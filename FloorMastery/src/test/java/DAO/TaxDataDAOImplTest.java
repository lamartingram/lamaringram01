/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.TaxData;
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
public class TaxDataDAOImplTest {

    TaxDataDAO taxDao;
    TaxData tax;

    public TaxDataDAOImplTest() {
        taxDao = new TaxDataDAOImpl();

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<TaxData> taxes = taxDao.displayTax();
        for (TaxData tax : taxes) {
            taxDao.removeTax(tax.getState());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTax method, of class TaxDataDAOImpl.
     */
    @Test
    public void testGetTax() {
        List<TaxData> taxes = taxDao.displayTax();
        for (TaxData tax : taxes) {
            taxDao.removeTax(tax.getState());
        }
    }

    /**
     * Test of displayTax method, of class TaxDataDAOImpl.
     */
    @Test
    public void testDisplayTax() {
        TaxData tax1 = new TaxData("PA");
        tax1.setTaxRate(new BigDecimal(1.5));
        taxDao.addTax(tax1.getState(), tax);

        TaxData tax2 = new TaxData("OH");
        tax2.setTaxRate(new BigDecimal(2.5));
        taxDao.addTax(tax2.getState(), tax);

        Assert.assertEquals(2, taxDao.displayTax().size());

    }

    /**
     * Test of addTax method, of class TaxDataDAOImpl.
     */
    @Test
    public void testAddTax() {
        TaxData taxes = new TaxData("OH");
        taxes.setTaxRate(new BigDecimal(1.5));

        Assert.assertEquals(0, taxDao.displayTax().size());

        taxDao.addTax(taxes.getState(), tax);

        Assert.assertEquals(1, taxDao.displayTax().size());
    }

    /**
     * Test of removeTax method, of class TaxDataDAOImpl.
     */
    @Test
    public void testRemoveTax() {
        TaxData taxes = new TaxData("MA");
        taxes.setTaxRate(new BigDecimal(1.5));
        taxDao.addTax(taxes.getState(), tax);

        TaxData tax2 = new TaxData("DE");
        tax2.setTaxRate(new BigDecimal(2.5));
        taxDao.addTax(tax2.getState(), tax);

        Assert.assertEquals(2, taxDao.displayTax().size());

        taxDao.removeTax(tax2.getState());

        Assert.assertEquals(1, taxDao.displayTax().size());
        taxDao.removeTax(taxes.getState());

        Assert.assertEquals(0, taxDao.displayTax().size());
    }

    /**
     * Test of loadTax method, of class TaxDataDAOImpl.
     */
    @Test
    public void testLoadTax() throws Exception {

    }

}
