/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppClass;

import Controller.FlooringController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {

//        UserIO myIo = new UserIOConsoleImpl();
//        View myView = new View(myIo);
//        TaxDataDAO myDao = new TaxDataDAOImpl();
//
//        ModeDAO myModeDao = new ModeDAOImpl();
//        OrderRecords myOrderRecords = new OrderRecordsImpl();
//        ProductInventory myProductInventory = new ProductInventoryImpl();
//
//        ServiceLayer myService = new ServiceLayerImpl(myOrderRecords, myProductInventory, myDao, myModeDao);
//        FlooringController controller = new FlooringController(myService, myView);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = ctx.getBean("controller", FlooringController.class);
        controller.run();
    }
}
