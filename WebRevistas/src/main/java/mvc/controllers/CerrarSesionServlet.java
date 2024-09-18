/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "ControladorSesionCerradaServlet", urlPatterns = {"/Controllers/Usuario/cerrarSesion-servlet"})
public class CerrarSesionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual, si existe.
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Invalida la sesión para cerrar sesión.
            System.out.println("Se cerro sesion");
            DBRevistas db = (DBRevistas) session.getAttribute("dbConnection");
            if (db != null) {
                db.close();
                session.removeAttribute("dbConnection");
            }
            session.invalidate();
        }

        // Redirigir al login.jsp
        response.sendRedirect(request.getContextPath() + "/Login/Login.jsp");
    }
}
