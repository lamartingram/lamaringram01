/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Order;
import ServicePackage.FMMemoryPersistenceException;
import ServicePackage.FMNoOrdersOnDateException;
import ServicePackage.InvaildOrderException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderRecords {

    Order getOrder(int orderId, LocalDate date);

    List<Order> displayOrdersByDate(LocalDate date);

    List<Order> displayOrders();

    Order addOrder(int orderId, Order order) throws InvaildOrderException;

    Order removeOrder(int orderId, LocalDate date) throws InvaildOrderException ;

    int generateOrderNumber();

    public int loadOrders() throws FMMemoryPersistenceException;

    void writeOrders() throws FMMemoryPersistenceException ;

}
