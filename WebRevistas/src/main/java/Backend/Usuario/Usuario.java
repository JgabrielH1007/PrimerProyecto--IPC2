/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Usuario;

/**
 *
 * @author gabrielh
 */
public class Usuario {
    
    private String userName;
    private String password;
    private String rol;
    private float cartera;

    public Usuario() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public float getCartera() {
        return cartera;
    }

    public void setCartera(float cartera) {
        this.cartera = cartera;
    }
    
    public boolean esValido(){
        return userName != null && password != null && rol != null && cartera > 0;
    }
    
    
    
}
