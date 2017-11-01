/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import ServicePackage.FMMemoryPersistenceException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductInventory {

    Product getProduct(String productType);

    List<Product> displayProducts();

    Product removeProduct(String productType);

    Product addProduct(String productType, Product product);

    public void loadProducts() throws FMMemoryPersistenceException;
}

