/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Backend.Exceptions.UserDataException;
import Backend.Usuario.AdministradorUsuarios;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielh
 */
    
    
@WebServlet(name = "ControladorServlet", urlPatterns = {"/Controllers/Usuario/usuario-servlet"})
public class ControladorServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdministradorUsuarios adminUsuario = new AdministradorUsuarios();
        String action = req.getParameter("action");  // "login" o "registro"
        
        if ("registro".equals(action)) {
            try {
                // Intentar crear un nuevo usuario
                Usuario newUser = adminUsuario.crearUsuario(req); 
                req.setAttribute("mensaje", "Usuario creado exitosamente: " + newUser.getUserName());
                req.getRequestDispatcher("/Login/LoginNewUser.jsp").forward(req, resp);
            } catch (UserDataException e) {
                // Usuario ya existe, manejar la excepción
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Login/LoginNewUser.jsp").forward(req, resp);
            }
        } else if ("login".equals(action)) {
            // Realizar login
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            try {
                if (adminUsuario.validarLogin(userName, password)) {
                    // Login exitoso
                    req.setAttribute("mensaje", "Login exitoso");
                    //req.getRequestDispatcher("/welcome.jsp").forward(req, resp);  // Redirigir al home o página de bienvenida
                } else {
                    // Contraseña incorrecta o usuario no encontrado
                    throw new UserDataException("Usuario o contraseña incorrectos.");
                }
            } catch (UserDataException e) {
                // Login fallido, manejar la excepción
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/Login/Login.jsp").forward(req, resp);
            }
        }
    }
}





