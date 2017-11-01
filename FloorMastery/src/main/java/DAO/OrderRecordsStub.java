/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Order;
import ServicePackage.FMMemoryPersistenceException;
import ServicePackage.InvaildOrderException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrderRecordsStub implements OrderRecords {

    private OrderRecords orderRecords;
    private ArrayList<Order> orders = new ArrayList<>();
    private Order onlyOrder;

    public OrderRecordsStub() {
        onlyOrder = new Order();
        onlyOrder.setOrderId(1);
        onlyOrder.setCustomerName("Lamar");
        onlyOrder.setOrderDate(LocalDate.now());
        onlyOrder.setProductType("Carpet");
        onlyOrder.setArea(new BigDecimal("100"));    
        onlyOrder.setState("KY");
        onlyOrder.setTaxRate(new BigDecimal("6"));

        orders.add(onlyOrder);// dummy data
    }

    @Override
    public Order getOrder(int orderId, LocalDate date) {
        if (orderId == onlyOrder.getOrderId() && date.toString().equals(onlyOrder.getOrderDate().toString())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List<Order> displayOrders() {
        return orders;
    }

    @Override
    public Order addOrder(int orderId, Order order) throws InvaildOrderException {
        if (orderId == onlyOrder.getOrderId()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order removeOrder(int orderId, LocalDate date) {
        if (orderId == onlyOrder.getOrderId() && date.toString().equals(onlyOrder.getOrderDate().toString())) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public int generateOrderNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int loadOrders() throws FMMemoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeOrders() throws FMMemoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> displayOrdersByDate(LocalDate date) {
       if(date == onlyOrder.getOrderDate()){
           return orders;
       }else{
           return null;
       }
    }

}
