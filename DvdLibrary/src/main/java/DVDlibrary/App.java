/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDlibrary;

import DVDLibrary.dao.DvdLibraryDao;
import DVDLibrary.dao.DvdLibraryDaoImpl;
import DVDlibrary.controller.DvdLibraryController;
import DVDlibrary.ui.DvdView;
import DVDlibrary.ui.OfficalUserIoImpl;
import DVDlibrary.ui.UserIO;
import DVDlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author apprentice
 */
public class App {

    public static void main(String[] args) {
        UserIO myIO = new OfficalUserIoImpl(); //CHOSING AND INSTANTIATING IMPLEMENTATION
        DvdView myView = new DvdView(myIO); //CONSTRUCTION OF DVDVIEW, PASSING IO IMPL, AND INSTANTIATING VIEW
        DvdLibraryDao myDao = new DvdLibraryDaoImpl(); //instantiating DAO IMPL
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }
}
