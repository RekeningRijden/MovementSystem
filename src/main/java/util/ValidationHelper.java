/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marijn
 */
public class ValidationHelper {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            Logger.getLogger(ValidationHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } catch (NullPointerException e) {
            Logger.getLogger(ValidationHelper.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
