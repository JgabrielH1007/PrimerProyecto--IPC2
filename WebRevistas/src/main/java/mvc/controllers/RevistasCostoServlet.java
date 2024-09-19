/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author gabrielh
 */
@WebServlet(name = "RevistasCostoServlet", urlPatterns = "/Controllers/revistas/revSinCostoServlet")
public class RevistasCostoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear una instancia de la clase de acceso a datos
        DBRevistas db = new DBRevistas();

        // Obtener la lista actualizada de revistas sin costo
        List<String> revistasSinCosto = db.obtenerRevistasSinCosto();
        request.setAttribute("revistasSinCosto", revistasSinCosto);

        // Obtener la lista de todas las revistas
        List<String> todasLasRevistas = db.obtenerTodasLasRevistas();
        request.setAttribute("todasLasRevistas", todasLasRevistas);

        // Redirigir al JSP
        request.getRequestDispatcher("/RolUsuarios/Administrador/Administrador.jsp").forward(request, response);
    }

}

