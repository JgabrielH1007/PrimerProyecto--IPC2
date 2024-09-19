/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

/**
 *
 * @author gabrielh
 */
import Backend.DataBase.DBRevistas;
import Backend.Revista.Capitulo;
import Backend.Revista.Comentario;
import Backend.Revista.Revista;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/Controllers/revistas/ver-contenido")
public class VerContenidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreRevista = request.getParameter("nombre");

        if (nombreRevista == null || nombreRevista.isEmpty()) {
            response.sendRedirect("path/to/errorPage.jsp");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("Login/login.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuario");
        DBRevistas db = new DBRevistas();

        boolean suscrito = db.comprobarSuscripcion(usuario.getUserName(), nombreRevista);
        Revista revista = db.obtenerRevistaPorNombre(nombreRevista);

        if (revista == null) {
            response.sendRedirect("path/to/errorPage.jsp");
            return;
        }

        if (suscrito) {
            List<Capitulo> capitulos = db.obtenerCapitulosPorRevista(nombreRevista);
            List<Comentario> comentarios = db.obtenerComentariosPorRevista(nombreRevista);
            boolean leGusta = db.verificarMeGusta(usuario.getUserName(), revista.getNombre());

            request.setAttribute("leGusta", leGusta);
            request.setAttribute("revista", revista);
            request.setAttribute("capitulos", capitulos);
            request.setAttribute("comentarios", comentarios);
            request.getRequestDispatcher("/RolUsuarios/Suscriptor/RevistaSuscrita.jsp").forward(request, response);
        } else {
            // Convertir la lista de etiquetas a una cadena separada por comas
            String etiquetasStr = revista.getEtiquetas() != null ?
                revista.getEtiquetas().stream().map(Enum::name).collect(Collectors.joining(", ")) : "No disponibles";
            request.setAttribute("etiquetasStr", etiquetasStr);
            request.setAttribute("revista", revista);
            request.getRequestDispatcher("/RolUsuarios/Suscriptor/RevistaNoSuscrita.jsp").forward(request, response);
        }
    }
}






