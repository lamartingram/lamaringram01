/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ServicePackage.FMMemoryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ModeDAOImpl implements ModeDAO {

    public static final String CONFIG_FILE = "config.txt";

    @Override
    public boolean checkMode() throws FMMemoryPersistenceException {
        Boolean isTraining = true;
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(CONFIG_FILE)));
        } catch (FileNotFoundException e) {
            throw new FMMemoryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
        String currentLine;

        currentLine = scanner.nextLine();
        isTraining = !currentLine.equals("mode=Production");

        scanner.close();

        return isTraining;
    }
}
