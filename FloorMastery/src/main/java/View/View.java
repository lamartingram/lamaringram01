/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Order;
import Model.Product;
import Model.TaxData;
import MyUserIO.UserIO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int printMenuAndSelection() {
        io.print("***********FLOORING PROGRAM MAIN MENU ***********");
        io.print("1.***DISPLAY ORDERS***");
        io.print("2.*** ADD AN ORDER*** ");
        io.print("3.***EDIT AN ORDER***");
        io.print("4.***REMOVE AN ORDER");
        io.print("5.*** SAVE CURRENT WORK***");
        io.print("6.***QUIT***");
        return io.readInt("Please select from the above choices.", 1, 6);

    }

    public void displayOrders(List<Order> orders) {
        for (Order order : orders) {
            io.print("OrderId: " + order.getOrderId());
            io.print("Customer Name: " + order.getCustomerName() + "| State: "
                    + order.getState() + "| State Tax Rate: " + order.getTaxRate());
            io.print("Area: " + order.getArea() + "| Materials: "
                    + order.getProductType() + "| Materials Cost Per Sq Foot: "
                    + order.getCostPerSqFoot());
            io.print("Labor Cost per Sq Foot: " + order.getLaborCostPersqFt()
                    + "Total Matierals Cost: " + order.getMaterialCost() + "Total "
                    + "Labor Cost: " + order.getLaborCost());
            io.print("Taxes: " + order.getTax());
            io.print("=====");
            io.print("Total Cost: " + order.getOrderTotal());
            io.print("******************************");
        }
    }

    public void displayAddedOrder(Order order) {
        io.print("OrderId: " + order.getOrderId());
        io.print("Customer Name: " + order.getCustomerName() + "| State: "
                + order.getState() + "| State Tax Rate: " + order.getTaxRate());
        io.print("Area: " + order.getArea() + "| Materials: "
                + order.getProductType() + "| Materials Cost Per Sq Foot: "
                + order.getCostPerSqFoot());
        io.print("Labor Cost per Sq Foot: " + order.getLaborCostPersqFt()
                + "Total Matierals Cost: " + order.getMaterialCost() + "Total "
                + "Labor Cost: " + order.getLaborCost());
        io.print("Taxes: " + order.getTax());
        io.print("=====");
        io.print("Total Cost: " + order.getOrderTotal());
        io.print("******************************");
    }

    public Order getUserOrderInput(LocalDate date, String name, String state, String productType, BigDecimal area) {
        Order userOrder = new Order(name);
        userOrder.setCustomerName(name);
        userOrder.setState(state);
        userOrder.setOrderDate(date);
        userOrder.setArea(area);
        userOrder.setProductType(productType);
        return userOrder;
    }

    public int getOrderNumber() {
        int getOrderId = 0;
        boolean isValidEntry;
        while (isValidEntry = true) {
            getOrderId = io.readInt("Please enter in the Order ID Number");
            if (getOrderId < 0) {
                isValidEntry = false;
            } else {
                isValidEntry = true;
                break;
            }
        }
        return getOrderId;
    }

    public String getName() {
        String getName = " ";
        boolean isValidEntry = false;
        while (!isValidEntry) {
            getName = io.readString("Please Enter A Name For The Order");
            if (getName.contains(",")) {
                isValidEntry = false;
                io.print("At this time, our system does not support commas. "
                        + "Please try again");
            } else {
                isValidEntry = true;
                break;
            }
        }
        return getName;
    }

    public String getState() {
        boolean isState = true;
        String answer = io.readStringRead("Please Enter A State Abbrevation");
        String toUpperCase = answer.toUpperCase();
        return toUpperCase;
    }

    public LocalDate getDate() {
        return io.readLocalDate("Please Enter The Date(MMDDYYYY)");

    }

    public BigDecimal getArea() {

        return io.readBigDecimal("Please Enter the Area of the Room");
    }

    public BigDecimal getTaxRate() {
        return io.readBigDecimal("Please Enter the Tax Rate");

    }

    public BigDecimal CostPerSquareFoot() {
        return io.readBigDecimal("Please Enter the Cost Per Square Foot");

    }

    public BigDecimal LaborPerSquareFoot() {
        return io.readBigDecimal("Please Enter the Labor Per Square Foot");

    }

    public BigDecimal getMaterialsCost() {
        return io.readBigDecimal("Please Enter the Materials Cost");

    }

    public BigDecimal getTotalLaborCost() {
        return io.readBigDecimal("Please Enter the Total Labor Cost");

    }

    public BigDecimal getTaxes() {
        return io.readBigDecimal("Please Enter the Taxes");

    }

    public BigDecimal getTotalCost() {
        return io.readBigDecimal("Please Enter the Order Total");
    }

    public String getProductType() {

        String productType = io.readStringRead("Please Select Flooring Material");
        productType.equalsIgnoreCase(productType);
        return productType;
    }

    public int getOrderToEdit() {
        io.print("What Field Would You Like To Edit?");
        io.print("1. Name");
        io.print("2. State/Location");
        io.print("3. Floor Area");
        io.print("4. Product Type (Carpet, Tile, etc)");
        io.print("5.Order Date");
        io.print("6. Tax Rate");
        io.print("7. Cost Per Square Foot");
        io.print("8. Labor Per Square Foot");
        io.print("9. Total Labor Cost");
        io.print("10. Materials Cost");
        io.print("11. Taxes");
        io.print("12. Order Total");
        io.print("13. Exit");
        return io.readInt("Please Make A Selection", 1, 13);
    }

    public String confirmRemoveOrder() {
        return io.readString("Confirm Removal. (Y) to Remove Order or (N) to "
                + "Return To Main Menu");
    }

    public String confirmEditOrder() {
        return io.readString("Confirm you want to edit this order. (Y) to save order or (N) to restore old order"
                + "Return To Main Menu");
    }

    public void notEditingOrder() {
        io.print("Okay order will not be edited");
    }

    public String confirmAddOrder() {
        return io.readString("Confirm Add. (Y) to Add Order or (N) to "
                + "Return To Main Menu");
    }

    public void saveOrderTraining() {
        io.print("==YOU ARE IN TRAINING MODE==");
        io.print("Were We In Production, Your Progress Would Save Here");
    }

    public String confirmExit() {

        String confirmExit = io.readString("Are You Sure You'd Like to Exit?"
                + " Enter (Y) to Exit or (N) to Keep Editing");
        return confirmExit;
    }
    //******Banners********

    public void displayAddOrderBanner() {
        io.print("==Create Order==");
    }

    public void displayAbandonAddOrderBanner() {
        io.print("Your Order Was Not Created or Saved.");
    }

    public void displaySuccessfullyAddedOrderBanner() {
        io.print("You've Successfully Created An Order. Hit Enter To Continue");
    }

    public void displayRemoveOrderBanner() {
        io.print("==Remove Order==");
    }

    public void displaySuccessfullyRemovedOrderBanner() {
        io.print("You've Successfully Removed An Order.");
    }

    public void displayEditOrderBanner() {
        io.print("==Edit Order==");
    }

    public void displaySuccessfullyEditedOrderBanner() {
        io.print("You've Successfully Edited An Order. Hit Enter To Continue");
    }

    public void displayDisplayOrdersBanner() {
        io.print("==Display Orders==");
    }

    public void displaySaveProgressBanner() {
        io.print("==Save Progress==");
    }

    public void displaySuccessfullySavedProgressBanner() {
        io.print("Your Progress Has Been Saved. Hit Enter To Continue");
    }

    public void displayExitProgramBanner() {
        io.print("==Exit Program==");
    }

    public void displayErrorMessage(String e) {
        io.print("=== ERROR ===");
        io.print(e);
    }

    public void displayUnknownCommandMsg() {
        io.print("=== UNKNOWN COMMAND ===");
        io.print("Please Enter A Whole Number Value Between 1-6."
                + " Press Enter To Continue");
    }

    public void displayExitMessage() {
        io.print(" GOODBYEEEEEE!!!!");

    }

    public void displayModeTraining() {

        io.print("YOU ARE IN TRAINING MODE");

    }

    public void displayModeProduction() {

        io.print("YOU ARE IN PRODUCTION MODE");

    }

    public Order getOrderInfo(List<Product> products, List<TaxData> taxes) {
        Order o = new Order();

        //get Name
        boolean isName = true;
        while (isName) {
            String name = io.readString("Please Enter Name");
            if (name.isEmpty()) {
            } else {
                o.setCustomerName(name);
                isName = false;
            }
        }

        //get Date
        LocalDate date = io.readLocalDate("Please Enter The Date(MM-DD-YYYY)");
        o.setOrderDate(date);

        //get Are
        boolean isArea = true;
        while (isArea) {
            try {
                String area = io.readString("Enter Area of Space");
                if (area.isEmpty()) {
                } else {
                    BigDecimal bdArea = new BigDecimal(area);
                    o.setArea(bdArea);
                    isArea = false;
                }
            } catch (NumberFormatException e) {
                io.print("Area must be entered as a number");
            }

        }
        //getState
        boolean isState = true;
        while (isState) {
            io.print("States We Serve: ");
            printState(taxes);
            String state = io.readString("Please Enter Your State");
            if (state.isEmpty()) {
            } else {
                for (TaxData t : taxes) {
                    if (t.getState().equalsIgnoreCase(state)) {
                        o.setState(state);
                        isState = false;
                    }
                }
            }
        }
        //get product
        boolean isProduct = true;
        while (isProduct) {
            io.print("Available Materials: ");
            printProducts(products);
            String product = io.readString("Please Select a Product Type");
            if (product.isEmpty()) {
            } else {
                for (Product p : products) {
                    if (p.getProductType().equalsIgnoreCase(product)) {
                        o.setProductType(product);
                        isProduct = false;
                    }
                }
            }
        }
        return o;
    }

    public void printProducts(List<Product> pList) {
        for (Product product : pList) {
            io.print(product.getProductType() + ",");
        }
//        pList.stream().forEach((p) -> {
//            io.print(p.getProductType() + ", ");// practices using llamdas
//        });

    }

    public void printState(List<TaxData> tList) {
        for (TaxData tax : tList) {
            io.print(tax.getState() + ",");
        }
//        tList.stream().forEach((t) -> {
//            io.print(t.getState() + ", ");
//        });

    }

    public Order editOrder(List<Product> pList, List<TaxData> tList, Order editOrder) {

        Order o = editOrder;
        //get Name
        boolean isName = true;
        while (isName) {
            String name = io.readString("Please Enter Name if you would like to change your name from " + editOrder.getCustomerName());

            if (name == null || name.equals("")) {

                isName = false;
            } else {
                editOrder.setCustomerName(name);
                isName = false;
            }
        }

        //get Date
        LocalDate date = io.readLocalDate("Please Enter The Date(MM-DD-YYYY) if you would like to change you date from :" + " " + editOrder.getOrderDate());
        o.setOrderDate(date);

        //get Are
        boolean isArea = true;
        while (isArea) {
            try {
                String area = io.readString("Enter Area of Space if you would like to change your Area from :" + " " + editOrder.getArea());
                if (area == null || area.equals("")) {
                    isArea = false;

                } else {
                    BigDecimal bdArea = new BigDecimal(area);
                    o.setArea(bdArea);
                    isArea = false;
                }
            } catch (NumberFormatException e) {
                io.print("Area must be entered as a number");
            }

        }
        //getState
        boolean isState = true;
        while (isState) {
            io.print("States We Serve: ");
            printState(tList);
            String state = io.readString("Please Enter A State if you would like to change your State from : " + " " + editOrder.getState());
            if (state == null || state.equals("")) {
                isState = false;
            } else {
                for (TaxData t : tList) {
                    if (t.getState().equalsIgnoreCase(state)) {
                        o.setState(state);
                        isState = false;
                    }
                }
            }
        }
        //get product
        boolean isProduct = true;
        while (isProduct) {
            io.print("Available Materials: ");
            printProducts(pList);
            String product = io.readString("Please Select a Product Type if you would like to change your product type from :" + " " + editOrder.getProductType());
            if (product == null || product.equals("")) {
                isProduct = false;
            } else {
                for (Product p : pList) {
                    if (p.getProductType().equalsIgnoreCase(product)) {
                        o.setProductType(product);
                        isProduct = false;
                    }
                }
            }
        }
        return o;
    }

    public void noSuchOrder() {
        io.print("No Such order ");
    }
}
