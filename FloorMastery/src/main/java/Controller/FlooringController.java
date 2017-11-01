/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Order;
import Model.Product;
import Model.TaxData;
import ServicePackage.FMMemoryPersistenceException;
import ServicePackage.FMNoOrdersOnDateException;
import ServicePackage.ServiceLayer;
import ServicePackage.InvaildOrderException;
import View.View;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    private ServiceLayer service;
    private View view;

    public FlooringController(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }
    boolean keepGoing = true;

    public void run() {

        try {
            service.loadOrders();
            service.loadProducts();
            service.loadTax();
            boolean isMode = service.readMode();
            if (isMode) {
                view.displayModeTraining();
            } else {
                view.displayModeProduction();
            }

            while (keepGoing) {
                int menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();

                        break;
                    case 3:
                        try {
                            editOrder();
                        } catch (FMNoOrdersOnDateException e) {
                            view.displayErrorMessage(e.getMessage());

                        }

                        break;
                    case 4:

                        removeOrder();

                        break;
                    case 5:
                        if (isMode) {
                            view.saveOrderTraining();
                        } else {
                            saveOrder();
                        }
                        break;
                    case 6:
                        String confirmExit = view.confirmExit();
                        if (confirmExit.equalsIgnoreCase("y")) {

                            keepGoing = false;

                        }
                        break;

                    default:
                        unknownCommand();
                }
            }

            exitMessage();

        } catch (FMMemoryPersistenceException | FMNoOrdersOnDateException | InvaildOrderException e) {
            view.displayUnknownCommandMsg();
        }
    }

    private void addOrder() {
        try {

            List<TaxData> taxes = service.getAllTax();
            List<Product> products = service.getAllProducts();
            Order userOrder = view.getOrderInfo(products, taxes);
            Order userOrder2 = service.calculateProductsOrder(userOrder);

            int orderId = service.generateOrderId();
            userOrder2.setOrderId(orderId);

            view.displayAddedOrder(userOrder2);
            String answer = view.confirmAddOrder();
            if (answer.equalsIgnoreCase("y")) {
                service.addOrder(userOrder2.getOrderId(), userOrder2);
                view.displaySuccessfullyAddedOrderBanner();
            } else {
                view.displayAbandonAddOrderBanner();
            }
        } catch (InvaildOrderException e) {
            view.displayErrorMessage("unable To add order");
        }
    }

    private void removeOrder() throws InvaildOrderException, FMNoOrdersOnDateException, FMMemoryPersistenceException {
        try {
            view.displayRemoveOrderBanner();
            int orderId = view.getOrderNumber();
            LocalDate date = view.getDate();
            String confirm = view.confirmRemoveOrder();
            service.confirmRemoveOrder(confirm);
            if (confirm.equalsIgnoreCase("y")) {
                service.removeOrder(orderId, date);
                service.writeOrders();
                view.displaySuccessfullyRemovedOrderBanner();
            }

            view.noSuchOrder();
        } catch (InvaildOrderException e) {
            view.displayErrorMessage("Invalid Order");
        }

    }

    private void displayOrders() {
        view.displayDisplayOrdersBanner();
        LocalDate date = view.getDate();

        List<Order> orders = service.displayAllOrdersByDate(date);
        view.displayOrders(orders);

    }

    private void editOrder() throws FMNoOrdersOnDateException {
        view.displayEditOrderBanner();
        try {
            LocalDate dateToEdit = view.getDate();
            int orderNumberToEdit = view.getOrderNumber();
            Order editToOrder = service.getOneOrder(orderNumberToEdit, dateToEdit);
            List<TaxData> taxes = service.getAllTax();
            List<Product> products = service.getAllProducts();
            Order editedOrder = view.editOrder(products, taxes, editToOrder);
            editedOrder = service.calculateProductsOrder(editedOrder);
            String confirmation = view.confirmEditOrder();
            if (confirmation.equalsIgnoreCase("Y")) {
                service.addOrder(editedOrder.getOrderId(), editedOrder);
            } else {
                view.notEditingOrder();
            }
        } catch (InvaildOrderException e) {
            view.displayEditOrderBanner();
        }

    }

    private void saveOrder() throws FMMemoryPersistenceException {
        view.displaySaveProgressBanner();
        service.writeOrders();
        view.displaySuccessfullySavedProgressBanner();
    }

    private void saveOrderTraining() {
        view.displaySaveProgressBanner();
        view.saveOrderTraining();
    }

    private int getMenuSelection() {
        return view.printMenuAndSelection();
    }

    private void unknownCommand() {
        view.displayUnknownCommandMsg();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }
}
