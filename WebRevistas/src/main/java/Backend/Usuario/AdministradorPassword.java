/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Usuario;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gabrielh
 */
public class AdministradorPassword {


    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); 
    }
    
     public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
     
}
