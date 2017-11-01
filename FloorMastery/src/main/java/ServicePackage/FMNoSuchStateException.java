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
public class FMNoSuchStateException extends Exception {

    public FMNoSuchStateException(String message) {
        super(message);

    }

    public FMNoSuchStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
