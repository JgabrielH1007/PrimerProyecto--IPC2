/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.controllers;

import Backend.DataBase.DBRevistas;
import Backend.Usuario.Perfil;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author gabrielh
 */
@WebServlet("/Controllers/editar-perfil")
public class EditarPerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        DBRevistas dbPerfil = new DBRevistas();
        Perfil perfil = dbPerfil.obtenerPerfil(usuario.getUserName());

        request.setAttribute("perfil", perfil);
        request.getRequestDispatcher("/includes/EditarPerfil.jsp").forward(request, response);
    }
}

