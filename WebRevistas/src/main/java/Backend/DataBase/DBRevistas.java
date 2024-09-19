/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DataBase;

import Backend.Revista.Capitulo;
import Backend.Revista.Categorias;
import Backend.Revista.Comentario;
import Backend.Revista.Etiquetas;
import Backend.Revista.Revista;
import Backend.Usuario.AdministradorPassword;
import Backend.Usuario.Perfil;
import Backend.Usuario.Roles;
import Backend.Usuario.Usuario;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author gabrielh
 */
public class DBRevistas {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/sistema_revistas";
    private static final String USER = "root";
    private static final String PASSWORD = "1007";
    
    private Connection connection;
    private AdministradorPassword admin = new AdministradorPassword();


    public DBRevistas()  {
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }
    
    public void close() {
        if (connection != null) {
            try {
                connection.close(); // Cerrar la conexión a la base de datos
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public List<Revista> obtenerRevistas() {
       List<Revista> revistas = new ArrayList<>();
       String query = "SELECT * FROM revista";

       try (PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {

           while (rs.next()) {
               Revista revista = new Revista();
               revista.setNombre(rs.getString("nombre"));
               revista.setDescripcion(rs.getString("descripcion"));
               revista.setCategoria(Categorias.valueOf(rs.getString("categoria")));

               // Convertir la cadena de etiquetas a una lista de Etiquetas
               String etiquetasStr = rs.getString("etiquetas");
               List<Etiquetas> etiquetas = new ArrayList<>();
               if (etiquetasStr != null && !etiquetasStr.isEmpty()) {
                   String[] etiquetasArray = etiquetasStr.split(",");
                   for (String etiqueta : etiquetasArray) {
                       etiquetas.add(Etiquetas.valueOf(etiqueta.trim()));
                   }
               }
               revista.setEtiquetas(etiquetas);

               revista.setAutor(rs.getString("autor"));
               revista.setCantidadMegusta(rs.getInt("cantidad_megusta"));

               // Obtener capítulos asociados
               List<Capitulo> capitulos = obtenerCapitulosPorRevista(revista.getNombre());
               revista.setCapitulos(capitulos);

               revistas.add(revista);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return revistas;
    }


    public List<Capitulo> obtenerCapitulosPorRevista(String nombreRevista) {
        List<Capitulo> capitulos = new ArrayList<>();
        String query = "SELECT * FROM capitulo_revista WHERE nombre_revista = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, nombreRevista);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Capitulo capitulo = new Capitulo();
                    capitulo.setNumeroCapitulo(rs.getInt("no_capitulo"));
                    capitulo.setNombreRevista(rs.getString("nombre_revista"));
                    capitulo.setPdf(rs.getBytes("pdf"));
                    capitulo.setNombreArchivo("capitulo_" + capitulo.getNumeroCapitulo() + ".pdf"); // Ajusta si es necesario
                    capitulos.add(capitulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capitulos;
    }

    public byte[] obtenerPdfPorNombre(String nombreRevista, int noCapitulo) {
        byte[] pdf = null;
        String query = "SELECT pdf FROM capitulo_revista WHERE nombre_revista = ? AND no_capitulo = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
                
            
            statement.setString(1, nombreRevista);
            statement.setInt(2, noCapitulo);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    pdf = rs.getBytes("pdf");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pdf;
    }
    
    public List<Comentario> obtenerComentariosPorRevista(String nombreRevista) {
    List<Comentario> comentarios = new ArrayList<>();
    String query = "SELECT * FROM comentario WHERE nombre_revista = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, nombreRevista);
        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setUserName(rs.getString("user_name"));
                comentario.setNombreRevista(rs.getString("nombre_revista"));
                comentario.setContenido(rs.getString("contenido"));
                comentarios.add(comentario);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return comentarios;
}

    
    public boolean usuarioExistente(String userName) {
            String sql = "SELECT COUNT(*) FROM usuario WHERE user_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Si el conteo es mayor que 0, el usuario existe
                        return resultSet.getInt(1) > 0;
                    }
                }
               close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }  
    
    public boolean revistaExiste(String nombreRevista) {
            String sql = "SELECT COUNT(*) FROM revista WHERE nombre = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombreRevista);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Si el conteo es mayor que 0, el usuario existe
                        return resultSet.getInt(1) > 0;
                    }
                }
               close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
    }  
    
    public boolean verificarUsuario(String userName, String password) {
        String sql = "SELECT password FROM usuario WHERE BINARY user_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Obtiene la contraseña hasheada almacenada en la base de datos
                    String hashedPassword = resultSet.getString("password");

                    // Compara la contraseña ingresada con la hasheada
                    return admin.checkPassword(password, hashedPassword);
                }
                close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;      
    }

    public void guardarUsuario(Usuario user){
        user.setPassword(admin.hashPassword(user.getPassword()));
        String sql = "INSERT INTO usuario (user_name, password, rol, cartera) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2,  user.getPassword());
            statement.setString(3, user.getRol().name());
            statement.setFloat(4, user.getCartera());

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Usuario guardado exitosamente.");
            }
            close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario, lanzar una nueva o loggear el error.
        }
    }

    public Usuario obtenerUsuarioPorNombre(String userName) {
        String sql = "SELECT user_name, password, rol, cartera FROM usuario WHERE user_name = ?";
        Usuario user = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            // Si se encuentra el usuario, se crea un objeto Usuario con los datos obtenidos
            if (resultSet.next()) {
                user = new Usuario();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRol(Roles.valueOf(resultSet.getString("rol")));  // Asumiendo que tienes una enumeración 'Roles'
                user.setCartera(resultSet.getFloat("cartera"));
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar excepción según sea necesario
        }

        return user;  // Si no se encuentra el usuario, devuelve null
    }

    public List<String> obtenerListaDeRevistas(String userName) {
        List<String> revistas = new ArrayList<>();
        String sql = "SELECT nombre FROM revista WHERE autor = ?";  // Filtra las revistas por el autor

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);  // Establece el parámetro de la consulta
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    revistas.add(resultSet.getString("nombre"));
                }
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario
        }

        return revistas;
    }

    public void guardarRevista(Revista revista) {
        String sql = "INSERT INTO revista (nombre, descripcion, categoria, etiquetas, fecha, habilitar_comentarios, habilitar_megusta, autor) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Configurar los valores en el PreparedStatement según los datos de la revista
            stmt.setString(1, revista.getNombre());
            stmt.setString(2, revista.getDescripcion());
            stmt.setString(3, revista.getCategoria().name()); // Asumiendo que Categorias es un enum
            stmt.setString(4, String.join(",", revista.getEtiquetas().stream()
                                    .map(Etiquetas::name)  // Convertir cada etiqueta en su nombre
                                    .collect(Collectors.toList())));
            stmt.setDate(5, java.sql.Date.valueOf(revista.getFecha())); // Convertir java.util.Date a 
            stmt.setBoolean(6, revista.isComentario()); // booleano para comentarios
            stmt.setBoolean(7, revista.isMegusta()); // booleano para "me gusta"
            stmt.setString(8, revista.getAutor());

            // Ejecutar el insert
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Revista guardada exitosamente.");
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar la revista en la base de datos.");
        }
    }

    public void guardarCapitulo(String nombreRevista, InputStream pdfInputStream) throws SQLException {
        String sql = "INSERT INTO capitulo_revista (nombre_revista, pdf) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombreRevista);
            statement.setBlob(2, pdfInputStream);

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                System.out.println("Capítulo guardado exitosamente.");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario, lanzar una nueva o loggear el error.
        }
    }

    public void actualizarHabilitacionFunciones(String revista, boolean meGusta, boolean comentarios, boolean suscripciones) {
        String sql = "UPDATE revista SET habilitar_megusta = ?, habilitar_comentarios = ?, habilitar_suscripciones = ? WHERE nombre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Asignar los valores booleanos a los parámetros de la consulta
            preparedStatement.setBoolean(1, meGusta);
            preparedStatement.setBoolean(2, comentarios);
            preparedStatement.setBoolean(3, suscripciones);
            preparedStatement.setString(4, revista); // Asignar el nombre de la revista al cuarto parámetro

            // Ejecutar la actualización
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Revista actualizada exitosamente.");
            } else {
                System.out.println("No se encontró la revista con el nombre proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario
        }
    
    }
    
    public List<Revista> search(String category, List<String> tags) {
        List<Revista> revistas = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM revista WHERE 1=1");

     
        if (category != null && !category.isEmpty()) {
            query.append(" AND categoria = ?");
        }
        if (tags != null && !tags.isEmpty()) {
            // Para simplificar, asumimos que la base de datos tiene un formato de texto para etiquetas
            // y buscamos si alguna etiqueta coincide. La lógica puede variar según el esquema de base de datos.
            query.append(" AND etiquetas LIKE ?");
        }

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            int index = 1;

            
            if (category != null && !category.isEmpty()) {
                statement.setString(index++, category);
            }
            if (tags != null && !tags.isEmpty()) {
                // Construir el patrón de búsqueda para etiquetas
                String tagsPattern = String.join(",", tags);
                statement.setString(index++, "%" + tagsPattern + "%");
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Revista revista = new Revista();
                    revista.setNombre(rs.getString("nombre"));
                    revista.setDescripcion(rs.getString("descripcion"));
                    revista.setCategoria(Categorias.valueOf(rs.getString("categoria")));

                    // Convertir la cadena de etiquetas a una lista de Etiquetas
                    String etiquetasStr = rs.getString("etiquetas");
                    List<Etiquetas> etiquetas = new ArrayList<>();
                    if (etiquetasStr != null && !etiquetasStr.isEmpty()) {
                        String[] etiquetasArray = etiquetasStr.split(",");
                        for (String etiqueta : etiquetasArray) {
                            etiquetas.add(Etiquetas.valueOf(etiqueta.trim()));
                        }
                    }
                    revista.setEtiquetas(etiquetas);

                    revista.setAutor(rs.getString("autor"));
                    revista.setCantidadMegusta(rs.getInt("cantidad_megusta"));

                    // Obtener capítulos asociados
                    List<Capitulo> capitulos = obtenerCapitulosPorRevista(revista.getNombre());
                    revista.setCapitulos(capitulos);

                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }

    public boolean comprobarSuscripcion(String userName, String nombreRevista) {
        String sql = "SELECT COUNT(*) FROM suscribir WHERE user_name = ? AND nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, nombreRevista);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void suscribirRevista(String userName, String nombreRevista, String fechaSuscripcion) {
        String query = "INSERT INTO suscribir (Fecha, nombre_revista, user_name) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fechaSuscripcion);
            statement.setString(2, nombreRevista);
            statement.setString(3, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

   public Revista obtenerRevistaPorNombre(String nombreRevista) {
    Revista revista = null;
    String sql = "SELECT * FROM revista WHERE nombre = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, nombreRevista);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                revista = new Revista();
                revista.setNombre(resultSet.getString("nombre"));
                revista.setDescripcion(resultSet.getString("descripcion"));
                revista.setCategoria(Categorias.valueOf(resultSet.getString("categoria")));
                revista.setAutor(resultSet.getString("autor"));

                // Leer y asignar etiquetas
                String etiquetasStr = resultSet.getString("etiquetas");
                if (etiquetasStr != null && !etiquetasStr.isEmpty()) {
                    List<Etiquetas> etiquetas = new ArrayList<>();
                    String[] etiquetasArray = etiquetasStr.split(",");
                    for (String etiqueta : etiquetasArray) {
                        etiquetas.add(Etiquetas.valueOf(etiqueta.trim()));
                    }
                    revista.setEtiquetas(etiquetas);
                }

                // Asignar otros atributos
                //revista.setCostoDia(resultSet.getBigDecimal("costo_dia"));
                revista.setFecha(resultSet.getDate("fecha").toLocalDate());
                revista.setMegusta(resultSet.getBoolean("habilitar_megusta"));
                revista.setComentario(resultSet.getBoolean("habilitar_comentarios"));
                revista.setSuscripciones(resultSet.getBoolean("habilitar_suscripciones"));
                revista.setCantidadMegusta(resultSet.getInt("cantidad_megusta"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return revista;
}

    
    public boolean guardarComentario(String userName, String nombreRevista, String contenido) {
        String query = "INSERT INTO comentario (user_name, nombre_revista, contenido) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, nombreRevista);
            statement.setString(3, contenido);

            int rowsInserted = statement.executeUpdate();
            close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarMeGusta(String userName, String nombreRevista) {
        String query = "SELECT COUNT(*) FROM me_gusta WHERE user_name = ? AND nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, nombreRevista);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Retorna true si ya le dio "Me Gusta"
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void agregarMeGusta(String userName, String nombreRevista) {
        String query = "INSERT INTO me_gusta (user_name, nombre_revista) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, nombreRevista);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void quitarMeGusta(String userName, String nombreRevista) {
        String query = "DELETE FROM me_gusta WHERE user_name = ? AND nombre_revista = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, nombreRevista);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCantidadMeGusta(String nombreRevista, int cantidad) {
        String query = "UPDATE revista SET cantidad_megusta = COALESCE(cantidad_megusta, 0) + ? WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            System.out.println("Conexión establecida y preparada la consulta.");

            statement.setInt(1, cantidad);
            statement.setString(2, nombreRevista);

            System.out.println("Ejecutando la actualización para la revista: " + nombreRevista);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No se actualizó la cantidad de Me Gusta. Verifica el nombre de la revista.");
            } else {
                System.out.println("Cantidad de Me Gusta actualizada correctamente.");
            }
            close();
        } catch (SQLException e) {
            System.out.println("Error al intentar actualizar la cantidad de Me Gusta.");
            e.printStackTrace();
        }
    }
    
    public void suscribirRevista(String userName, String nombreRevista) {
        String query = "INSERT INTO suscribir (Fecha, nombre_revista, user_name) VALUES (CURDATE(), ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreRevista);
            statement.setString(2, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Revista> obtenerRevistasSuscritas(String username) {
        List<Revista> revistas = new ArrayList<>();
        String sql = "SELECT * FROM revista WHERE nombre IN (SELECT nombre_revista FROM suscribir WHERE user_name = ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revista revista = new Revista();

                    // Datos básicos de la revista
                    revista.setNombre(resultSet.getString("nombre"));
                    revista.setDescripcion(resultSet.getString("descripcion"));
                    revista.setFecha(resultSet.getDate("fecha").toLocalDate());
                    revista.setCategoria(Categorias.valueOf(resultSet.getString("categoria")));
                    revista.setAutor(resultSet.getString("autor"));

                    // Procesar etiquetas
                    String etiquetasStr = resultSet.getString("etiquetas");
                    if (etiquetasStr != null && !etiquetasStr.isEmpty()) {
                        List<Etiquetas> etiquetas = new ArrayList<>();
                        String[] etiquetasArray = etiquetasStr.split(",");
                        for (String etiqueta : etiquetasArray) {
                            etiquetas.add(Etiquetas.valueOf(etiqueta.trim())); // Convertir a enum Etiquetas
                        }
                        revista.setEtiquetas(etiquetas);
                    }

                    // Configuración de flags y contadores
                    revista.setCantidadMegusta(resultSet.getInt("cantidad_megusta"));
                    revista.setMegusta(resultSet.getBoolean("habilitar_megusta"));
                    revista.setComentario(resultSet.getBoolean("habilitar_comentarios"));
                    revista.setSuscripciones(resultSet.getBoolean("habilitar_suscripciones"));

                    // Añadir a la lista
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistas;
    }
    
    public Perfil obtenerPerfil(String userName) {
        Perfil perfil = null;
        String sql = "SELECT * FROM perfil WHERE user_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    perfil = new Perfil();
                    perfil.setUserName(resultSet.getString("user_name"));
                    perfil.setTemasInteres(resultSet.getString("tema_interes"));
                    perfil.setHobbies(resultSet.getString("hobbies"));
                    perfil.setGustos(resultSet.getString("gustos"));
                    perfil.setDescripcion(resultSet.getString("descripcion"));

                    // Obtener foto en formato byte[]
                    byte[] fotoBytes = resultSet.getBytes("foto");
                    perfil.setFoto(fotoBytes);
                }
            }
                        close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfil;
    }
    
    public boolean actualizarPerfil(String userName, String temaInteres, String hobbies, String gustos, String descripcion, byte[] foto) {
        String sql = "UPDATE perfil SET tema_interes = ?, hobbies = ?, gustos = ?, descripcion = ?, foto = ? WHERE user_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, temaInteres);
            statement.setString(2, hobbies);
            statement.setString(3, gustos);
            statement.setString(4, descripcion);
            statement.setBytes(5, foto);
            statement.setString(6, userName);

            int rowsUpdated = statement.executeUpdate();
                        close();

            return rowsUpdated > 0; // Si se actualizó alguna fila, devuelve true
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Si falla o no se actualiza ninguna fila
    }


    public boolean guardarPerfilInicialmente(String userName, String temaInteres, String hobbies, String gustos, String descripcion, byte[] foto) {
        String sql = "INSERT INTO perfil (user_name, tema_interes, hobbies, gustos, descripcion, foto) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(userName + temaInteres + hobbies + gustos + descripcion);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, temaInteres);
            statement.setString(3, hobbies);
            statement.setString(4, gustos);
            statement.setString(5, descripcion);
            statement.setBytes(6, foto);

            int rowsInserted = statement.executeUpdate();
            close();
            return rowsInserted > 0; // Si se insertó el perfil, devuelve true
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Si falla o no se inserta ninguna fila
    }
    
    public boolean existePerfil(String userName) {
        String sql = "SELECT COUNT(*) FROM perfil WHERE user_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                                

                    return count > 0; // Si el conteo es mayor a 0, el perfil existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Si falla la consulta o no existe el perfil
    }
    
        public boolean asignarValorAnuncio(String tipo, String tiempo, double precio) {
        String sql = "INSERT INTO valores_anuncios (tipo, tiempo, precio) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE precio = VALUES(precio)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            stmt.setString(2, tiempo);
            stmt.setDouble(3, precio);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para asignar un costo a una revista
    public boolean asignarCostoRevista(String revista, double costo) {
        String sql = "UPDATE revista SET costo_dia = ? WHERE nombre = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, costo);
            stmt.setString(2, revista);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener revistas sin costo asignado
    public List<String> obtenerRevistasSinCosto() {
        List<String> revistas = new ArrayList<>();
        String sql = "SELECT nombre FROM revista WHERE costo_dia IS NULL";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                revistas.add(rs.getString("nombre"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revistas;
    }
    
    public List<String> obtenerTodasLasRevistas() {
        List<String> revistas = new ArrayList<>();
        String query = "SELECT nombre FROM revista"; // Suponiendo que la tabla se llama 'revistas' y tiene una columna 'nombre'
        
        try (PreparedStatement stmt = connection.prepareStatement(query); // Obtener conexión a la base de datos
              // Preparar la consulta SQL
             ResultSet rs = stmt.executeQuery()) { // Ejecutar la consulta y obtener los resultados

            // Recorrer los resultados y agregar cada nombre de revista a la lista
            while (rs.next()) {
                revistas.add(rs.getString("nombre")); // Suponiendo que la columna es 'nombre'
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return revistas; // Devolver la lista de revistas
    }



    
}





    
    

