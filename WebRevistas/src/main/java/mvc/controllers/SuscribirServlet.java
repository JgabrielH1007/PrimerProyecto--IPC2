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
@WebServlet("/Controllers/revistas/suscribir")
public class SuscribirServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login/login.jsp"); // Redirige si no hay un usuario en sesión
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String nombreRevista = request.getParameter("nombreRevista");
        String fechaSuscripcion = request.getParameter("fecha-suscripcion");
        System.out.println(fechaSuscripcion);
        // Validar que el nombre de la revista y la fecha no estén vacíos
        if (nombreRevista == null || nombreRevista.isEmpty() || fechaSuscripcion == null || fechaSuscripcion.isEmpty()) {
            response.sendRedirect("path/to/errorPage.jsp"); // Redirige si falta información
            return;
        }

        DBRevistas db = new DBRevistas();

        // Verificar si el usuario ya está suscrito a la revista
        boolean yaSuscrito = db.comprobarSuscripcion(usuario.getUserName(), nombreRevista);

        if (!yaSuscrito) {
            // Si no está suscrito, se crea la suscripción con la fecha
            db.suscribirRevista(usuario.getUserName(), nombreRevista, fechaSuscripcion);

            // Redirigir nuevamente a la página de la revista
            response.sendRedirect(request.getContextPath() + "/Controllers/revistas/ver-contenido?nombre=" + nombreRevista);
        } else {
            // Si ya está suscrito, puedes mostrar un mensaje de error o advertencia
            response.sendRedirect(request.getContextPath() + "/Controllers/revistas/ver-contenido?nombre=" + nombreRevista + "&error=Ya estas suscrito");
        }
    }
}

