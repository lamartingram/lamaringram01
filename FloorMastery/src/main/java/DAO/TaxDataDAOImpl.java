/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.OrderRecordsImpl.DELIMITER;
import Model.TaxData;
import ServicePackage.FMMemoryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxDataDAOImpl implements TaxDataDAO {

    private Map<String, TaxData> taxes = new HashMap();
    public static final String TAX_FILE = "tax.txt";
    public static final String DELIMITER = ",";

    @Override

    public TaxData getTax(String state) {
        return taxes.get(state);
    }

    @Override
    public List<TaxData> displayTax() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public TaxData addTax(String state, TaxData tax) {
        TaxData newTax = taxes.put(state, tax);
        return newTax;
    }

    @Override
    public TaxData removeTax(String state) {
        TaxData removeTax = taxes.remove(state);
        return removeTax;
    }

    @Override
    public void loadTax() throws FMMemoryPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FMMemoryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            TaxData currentTax = new TaxData(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            // Put currentTax into the map using state as the key
            taxes.put(currentTax.getState(), currentTax);
        }
        // close scanner
        scanner.close();
        //comment for new commit   
    }
}
