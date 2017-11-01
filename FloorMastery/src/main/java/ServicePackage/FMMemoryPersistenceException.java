/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicePackage;

/**
 *
 * @author apprentice
 */
public class FMMemoryPersistenceException extends Exception{
    public FMMemoryPersistenceException(String message){
        super(message);
        
    }
    public FMMemoryPersistenceException(String message, Throwable cause){
        super(message,cause);
    }
}
