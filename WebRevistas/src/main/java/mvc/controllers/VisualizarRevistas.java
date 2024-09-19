/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Revista.Revista;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "ControladorBusquedaServlet", urlPatterns = {"/Controllers/Revista/search"})
public class VisualizarRevistas extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String[] tagsArray = request.getParameterValues("tags");

        // Convertir el arreglo de etiquetas en una lista
        List<String> tags = (tagsArray != null) ? Arrays.asList(tagsArray) : Arrays.asList();

        // Buscar las revistas en la base de datos
        List<Revista> revistas = searchRevistas(category, tags);

        // Establecer los resultados como atributo de solicitud
        request.setAttribute("revistas", revistas);

        // Redirigir a la página de resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("/RolUsuarios/Suscriptor/resultados_busqueda.jsp");
        dispatcher.forward(request, response);
    }

    private List<Revista> searchRevistas(String category, List<String> tags) {
        // Implementar la lógica para buscar en la base de datos según los parámetros
        DBRevistas db = new DBRevistas();
        return db.search(category, tags);
    }
}

