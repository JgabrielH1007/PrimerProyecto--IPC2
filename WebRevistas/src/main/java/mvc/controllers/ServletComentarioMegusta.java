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
import java.util.List;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "MegComController", urlPatterns = {"/Controllers/Revista/habilitar"})
public class ServletComentarioMegusta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBRevistas db = new DBRevistas();
        try {
            String revista = req.getParameter("revista");
            boolean comentarios = req.getParameter("comentarios") != null;
            boolean meGusta = req.getParameter("me_gusta") != null;
            boolean suscripciones = req.getParameter("suscripciones") != null;

            // Actualizar habilitación de funciones en la base de datos
            db.actualizarHabilitacionFunciones(revista, meGusta, comentarios, suscripciones);

            // Obtener la sesión actual
            HttpSession session = req.getSession(false);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String userName = usuario.getUserName();

            // Obtener la lista actualizada de revistas
            List<String> revistas = db.obtenerListaDeRevistas(userName);
            req.setAttribute("revistas", revistas);

            // Establecer el mensaje de éxito o error
            req.setAttribute("mensajeHabilitarFunciones", "Funciones actualizadas exitosamente.");
        } catch (Exception e) {
            req.setAttribute("errorHabilitarFunciones", "Error al actualizar funciones: " + e.getMessage());
            e.printStackTrace();
        }

        // Redirigir a la página JSP con los datos actualizados
        req.getRequestDispatcher("/RolUsuarios/Editor/Editor.jsp").forward(req, resp);
    }
}

        
    
