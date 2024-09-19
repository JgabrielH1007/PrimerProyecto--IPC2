/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.Revista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gabrielh
 */
public class Revista {
    private String nombre;
    
    private String descripcion;
    private Categorias categoria;
    private List<Etiquetas> etiquetas;
    private String autor;
    private float costoDia;
    private LocalDate fecha;
    private int cantidadMegusta;
    private boolean megusta;
    private boolean comentario;
    private boolean suscripciones;
    private List<Capitulo> capitulos;

    public boolean isSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(boolean suscripciones) {
        this.suscripciones = suscripciones;
    }
    
    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public List<Etiquetas> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiquetas> etiquetas) {
        this.etiquetas = etiquetas;
    }

    



    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getCostoDia() {
        return costoDia;
    }

    public void setCostoDia(float costoDia) {
        this.costoDia = costoDia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidadMegusta() {
        return cantidadMegusta;
    }

    public void setCantidadMegusta(int cantidadMegusta) {
        this.cantidadMegusta = cantidadMegusta;
    }

    public boolean isMegusta() {
        return megusta;
    }

    public void setMegusta(boolean megusta) {
        this.megusta = megusta;
    }

    public boolean isComentario() {
        return comentario;
    }

    public void setComentario(boolean comentario) {
        this.comentario = comentario;
    }
 
}
