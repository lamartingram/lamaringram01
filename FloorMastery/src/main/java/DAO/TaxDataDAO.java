/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.TaxData;
import ServicePackage.FMMemoryPersistenceException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxDataDAO {

    TaxData getTax(String state);

    List<TaxData> displayTax();

    TaxData addTax(String state, TaxData tax);

    TaxData removeTax(String state);

    public void loadTax() throws FMMemoryPersistenceException;

}
