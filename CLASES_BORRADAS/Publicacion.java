package com.TABOO.Backend;

import jakarta.persistence.*;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "contenido")
    private String contenido;

    //Constructor vacío
    public Publicacion() {}

    //Constructor con parámetros
    public Publicacion(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    //toString
    @Override
    public String toString() {
        return "Publicacion [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + "]";
    }
}
