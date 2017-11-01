/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicePackage;

import Model.Order;
import Model.Product;
import Model.TaxData;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface ServiceLayer {

    public Order addOrder(int orderId, Order order) throws InvaildOrderException;

    public Order getOneOrder(int orderId, LocalDate date) throws FMNoOrdersOnDateException;

    public List<Order> displayOrders();

    public Order removeOrder(int orderId, LocalDate date) throws InvaildOrderException;

    public boolean confirmRemoveOrder(String confirm) throws InvaildOrderException, FMNoOrdersOnDateException;

    public int generateOrderId();

    public BigDecimal calcTaxFinal(Order order);

    public Order calculateProductsOrder(Order order);

    public BigDecimal getTaxRate(String state);

    public BigDecimal getMaterialCost(String productType);

    public BigDecimal getCostPerSqFoot(String productType);

    public BigDecimal getLaborPerSqFoot(String productType);

    public Boolean readMode() throws FMMemoryPersistenceException;

    public List<Order> displayAllOrdersByDate(LocalDate date);

    public void loadOrders()
            throws FMMemoryPersistenceException;

    public void loadTax()
            throws FMMemoryPersistenceException;

    public void loadProducts()
            throws FMMemoryPersistenceException;

    public void writeOrders()
            throws FMMemoryPersistenceException;

    //public LocalDate convertDate(LocalDate date);
    public List<TaxData> getAllTax();

    public List<Product> getAllProducts();

}
