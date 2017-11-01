/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Order;
import Model.TaxData;
import ServicePackage.FMMemoryPersistenceException;
import ServicePackage.FMNoOrdersOnDateException;
import ServicePackage.FMNoSuchProductException;
import ServicePackage.InvaildOrderException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class OrderRecordsImpl implements OrderRecords {

    private Set<LocalDate> dates = new HashSet<>();
    //the integer is for the Order ID
    private Map<Integer, Order> orders = new HashMap();

    public static final String DELIMITER = ",";
    public static final String ORDER_FOLDER = ".//floororders/";
    int orderId = 0;

    @Override
    public Order getOrder(int orderId, LocalDate date) {
        return orders.get(orderId);
    }

    @Override
    public List<Order> displayOrders() {
        return new ArrayList<Order>(orders.values());

    }

    @Override
    public List<Order> displayOrdersByDate(LocalDate date) {
        String dateString = date.toString();
        return orders.values()
                .stream()
                .filter(order -> order.getOrderDate().toString().equals(dateString))
                .collect(Collectors.toList());
    }

    @Override
    public Order addOrder(int orderId, Order order) throws InvaildOrderException {
        dates.add(order.getOrderDate());
        Order newOrder = orders.put(orderId, order);
        return newOrder;
    }

    @Override
    public Order removeOrder(int orderId, LocalDate date) {
        Order removedOrder = orders.remove(orderId);
        return removedOrder;
    }

    @Override
    public int generateOrderNumber() {
        int giveId = 0;
        boolean isUnique = true;
        while (isUnique) {
            for (int currentId : orders.keySet()) {
                if (giveId > currentId) {
                    currentId = giveId;
                    isUnique = false;
                }
            }
        }
        return giveId;
    }

    @Override
    public int loadOrders() throws FMMemoryPersistenceException {
        Scanner scanner;
        int orderIdTracker = 0;
        File dir = new File(ORDER_FOLDER);
        File[] files = dir.listFiles();

        for (File file : files) {

            try {
                // Create Scanner for reading the file
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FMMemoryPersistenceException(
                        "-_- Could not load inventory data into memory.", e);
            }

            String currentLine;

            String[] currentTokens;

            String fName = file.getName();
            String[] dateSplit1 = fName.split("_");
            String[] dateSplit2 = dateSplit1[1].split("\\.");
            LocalDate orderDate = LocalDate.parse(dateSplit2[0], DateTimeFormatter.ofPattern("MMddyyyy"));

            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();

                currentTokens = currentLine.split(DELIMITER);

                Order currentOrder = new Order();

                currentOrder.setOrderId(Integer.parseInt(currentTokens[0]));
                currentOrder.setCustomerName(currentTokens[1]);
                currentOrder.setState((currentTokens[2]));
                currentOrder.setTaxRate(new BigDecimal((currentTokens[3])));
                currentOrder.setProductType((currentTokens[4]));
                currentOrder.setArea(new BigDecimal((currentTokens[5])));
                currentOrder.setCostPerSqFoot(new BigDecimal((currentTokens[6])));
                currentOrder.setLaborCostPersqFt(new BigDecimal((currentTokens[7])));
                currentOrder.setMaterialCost(new BigDecimal((currentTokens[8])));
                currentOrder.setLaborCost(new BigDecimal((currentTokens[9])));
                currentOrder.setTax(new BigDecimal((currentTokens[10])));
                currentOrder.setOrderTotal(new BigDecimal((currentTokens[11])));
                currentOrder.setOrderDate(orderDate);

                if (Integer.parseInt(currentTokens[0]) > orderIdTracker) {
                    orderIdTracker = Integer.parseInt(currentTokens[0]);
                }
                dates.add(orderDate);// add order dates to Hashset
                orders.put(currentOrder.getOrderId(), currentOrder);

            }
            // close scanner
            scanner.close();

            //comment for new commit   
        }
        return orderIdTracker;
    }

    @Override
    public void writeOrders() throws FMMemoryPersistenceException {

        PrintWriter out;

        File dir = new File(ORDER_FOLDER);
        File[] files = dir.listFiles();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

        for (File file : files) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        Set<String> dates = new HashSet<>();

        for (Order order : displayOrders()) {
            dates.add(order.getOrderDate().format(formatter));
        }

        for (String date : dates) {
//            //construct a path, that path will go to the orders folder 
//            //will be the flooringOrders/Orders_date.txt"
            String path = "floororders/Orders_" + date + ".txt";
            try {
                out = new PrintWriter(new FileWriter(path));

            } catch (IOException e) {
                throw new FMMemoryPersistenceException("Could not save Order data.", e);
            }

            List<Order> orders = this.displayOrders();
            for (Order currentOrder : orders) {
                if (currentOrder.getOrderDate().format(formatter).equals(date)) {
                    // write the Inventory object to the file
                    out.println(currentOrder.getOrderId() + DELIMITER
                            + currentOrder.getCustomerName() + DELIMITER
                            + currentOrder.getState() + DELIMITER
                            + currentOrder.getTaxRate() + DELIMITER
                            + currentOrder.getProductType() + DELIMITER
                            + currentOrder.getArea() + DELIMITER
                            + currentOrder.getCostPerSqFoot() + DELIMITER
                            + currentOrder.getLaborCostPersqFt() + DELIMITER
                            + currentOrder.getMaterialCost() + DELIMITER
                            + currentOrder.getLaborCost() + DELIMITER
                            + currentOrder.getTax() + DELIMITER
                            + currentOrder.getOrderTotal());

                    out.flush();
                }
            }
            // Clean up
            out.close();
        }
    }
}
