/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.Exceptions.UserDataException;
import Backend.Revista.AdministradorRevista;
import Backend.Revista.Capitulo;
import Backend.Revista.Categorias;
import Backend.Revista.Etiquetas;
import Backend.Revista.Revista;
import Backend.Usuario.Roles;
import Backend.Usuario.Usuario;
import com.google.protobuf.TextFormat.ParseException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "ControladorRevistaServlet", urlPatterns = {"/Controllers/Revista/revista-servlet"})
public class RevistaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Procesa la publicación de la revista
            publicarRevista(request, response);
        } catch (java.text.ParseException ex) {
            Logger.getLogger(RevistaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void publicarRevista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, java.text.ParseException {
        System.out.println("Servlet llamado");  // Verificar si el método es alcanzado

        // Verifica los valores de los parámetros
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String fechaPublicacion = request.getParameter("fecha-publicacion");
        String comentarios = request.getParameter("comentarios");
        String meGusta = request.getParameter("me_gusta");

        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Categoría: " + categoria);
        System.out.println("Fecha de publicación: " + fechaPublicacion);
        System.out.println("Comentarios: " + comentarios);
        System.out.println("Me gusta: " + meGusta);

        // Crear el AdministradorRevista y publicar la revista
        AdministradorRevista admin = new AdministradorRevista();
        admin.publicarRevista(request);
    }
}


