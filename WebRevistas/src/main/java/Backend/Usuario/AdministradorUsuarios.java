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
public class AdministradorUsuarios {
    private DBRevistas db = new DBRevistas();
    private boolean exist = false;

    // Crear usuario
    public Usuario crearUsuario(HttpServletRequest req) throws UserDataException {
        Usuario newUser = validarDatos(req);
        
        try {
            if (db.usuarioExistente(newUser.getUserName())) {
                exist = true;
                throw new UserDataException("¡El nombre de usuario ya existe!");
            }
            db.guardarUsuario(newUser);
            return newUser;
        } finally {
            db.close(); // Asegurar que la conexión se cierra
        }
    }

    // Login de usuario
    public boolean validarLogin(String userName, String password) {
        return db.verificarUsuario(userName, password);
    }

    // Validación de datos para la creación de un usuario
    private Usuario validarDatos(HttpServletRequest req) throws UserDataException {
        Usuario newUser = new Usuario();
        try {
            newUser.setUserName(req.getParameter("userName"));
            newUser.setPassword(req.getParameter("password"));
            newUser.setRol(Roles.valueOf(req.getParameter("rol")));           
            newUser.setCartera(Float.parseFloat(req.getParameter("cartera")));
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new UserDataException("Error en los datos enviados");
        }

        if (newUser.esValido()) {
            return newUser;
        }
        throw new UserDataException("Error en los datos enviados");
    }
}
