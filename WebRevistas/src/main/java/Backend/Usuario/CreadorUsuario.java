/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Usuario;

import Backend.DataBase.DBRevistas;
import Backend.Exceptions.UserDataException;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author gabrielh
 */
public class CreadorUsuario {
    private DBRevistas db = new DBRevistas();

   public Usuario crearUsuario(HttpServletRequest req) throws UserDataException {
        Usuario newUser = validarLogin(req);
        
        return newUser;
    }
    
    private Usuario validarLogin(HttpServletRequest req) throws UserDataException{
        Usuario newUser = new Usuario();
        try {
            newUser.setUserName(req.getParameter("userName"));
           //newUser.setRol(Roles.valueOf(req.getParameter("rol")));
            newUser.setPassword(req.getParameter("password"));
           //newUser.setCartera(Float.parseFloat(req.getParameter("credito")));
        } catch (IllegalArgumentException
                | NullPointerException e) {
            throw new UserDataException("Error en los datos enviados");
        }
        
        if (newUser.esValido()) {
            return newUser;
        }
        
        throw new UserDataException("Error en los datos enviados");
    }
    
}
