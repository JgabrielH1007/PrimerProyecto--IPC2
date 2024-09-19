/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Usuario.Perfil;
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
@WebServlet("/Controllers/mostrar-perfilAutor")
public class PerfilAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro 'autor' desde la URL
        String autor = request.getParameter("autor");

        // Verificar si el parámetro autor es válido
        if (autor != null && !autor.isEmpty()) {
            // Crear instancia de la base de datos o servicio de datos
            DBRevistas db = new DBRevistas();
            
            // Obtener el perfil del autor desde la base de datos o servicio
            Perfil perfil = db.obtenerPerfil(autor);

            // Comprobar si se obtuvo un perfil válido
            if (perfil != null) {
                // Pasar el perfil al JSP
                request.setAttribute("perfil", perfil);
                // Redirigir al JSP para mostrar el perfil del autor
                request.getRequestDispatcher("/includes/PerfilAutor.jsp").forward(request, response);
            } else {
                // Si no se encuentra el perfil, mostrar un mensaje de error
                request.setAttribute("error", "No se pudo encontrar el perfil del autor.");
                request.getRequestDispatcher("/includes/PerfilAutor.jsp").forward(request, response);
            }
        } else {
            // Si el parámetro autor no es válido, mostrar un error
            request.setAttribute("error", "El nombre del autor no es válido.");
            request.getRequestDispatcher("/includes/PerfilAutor.jsp").forward(request, response);
        }
    }
}



