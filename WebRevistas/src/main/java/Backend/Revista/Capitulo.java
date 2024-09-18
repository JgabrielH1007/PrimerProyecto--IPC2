/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Revista;

/**
 *
 * @author gabrielh
 */
public class Capitulo {
    private int noRevista;   
    private byte[] pdf;      
    private String nombreArchivo;

    public int getNoRevista() {
        return noRevista;
    }

    public void setNoRevista(int noRevista) {
        this.noRevista = noRevista;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    
    
}

