/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ServicePackage.FMMemoryPersistenceException;

/**
 *
 * @author apprentice
 */
public interface ModeDAO {

    boolean checkMode() throws FMMemoryPersistenceException;
}
