/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Revista.Capitulo;
import Backend.Usuario.Roles;
import Backend.Usuario.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabrielh
 */    
@WebServlet(name = "ControladorRevistas", urlPatterns = {"/Controllers/revistas/ver_revistas"})
@MultipartConfig(location = "/tmp")
public class CapituloServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBRevistas dbRevistas = new DBRevistas();
        HttpSession session = req.getSession(false);  // false evita crear una nueva sesión si no existe
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Obtener el nombre de la revista y el archivo PDF del formulario
        String nombreRevista = req.getParameter("revista");
        Part filePart = req.getPart("pdf");  // Obtiene el archivo PDF enviado en el formulario

        if (nombreRevista != null && filePart != null) {
            try (InputStream pdfInputStream = filePart.getInputStream()) {
                dbRevistas.guardarCapitulo(nombreRevista, pdfInputStream);
                req.setAttribute("mensajeSubirCapitulo", "Capítulo subido exitosamente.");

            } catch (SQLException e) {
                req.setAttribute("errorSubirCapitulo", "Error al guardar el capítulo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            req.setAttribute("errorSubirCapitulo", "Faltan datos para guardar el capítulo.");
        }

        // Asegurarse de que la lista de revistas siempre se establezca
        if (usuario != null && usuario.getRol() == Roles.EDITOR) {
            List<String> revistas = dbRevistas.obtenerListaDeRevistas(usuario.getUserName());
            req.setAttribute("revistas", revistas);  // Agregar la lista de revistas a la solicitud
        }

        // Redirigir al JSP del editor (con o sin mensaje)
        req.getRequestDispatcher("/RolUsuarios/Editor/Editor.jsp").forward(req, resp);
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBRevistas dbRevistas = new DBRevistas();
        HttpSession session = req.getSession(false);  // false evita crear una nueva sesión si no existe
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario != null && usuario.getRol() == Roles.EDITOR) {
            List<String> revistas = dbRevistas.obtenerListaDeRevistas(usuario.getUserName());
            req.setAttribute("revistas", revistas);  // Establece la lista de revistas en la solicitud
        }

        // Redirige al JSP donde se debe mostrar el formulario
        req.getRequestDispatcher("/RolUsuarios/Editor/Editor.jsp").forward(req, resp);
    }

}


