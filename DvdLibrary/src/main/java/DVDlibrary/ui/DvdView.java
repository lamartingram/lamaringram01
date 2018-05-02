/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDlibrary.ui;

import DVDlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DvdView {

    private UserIO io;// allow for dependency Injection 
    private static final String NOMOVIE = "Dvd doesnt exist"; //its a Constant

    public DvdView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. ---------->Add DVD to the collection<----------------------");
        io.print("2. ---------->Remove a DVD from the collection<---------------");
        io.print("3. ---------->Edit the information for an existing DVD<-------");
        io.print("4. ---------->List the DVDs in the collection<-----------------");
        io.print("5. ---------->Display DVD Info<--------------------------------");
        io.print("6. ---------->Search for DVD by Title<-------------------------");
        io.print("7. ---------->Exit<--------------------------------------------");

        return io.readInt("Please select from the choices above");
    }
//Methods for adding dvd to collection

    public Dvd getNewDVDInfo() {
        Dvd currentDVD = new Dvd(io.readString("Please enter DVD Title"));//SETS THE Title WITH CONSTRUCTOR

        String releaseDate = io.readString("Please enter DVD release Date");
        String mpaaRating = io.readString("Please enter DVD MPAA Rating");
        String directorsName = io.readString("Please enter the directors name");
        String studio = io.readString("Please enter the prodcution studio");
        String userRating = io.readString("Please enter Ratings");

        // Dvd currentDVD = new Dvd(dvdTitle);//SETS THE TITLE WITH CONSTRUCTOR
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);

        return currentDVD;
//**********************************************************************************************************************

    }
//methods for editing the dvd

    public String getDvdByTitleChoice() {
        return io.readString("Please enter the DVD Title");
    }

    public void displayDoesNotExist() {
        io.print(NOMOVIE);
    }

    public int displayEditDvdMenu() {
        io.print("Edit Menu");
        io.print("1. Edit Title");
        io.print("2. Edit Release Date");
        io.print("3. Edit MPAA Rating");
        io.print("4. Edit the directors name");
        io.print("5. Edit the studio");
        io.print("6. Edit the user rating");
        io.print("7. Exit");

        return io.readInt("Please select from the choices above", 1, 7);

    }

    public String takeAndReturnString(String string) {
        return io.readString(string);
    }

    public String displayEditDvdPrompt() {
        return io.readString("What would you like  to set the field to?");
    }

    public int displayEditAnotherPrompt() {
        io.print("Would You like to edit another property for this DVD?");
        io.print("1 for Yesssss");
        io.print("2 for NOOOOOO");

        return io.readInt("Select you choice 1 or 2", 1, 2);
    }
//*****************************************************************************************************************

    public void displayDVDList(List<Dvd> dvdList) {
        //enhanced for loop through array list
        for (Dvd currentDVD : dvdList) {
            io.print(currentDVD.getTitle());//prints off all of the Dvds by Titles

        }
        io.readString("Enter to Continue");
    }

    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " - RELEASED: " + dvd.getReleaseDate() + " MPAA RATING: " + dvd.getMpaaRating() + " DIRECTOR: " + dvd.getDirectorsName() + " STUDIO: " + dvd.getStudio() + " USER RATING: " + dvd.getUserRating());
        } else {
            io.print(NOMOVIE);
        }
        io.readString("Enter to Continue");

    }

    public String displaySearchResults() {
        return io.readString("Enter the title of the Dvd you would like to search for: ");
    }

    public void printTitleResults(List<Dvd> matchedDvds) {
        if (matchedDvds != null) {
            io.print("This Title has the following results:");
            for (Dvd k : matchedDvds) {
                io.print(k.getTitle());
            }

        } else {
            io.print(NOMOVIE);
        }
        io.readString("Enter To Continue");
    }

    //**********************************************************************
    public void displayBannerPrint(String myBannerTitle) {
        io.print("======= " + myBannerTitle + " =======");
    }

    public void displaySuccessBanner(String myBannerSuccess) {
        io.readString(myBannerSuccess + " Press enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Goodbye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command! Please try your selection again");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("------ ERROR -----");
        io.print(errorMsg);
    }

}
