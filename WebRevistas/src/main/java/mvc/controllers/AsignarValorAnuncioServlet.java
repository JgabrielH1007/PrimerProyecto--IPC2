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
@WebServlet(name = "AsignarValorAnuncioServlet", urlPatterns = "/Controllers/Usuario/AsignarValorAnuncioServlet")
public class AsignarValorAnuncioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String tiempo = request.getParameter("tiempo");
        double precio = Double.parseDouble(request.getParameter("precio"));

        // Crear una instancia de la clase de acceso a datos
        DBRevistas db = new DBRevistas();
        boolean resultado = db.asignarValorAnuncio(tipo, tiempo, precio);

        String mensaje = resultado ? "Valor del anuncio asignado con éxito." : "Error al asignar el valor del anuncio.";
        request.setAttribute("mensaje", mensaje);
        List<String> revistas = db.obtenerRevistasSinCosto();
        request.setAttribute("revista", revistas);
        request.getRequestDispatcher("/RolUsuarios/Administrador/Administrador.jsp").forward(request, response);
    }
}

