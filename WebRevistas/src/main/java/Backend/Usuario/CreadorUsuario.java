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
    private AdministradorPassword admin = new AdministradorPassword();

   public Usuario crearUsuario(HttpServletRequest req) throws UserDataException {
        Usuario newUser = validarLogin(req);
        try{
        if(db.usuarioExistente(newUser.getUserName())){
            throw new UserDataException("UserName ya existe!");
        }
        newUser.setPassword(admin.hashPassword(newUser.getPassword()));
        db.guardarUsuario(newUser);
        
        return newUser;
        }finally {
            db.close(); // Asegurar que la conexión se cierra
        }
    }
    
    private Usuario validarLogin(HttpServletRequest req) throws UserDataException{
        Usuario newUser = new Usuario();
        try {
            newUser.setUserName(req.getParameter("userName"));
            newUser.setPassword(req.getParameter("password"));
            newUser.setRol(Roles.valueOf(req.getParameter("rol")));           
            newUser.setCartera(Float.parseFloat(req.getParameter("cartera")));
        } catch (NullPointerException e) {
            throw new UserDataException("Error en los datos enviados");
        }
        
        if (newUser.esValido()) {
            return newUser;
        }
        
        throw new UserDataException("Error en los datos enviados");
    }
    
}
