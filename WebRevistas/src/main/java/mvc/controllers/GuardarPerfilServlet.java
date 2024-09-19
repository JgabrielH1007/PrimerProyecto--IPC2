/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Usuario.Perfil;
import Backend.Usuario.Roles;
import static Backend.Usuario.Roles.EDITOR;
import static Backend.Usuario.Roles.SUSCRIPTOR;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "guardarController", urlPatterns ="/Controllers/guardarPerfil")
@MultipartConfig(location = "/tmp")
public class GuardarPerfilServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       HttpSession session = req.getSession(false);
       if (session == null || session.getAttribute("usuario") == null) {
           resp.sendRedirect("Login/login.jsp"); // Redirigir a la página de login si no hay sesión
           return;
       }

       Usuario usuario = (Usuario) session.getAttribute("usuario");
       String userName = usuario.getUserName();
       String temaInteres = req.getParameter("temaInteres");
       String hobbies = req.getParameter("hobbies");
       String gustos = req.getParameter("gustos");
       String descripcion = req.getParameter("descripcion");
       Part filePart = req.getPart("foto");
       byte[] foto = null;

       if (filePart != null && filePart.getSize() > 0) {
           foto = filePart.getInputStream().readAllBytes(); // Convertir la imagen en byte array
       }

       DBRevistas db = new DBRevistas();
       Perfil perfil = null;
       String message;

       // Verificar si el perfil ya existe
       if (db.existePerfil(userName)) {
           // Si existe, actualizar el perfil
           if (db.actualizarPerfil(userName, temaInteres, hobbies, gustos, descripcion, foto)) {
               message = "Perfil actualizado con éxito.";
           } else {
               message = "Error al actualizar el perfil.";
           }
       } else {
           // Si no existe, guardar el perfil por primera vez
           if (db.guardarPerfilInicialmente(userName, temaInteres, hobbies, gustos, descripcion, foto)) {
               message = "Perfil creado con éxito.";
           } else {
               message = "Error al crear el perfil.";
           }
       }

       // Obtener el perfil actualizado para mostrar en la vista
       perfil = db.obtenerPerfil(userName);
       req.setAttribute("perfil", perfil);
       req.setAttribute("message", message);

       // Redirigir a la página de perfil con el mensaje de éxito o error
                    if (usuario.getRol() == Roles.EDITOR) {
                        // Redirige al servlet de capítulos
                        resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/ver_revistas");
                    }else if (usuario.getRol() == Roles.SUSCRIPTOR){
                    // Maneja otros roles
                    
                    req.getRequestDispatcher("/RolUsuarios/Suscriptor/Suscriptor.jsp").forward(req, resp);
                    }else if (usuario.getRol() == Roles.ADMINISTRADOR){
                    // Maneja otros roles

                    resp.sendRedirect(req.getContextPath() + "/Controllers/revistas/revSinCostoServlet");
                    }else if(usuario.getRol() == Roles.ADMINISTRADOR){
                        req.getRequestDispatcher("/RolUsuarios/Suscriptor/Suscriptor.jsp").forward(req, resp);
                    }
    }
       
   }




