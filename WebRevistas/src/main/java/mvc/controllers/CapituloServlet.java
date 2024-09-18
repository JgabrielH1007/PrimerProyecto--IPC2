/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Revista.Capitulo;
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

        // Obtener el nombre de la revista y el archivo PDF del formulario
        String nombreRevista = req.getParameter("revista");
        Part filePart = req.getPart("pdf"); // Obtiene el archivo PDF enviado en el formulario

        if (nombreRevista != null && filePart != null) {
            try (InputStream pdfInputStream = filePart.getInputStream()) {
                dbRevistas.guardarCapitulo(nombreRevista, pdfInputStream);
                req.setAttribute("mensaje", "Capítulo subido exitosamente.");
                
            } catch (SQLException e) {
                req.setAttribute("error", "Error al guardar el capítulo: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            req.setAttribute("error", "Faltan datos para guardar el capítulo.");
        }

        // Redirigir a la página de éxito o error
        req.getRequestDispatcher("/RolUsuarios/Editor/Editor.jsp").forward(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBRevistas dbRevistas = new DBRevistas();
        // Obtener la sesión actual
        HttpSession session = req.getSession(false);  // false significa que no creará una nueva sesión si no existe
        Usuario usuario = (Usuario) session.getAttribute("usuario");  // Extraer el objeto Usuario de la sesión

       

        String userName = usuario.getUserName();
        System.out.println(userName);
        List<String> revistas = dbRevistas.obtenerListaDeRevistas(userName);  // Método para obtener la lista de revistas

        req.setAttribute("revistas", revistas);  // Establece el atributo en la solicitud

        // Redirige al JSP donde se debe mostrar el formulario
        req.getRequestDispatcher("/RolUsuarios/Editor/Editor.jsp").forward(req, resp);
    }
}


