/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import DVDlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdLibraryDao {

    Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException;

    void removeDvd(String dvdTitle) throws DvdLibraryDaoException;

    Dvd editDvd(Dvd dvd, String editInfo, int editChoice) throws DvdLibraryDaoException;

    List<Dvd> listDvdCollection() throws DvdLibraryDaoException;

    Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException;

    List<Dvd> searchByTitle(String searchItem) throws DvdLibraryDaoException;

    //marshalling and Unmarshalling
    void loadDvdCollection() throws DvdLibraryDaoException;

    void writeDvdCollection() throws DvdLibraryDaoException;
}
