/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicePackage;

;
import DAO.ModeDAO;
import DAO.OrderRecords;
import DAO.ProductInventory;
import DAO.TaxDataDAO;
import Model.Order;
import Model.Product;
import Model.TaxData;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */


public class ServiceLayerImpl implements ServiceLayer {

    private OrderRecords orderDao;
    private ProductInventory productDao;
    private TaxDataDAO taxDao;
    private ModeDAO modeDao;
    private int orderNumTracker;

    public ServiceLayerImpl(OrderRecords orderDao, ProductInventory productDao, TaxDataDAO taxDao, ModeDAO modeDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
        this.modeDao = modeDao;

    }

    @Override
    public Order addOrder(int orderId, Order order) throws InvaildOrderException {

        Order addOrder = orderDao.addOrder(orderId, order);
        if (orderId <= 0) {
            throw new InvaildOrderException("Invalid ID");
        }
        return addOrder;
    }

    @Override
    public Order getOneOrder(int orderId, LocalDate date) throws FMNoOrdersOnDateException {

        Order getOrder = orderDao.getOrder(orderId, date);
        if (getOrder == null) {

            throw new FMNoOrdersOnDateException("This order does not exist");
        }
        return getOrder;

    }

    @Override
    public List<Order> displayOrders() {
        return orderDao.displayOrders();
    }

    @Override
    public Order removeOrder(int orderId, LocalDate date) throws InvaildOrderException {

        return orderDao.removeOrder(orderId, date);
    }

    @Override
    public boolean confirmRemoveOrder(String confirm) {
        return confirm.equalsIgnoreCase("y");
    }

    @Override
    public int generateOrderId() {
        int newId = this.orderNumTracker + 1;
        this.orderNumTracker = newId;
        return newId;
    }

    @Override
    public BigDecimal calcTaxFinal(Order order) {
        BigDecimal taxRate = order.getTaxRate();
        BigDecimal beforeTaxTotal = order.getMaterialCost().add(order.getLaborCost());
        BigDecimal taxesFinal = taxRate.multiply(beforeTaxTotal);
        order.setTax(taxesFinal);
        return taxesFinal;
    }

    @Override
    public Order calculateProductsOrder(Order order) {
//CALCULATE TAX RATE
        BigDecimal taxRate = new BigDecimal("0");
        taxRate = taxRate.add(getTaxRate(order.getState()));
        BigDecimal newTaxRate = taxRate.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setTaxRate(newTaxRate);

        //Calculate CPSF
        BigDecimal costPerSqFoot = new BigDecimal("0");
        costPerSqFoot = getCostPerSqFoot(order.getProductType());
        BigDecimal newCostPerSqFoot = costPerSqFoot.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setCostPerSqFoot(newCostPerSqFoot);

        //Calcilate LPSF
        BigDecimal laborPerSqFoot = new BigDecimal("0");
        laborPerSqFoot = getLaborPerSqFoot(order.getProductType());
        BigDecimal newLaborPerSqFoot = laborPerSqFoot.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setLaborCostPersqFt(newLaborPerSqFoot);

        BigDecimal materialsCost = new BigDecimal("0");
        materialsCost = order.getCostPerSqFoot().multiply(order.getArea());
        BigDecimal newMaterialCost = materialsCost.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setMaterialCost(newMaterialCost);

        //calculate laborTotal
        BigDecimal laborTotal = new BigDecimal("0");
        laborTotal = order.getLaborCostPersqFt().multiply(order.getArea());
        BigDecimal newLaborTotal = laborTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setLaborCost(newLaborTotal);

        //calculate total before tax
        BigDecimal materials = new BigDecimal("0");
        materials = order.getMaterialCost();
        BigDecimal labor = new BigDecimal("0");
        labor = order.getLaborCost();
        BigDecimal totalBeforeTax = new BigDecimal("0");
        totalBeforeTax = materials.add(labor);

        //calculate final amount taxed
        BigDecimal taxFinal = new BigDecimal("0");
        taxRate = order.getTaxRate();
        BigDecimal newFinalTaxRate = taxRate.divide(new BigDecimal("100"));
        taxFinal = totalBeforeTax.multiply(newFinalTaxRate);
        BigDecimal newTaxFinal = taxFinal.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setTax(newTaxFinal);

        //calculate total price;
        BigDecimal finalPrice = new BigDecimal("0");
        finalPrice = totalBeforeTax.add(taxFinal);
        BigDecimal newFinalPrice = finalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        order.setOrderTotal(newFinalPrice);

        return order;

    }

    @Override
    public BigDecimal getTaxRate(String state) {
        BigDecimal taxRate = new BigDecimal("0");
        List<TaxData> taxes = taxDao.displayTax();
        for (TaxData tax : taxes) {
            if (tax.getState().equals(state)) {
                taxRate = taxRate.add(tax.getTaxRate());
            }
        }
        return taxRate;

    }

    @Override
    public BigDecimal getMaterialCost(String productType) {
        BigDecimal materialCost = new BigDecimal("0");
        List<Product> products = productDao.displayProducts();
        for (Product product : products) {
            if (product.getProductType().equals(productType)) {
                materialCost = materialCost.add(product.getCostPerSqFoot());
            }
        }
        return materialCost;
    }

    @Override
    public BigDecimal getCostPerSqFoot(String productType) {
        BigDecimal costPerSqFt = new BigDecimal("0");
        List<Product> products = productDao.displayProducts();
        for (Product product : products) {
            if (product.getProductType().equals(productType)) {
                costPerSqFt = costPerSqFt.add(product.getCostPerSqFoot());
            }
        }
        return costPerSqFt;

    }

    @Override
    public BigDecimal getLaborPerSqFoot(String productType) {
        BigDecimal laborPerSqFt = new BigDecimal("0");
        List<Product> products = productDao.displayProducts();
        for (Product product : products) {
            if (product.getProductType().equals(productType)) {
                laborPerSqFt = laborPerSqFt.add(product.getLaborCostPerSqFoot());
            }
        }
        return laborPerSqFt;
    }

    @Override
    public Boolean readMode() throws FMMemoryPersistenceException {
        return modeDao.checkMode();
    }

    @Override
    public List<Order> displayAllOrdersByDate(LocalDate date) {

        List<Order> orderList = orderDao.displayOrdersByDate(date);

        return orderList;
    }

    @Override
    public void loadOrders() throws FMMemoryPersistenceException {
        orderNumTracker = orderDao.loadOrders();
    }

    @Override
    public void loadTax() throws FMMemoryPersistenceException {
        taxDao.loadTax();

    }

    @Override
    public void loadProducts() throws FMMemoryPersistenceException {
        productDao.loadProducts();

    }

    @Override
    public void writeOrders() throws FMMemoryPersistenceException {
        orderDao.writeOrders();

    }

    @Override
    public List<TaxData> getAllTax() {
        return taxDao.displayTax();
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.displayProducts();
    }

}
