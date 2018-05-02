/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import java.io.FileNotFoundException;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoException extends Exception {

    public DvdLibraryDaoException(String message){
        super(message);// super is refering to the parent which is calling the construter from Exception? parent
       
    }

    public DvdLibraryDaoException(String message, Throwable cause) {
        super(message, cause);// make you able to throw it
    }

}


    

