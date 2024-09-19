/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Revista;

import Backend.DataBase.DBRevistas;
import Backend.Exceptions.UserDataException;
import Backend.Usuario.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabrielh
 */
public class AdministradorRevista {
    private DBRevistas db = new DBRevistas();
       
    public void publicarRevista(HttpServletRequest request)
            throws ServletException, IOException, java.text.ParseException, UserDataException {
        
        
        // Obtener la sesión actual
        HttpSession session = request.getSession(false);  // false significa que no creará una nueva sesión si no existe
        Usuario usuario = (Usuario) session.getAttribute("usuario");  // Extraer el objeto Usuario de la sesión

       

        String userName = usuario.getUserName();  // Obtener el nombre de usuario de la sesión

        System.out.println(userName);


        List<Etiquetas> listaEtiquetas = new ArrayList<>();
        String[] etiquetasSeleccionadas = request.getParameterValues("etiquetas");

        if (etiquetasSeleccionadas != null) {
            for (String etiqueta : etiquetasSeleccionadas) {
                try {
                    listaEtiquetas.add(Etiquetas.valueOf(etiqueta));
                } catch (IllegalArgumentException e) {
                    System.err.println("Etiqueta inválida: " + etiqueta);
                }
            }
        }

        // Crear la revista con los datos obtenidos
        Revista revista = new Revista();
        revista.setNombre(request.getParameter("nombre"));
        System.out.println(revista.getNombre());
        revista.setDescripcion(request.getParameter("descripcion"));
        revista.setCategoria(Categorias.valueOf(request.getParameter("categoria")));
        revista.setEtiquetas(listaEtiquetas);
        revista.setFecha(LocalDate.parse(request.getParameter("fecha-publicacion")));
        revista.setComentario(request.getParameter("comentarios") != null);
        revista.setMegusta(request.getParameter("me_gusta") != null);

        // Establecer el autor de la revista usando el nombre de usuario desde la sesión
        revista.setAutor(userName);
        verificarRevistaExistente(revista.getNombre(), db);
        // Guardar la revista en la base de datos
        db.guardarRevista(revista);  // Suponiendo que tienes este método en DBRevistas

    }
    
    public void verificarRevistaExistente(String nombreRevista, DBRevistas db) throws UserDataException{
        if(db.revistaExiste(nombreRevista)){
            throw new UserDataException("¡El nombre de usuario ya existe!");
        }
    }
}
