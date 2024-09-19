/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author gabrielh
 */
@WebServlet("/Controllers/revistas/descargar-pdf")
public class DescargarPdfServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreRevista = request.getParameter("nombre");
        int noCapitulo = Integer.parseInt(request.getParameter("noCapitulo"));

        if (nombreRevista == null || nombreRevista.isEmpty()) {
            response.sendRedirect("path/to/errorPage.jsp"); // Redirigir a una página de error si falta el nombre
            return;
        }

        DBRevistas db = new DBRevistas();
        byte[] pdfBytes = db.obtenerPdfPorNombre(nombreRevista, noCapitulo);

        if (pdfBytes != null) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=capitulo_" + noCapitulo + ".pdf");
            response.setContentLength(pdfBytes.length);
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                outputStream.write(pdfBytes);
            }
        } else {
            response.sendRedirect("path/to/errorPage.jsp"); // Redirigir a una página de error si el PDF no se encuentra
        }
    }
}

