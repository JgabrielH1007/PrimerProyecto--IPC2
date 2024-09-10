/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Backend.Exceptions.UserDataException;
import Backend.Usuario.CreadorUsuario;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "ControladorServlet", urlPatterns = {"/Controllers/Usuario/usuario-servlet"})
public class ControladorServlet extends HttpServlet{
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreadorUsuario creadorUsuario = new CreadorUsuario();
        try {
            Usuario newUser = creadorUsuario.crearUsuario(req);

            // para compartir un modelo y usar redirect para mostrar una vista jsp
            /*req.getSession().setAttribute("solicitudCreada", solicitudCreada);
            resp.sendRedirect(req.getContextPath() + "/solicitudes/crear-solicitud.jsp?codigo=" + solicitudCreada.getCodigo());*/

            // para compartir un modelo y usar forward para mostrar una vista jsp
            System.out.println("hola");
            req.setAttribute("Usuario creado", newUser);
            req.getRequestDispatcher("/Login/LoginNewUser.jsp")
                 .forward(req, resp);
        } catch (UserDataException e) {
            e.printStackTrace();
        }

    }
}

