/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Product;
import ServicePackage.FMMemoryPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ProductInventoryStub implements ProductInventory {

    private Product onlyProduct;
    private List<Product> products = new ArrayList<>();

    public ProductInventoryStub() {
        onlyProduct = new Product("Wood");
        onlyProduct.setProductType("Wood");
        onlyProduct.setCostPerSqFoot(new BigDecimal(("10")));
        onlyProduct.setLaborCostPerSqFoot(new BigDecimal("6.0"));

        products.add(onlyProduct);// dummy data
    }

    @Override
    public Product getProduct(String productType) {
        if (productType.equals(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }

    }

    @Override
    public List<Product> displayProducts() {
        return products;
    }

    @Override
    public Product removeProduct(String productType) {
        if (productType.equals(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public Product addProduct(String productType, Product product) {
        if (productType.equals(onlyProduct.getProductType())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public void loadProducts() throws FMMemoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
