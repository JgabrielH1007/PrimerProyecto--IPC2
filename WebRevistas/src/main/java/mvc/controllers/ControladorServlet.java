/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.Exceptions.UserDataException;
import Backend.Usuario.AdministradorUsuarios;
import Backend.Usuario.Roles;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    
                if (newUser.getRol() == Roles.EDITOR) {
                    // Establece el usuario en la sesión y redirige al servlet de capítulos
                    HttpSession session = req.getSession();
                    session.setAttribute("usuario", newUser);
                    resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/ver_revistas");
                } else if (newUser.getRol() == Roles.SUSCRIPTOR){
                    // Maneja otros roles
                    HttpSession session = req.getSession();
                    session.setAttribute("usuario", newUser);
                    req.getRequestDispatcher("/RolUsuarios/Suscriptor/Suscriptor.jsp").forward(req, resp);
                } else if (newUser.getRol() == Roles.ADMINISTRADOR){
                    // Maneja otros roles
                    HttpSession session = req.getSession();
                    session.setAttribute("usuario", newUser);
                    resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/revSinCostoServlet");
                }
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

                    // Obtener el objeto Usuario desde la base de datos (puedes tener un método para obtener el usuario por nombre)
                    Usuario usuario = adminUsuario.obtenerUsuario(userName);
                    // Guardar el usuario en la sesión
                    HttpSession session = req.getSession();
                    session.setAttribute("usuario", usuario);

                    if (usuario.getRol() == Roles.EDITOR) {
                        // Redirige al servlet de capítulos
                        resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/ver_revistas");
                    }else if (usuario.getRol() == Roles.SUSCRIPTOR){
                    // Maneja otros roles
                    
                    req.getRequestDispatcher("/RolUsuarios/Suscriptor/Suscriptor.jsp").forward(req, resp);
                    }else if (usuario.getRol() == Roles.ADMINISTRADOR){
                    // Maneja otros roles

                    resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/revSinCostoServlet");
                }
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








