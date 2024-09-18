/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DataBase;

import Backend.Revista.Capitulo;
import Backend.Revista.Etiquetas;
import Backend.Revista.Revista;
import Backend.Usuario.AdministradorPassword;
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
import java.util.List;
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


    
    public void guardarPerfil(){
        
    }
    
    public void guardarRevista(Revista revista) {
    String sql = "INSERT INTO revista (nombre, descripcion, categoria, etiquetas, fecha, habilitar_comentarios, habilitar_megusta, autor) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        // Configurar los valores en el PreparedStatement según los datos de la revista
        System.out.println("holaaa");
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
            close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario, lanzar una nueva o loggear el error.
        }
    }


    
    public void guardarMeGusta(){
        
    }
    
    public void guardarComentario(){
        
    }
    
    public void publicarCapitulo(){
        
    }
    
    public void realizarPago(){
        
    }
    
    public void comprarAnuncio(){
        
    }
    
    public void extraerPerfil(){
        
    }
    
    public void extraerAnuncio(){
        
    }
    
    public void extraerComentario(){
        
    }
    
    public void extreaerRevista(){
        
    }
    
    
}
