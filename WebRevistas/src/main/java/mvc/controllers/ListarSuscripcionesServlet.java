/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Revista.Revista;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author gabrielh
 */
@WebServlet("/Controllers/revistas/listar-suscripciones")
public class ListarSuscripcionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login/login.jsp"); // Redirigir a la página de login si no hay sesión
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        DBRevistas db = new DBRevistas();
        List<Revista> revistas = db.obtenerRevistasSuscritas(usuario.getUserName());

        request.setAttribute("revistas", revistas);
        System.out.println(revistas);
        request.getRequestDispatcher("/RolUsuarios/Suscriptor/Suscriptor.jsp").forward(request, response);
        
    }
}
