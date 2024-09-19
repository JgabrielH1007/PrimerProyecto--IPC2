/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Usuario.Usuario;
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
@WebServlet("/Controllers/revistas/me-gusta")
public class MeGustaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombreRevista = request.getParameter("nombreRevista");

        // Validar que el nombre de la revista no esté vacío
        if (nombreRevista == null || nombreRevista.isEmpty()) {
            response.sendRedirect("path/to/errorPage.jsp"); // Redirige si falta el nombre de la revista
            return;
        }

        DBRevistas db = new DBRevistas();
        
        // Verificar si el usuario ya ha dado "Me Gusta" a la revista
        boolean leGusta = db.verificarMeGusta(usuario.getUserName(), nombreRevista);

        if (leGusta) {
            // Si ya le ha dado "Me Gusta", lo quita (elimina el registro de la tabla 'me_gusta')
            db.quitarMeGusta(usuario.getUserName(), nombreRevista);
            // Restar 1 a la cantidad de 'Me Gusta' de la revista
            db.actualizarCantidadMeGusta(nombreRevista, -1);
        } else {
            // Si no le ha dado "Me Gusta", agregarlo
            System.out.println("hola");
            db.agregarMeGusta(usuario.getUserName(), nombreRevista);
            // Sumar 1 a la cantidad de 'Me Gusta' de la revista
            db.actualizarCantidadMeGusta(nombreRevista, 1);
        }

        // Redirigir nuevamente a la página de la revista
        response.sendRedirect(request.getContextPath() + "/Controllers/revistas/ver-contenido?nombre=" + nombreRevista);
    }
}

