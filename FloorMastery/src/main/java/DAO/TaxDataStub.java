/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.TaxData;
import ServicePackage.FMMemoryPersistenceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class TaxDataStub implements TaxDataDAO {

    TaxData onlyTax;
    List<TaxData> taxes = new ArrayList<>();

    

    public TaxDataStub() {
        onlyTax = new TaxData("Michigan");
        onlyTax.setTaxRate(new BigDecimal("1.0"));

        taxes.add(onlyTax);// dummy data
    }

    @Override
    public TaxData getTax(String state) {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }

    }

    @Override
    public List<TaxData> displayTax() {
        return taxes;
    }

    @Override
    public TaxData addTax(String state, TaxData tax) {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }
    }

    @Override
    public TaxData removeTax(String state) {
        if (state.equals(onlyTax.getState())) {
            return onlyTax;
        } else {
            return null;
        }

    }

    @Override
    public void loadTax() throws FMMemoryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
