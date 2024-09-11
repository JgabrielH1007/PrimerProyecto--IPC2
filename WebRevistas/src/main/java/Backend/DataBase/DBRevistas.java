/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.DataBase;

import Backend.Usuario.AdministradorPassword;
import Backend.Usuario.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según sea necesario, lanzar una nueva o loggear el error.
        }
    }
    
    public void verificarUsuario(){
        
    }
    
    
    public boolean revistaExistente(){
        
        return false;
        
    }
    
    public void guardarPerfil(){
        
    }
    
    public void guardarRevista(){
        
    }
    
    public void verificarLogin(String UserName, String password){
        
    }
    
    public void asignarEtiqueta(){
        
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
