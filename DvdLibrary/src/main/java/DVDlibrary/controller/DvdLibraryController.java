/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDlibrary.controller;

import DVDLibrary.dao.DvdLibraryDao;
import DVDLibrary.dao.DvdLibraryDaoException;
import DVDLibrary.dao.DvdLibraryDaoImpl;
import DVDlibrary.dto.Dvd;
import DVDlibrary.ui.DvdView;
import DVDlibrary.ui.UserIO;
import DVDlibrary.ui.UserIOConsoleImpl;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DvdLibraryController {

    private DvdLibraryDao dao;
    private DvdView view;

    public DvdLibraryController(DvdLibraryDao dao, DvdView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        createNewDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvdInfo();
                        break;
                    case 4:
                        listDvdCollection();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        searchByTitle();

                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    //******************************   

    private void createNewDvd() throws DvdLibraryDaoException {
        view.displayBannerPrint("ADD NEW DVD TO COLLECTION");
        Dvd newDVD = view.getNewDVDInfo();
        dao.addDvd(newDVD);// add this new dvd by the info it gets from the view
        view.displaySuccessBanner("Dvd added successfully. ");
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayBannerPrint("REMOVE DVD FROM COLLECTION");
        String dvdTitle = view.getDvdByTitleChoice();
        if (dao.getDvd(dvdTitle) != null) { //CHECK THAT THE DVD TITLE EXISTS IN COLLECTION
            dao.removeDvd(dvdTitle);
            view.displaySuccessBanner("The DVD was successfully removed.");
        } else {
            view.displayDoesNotExist();
        }
    }

    private void editDvdInfo() throws DvdLibraryDaoException {
        boolean editAnother = true;
        String editDvd;
        int editAnotherNum;
        view.displayBannerPrint("EDIT DVD PROPERTY");
        String dvdTitle = view.getDvdByTitleChoice(); //PROMPTS FOR DVD ID

        Dvd dvd = dao.getDvd(dvdTitle); //PASSES THIS TO DAO TO RETURN DVD

        if (dvd != null) {

            thisLoop:

            while (editAnother) {
                int editChoice = view.displayEditDvdMenu(); //DISPLAYS OPTIONS 1 - 6 FOR MODIFYING DVD PROPERTY
                editDvd = view.displayEditDvdPrompt();//Passes the Dao the edit info
                dvd = dao.editDvd(dvd, editDvd, editChoice);

                editAnotherNum = view.displayEditAnotherPrompt();// What would you like to set the field too

                switch (editAnotherNum) {

                    case 1:
                        editAnother = true;   // The user had to enter 1 for Yess
                        break;
                    case 2:
                        editAnother = false;  // The user had to enter 2 for Noo
                        break;
                    default:
                        break;
                }

            }
        } else {
            view.displayDoesNotExist();
        }

    }

    private void listDvdCollection() throws DvdLibraryDaoException {
        view.displayBannerPrint("List all DVDs");
        List<Dvd> dvdList = dao.listDvdCollection();
        view.displayDVDList(dvdList);
    }
    //******************************

    private void viewDvd() throws DvdLibraryDaoException {
        view.displayBannerPrint("View dvd info");
        String title = view.getDvdByTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void searchByTitle() throws DvdLibraryDaoException {

        String s = view.takeAndReturnString("Enter the title of the movie you will like to search");
        List<Dvd> listOfDvd = dao.searchByTitle(s);

        view.displayDVDList(listOfDvd);

    }
    //*******************************

    private void unknownCommand() throws DvdLibraryDaoException {
        view.displayUnknownCommandBanner();
    }
    //**********************************

    private void exitMessage() throws DvdLibraryDaoException {
        view.displayExitBanner();
    }
}
