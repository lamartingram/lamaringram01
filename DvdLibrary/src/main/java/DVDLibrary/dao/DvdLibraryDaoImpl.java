/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.dao;

import DVDlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {
// Dao only needs to CRUDS

    private Map<String, Dvd> dvdCollection = new HashMap<>();

    public static final String DVD_FILE = "out.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        loadDvdCollection();
        dvdCollection.put(dvd.getTitle(), dvd);//
        writeDvdCollection();
        return dvd;
    }

    //method for removing DVD from collection
    @Override
    public void removeDvd(String title) throws DvdLibraryDaoException {
        loadDvdCollection();
        dvdCollection.remove(title);
        writeDvdCollection();

    }

    public Dvd editDvd(Dvd dvd, String editInfo, int editChoice) throws DvdLibraryDaoException {
        loop:
        switch (editChoice) {
            case 1:
                Dvd oldDvd = dvdCollection.get(dvd.getTitle());//COPY THE DVD OBJECT TO PLACEHOLDER
                dvdCollection.remove(dvd.getTitle()); //REMOVE OLD DVD FROM MAP
                dvd = oldDvd; //COPY PROPS FROM OLD TO NEW
                dvd.setTitle(editInfo); //SET NEW TITLE OF EDITED DVD
                dvdCollection.put(dvd.getTitle(), dvd);
                break;
            case 2:
                dvdCollection.get(dvd.getTitle()).setReleaseDate(editInfo);
                break;
            case 3:
                dvdCollection.get(dvd.getTitle()).setMpaaRating(editInfo);
                break;
            case 4:
                dvdCollection.get(dvd.getTitle()).setDirectorsName(editInfo);
                break;
            case 5:
                dvdCollection.get(dvd.getTitle()).setStudio(editInfo);
                break;
            case 6:
                dvdCollection.get(dvd.getTitle()).setUserRating(editInfo);
                break;
            case 7:
                break loop;
            default:
                break;
        }
        writeDvdCollection();
        return dvd;

    }

    public List<Dvd> listDvdCollection() throws DvdLibraryDaoException {
        loadDvdCollection();
        return new ArrayList<Dvd>(dvdCollection.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadDvdCollection();
        return dvdCollection.get(title);

    }
// method for searching dvd by title

    @Override
    public ArrayList<Dvd> searchByTitle(String searchItem) throws DvdLibraryDaoException {
        loadDvdCollection();
        List<Dvd> list = listDvdCollection();
        ArrayList<Dvd> arrayList = new ArrayList<>();

        for (Dvd k : list) {// go through and every dvd call it k
            if (k.getTitle().contains(searchItem)) {
                arrayList.add(k);
            }
        }
        return arrayList;
    }

    //****************************************************************************
    // file marshalling and unmarshalling
    public void loadDvdCollection() throws DvdLibraryDaoException {
        Scanner scanner;
        try {
            //CREATE SCANNER FOR READING FILE
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));//fileReader -Finds,uffered -reads, Scanner- next line
        } catch (FileNotFoundException e) {//catch error stores in e
            throw new DvdLibraryDaoException("Couldnt load Dvd data", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            //create new dvd 
            Dvd currentDvd = new Dvd(currentTokens[0]);
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorsName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);

            dvdCollection.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }

    public void writeDvdCollection() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException("Could not save data.", e);
        }
        List<Dvd> dvdList = this.listDvdCollection();

        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER + currentDvd.getReleaseDate() + DELIMITER + currentDvd.getMpaaRating() + DELIMITER + currentDvd.getDirectorsName() + DELIMITER + currentDvd.getStudio() + DELIMITER + currentDvd.getUserRating());
            out.flush();
        }
        out.close();
    }

}
