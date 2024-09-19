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
@WebServlet("/Controllers/revistas/comentar")
public class ComentarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario de la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Obtener los parámetros del formulario
        String nombreRevista = request.getParameter("nombreRevista");
        String contenidoComentario = request.getParameter("comentario");

        // Validar que no estén vacíos
        if (nombreRevista == null || nombreRevista.isEmpty() || contenidoComentario == null || contenidoComentario.isEmpty()) {
            request.setAttribute("error", "El comentario no puede estar vacío.");
            request.getRequestDispatcher("/RolUsuarios/Suscriptor/RevistaSuscrita.jsp").forward(request, response);
            return;
        }

        // Guardar el comentario en la base de datos
        DBRevistas db = new DBRevistas();
        boolean comentarioGuardado = db.guardarComentario(usuario.getUserName(), nombreRevista, contenidoComentario);

        if (comentarioGuardado) {
            response.sendRedirect(request.getContextPath() + "/Controllers/revistas/ver-contenido?nombre=" + nombreRevista);
        } else {
            request.setAttribute("error", "No se pudo guardar el comentario. Inténtalo de nuevo.");
            request.getRequestDispatcher("/RolUsuarios/Suscriptor/RevistaSuscrita.jsp").forward(request, response);
        }
    }
}

