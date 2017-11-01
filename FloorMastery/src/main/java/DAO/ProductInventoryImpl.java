/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import ServicePackage.FMMemoryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ProductInventoryImpl implements ProductInventory {

    private Map<String, Product> products = new HashMap();

    public static final String PRODUCTS_FILE = "products.txt";
    public static final String DELIMITER = ",";
   

    @Override
    public Product getProduct(String productType) {
        return products.get(productType);
    }

    @Override
    public List<Product> displayProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product removeProduct(String productType) {
        Product removeProduct = products.remove(productType);
        return removeProduct;
    }

    @Override
    public Product addProduct(String productType, Product product) {
        Product newProduct = products.put(productType, product);
        return newProduct;
    }

    @Override
    public void loadProducts() throws FMMemoryPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FMMemoryPersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPerSqFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSqFoot(new BigDecimal(currentTokens[2]));

            // Put Product into the map using product ID as the key
            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
        //comment for new commit   
    }
}
