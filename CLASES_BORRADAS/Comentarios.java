package com.TABOO.Backend;

import java.util.ArrayList;
import java.util.List;

public class Comentarios {
    //Atributos
    private List<String> comentarios;

    //Constructor
    public Comentarios() {
        this.comentarios = new ArrayList<>();
    }

    //Métodos
    //Agregar Comentario
    public void agregarComentario(String comentario) {
        this.comentarios.add(comentario);
    }

    //Eliminar Comentario
    public void eliminarComentario(int indice) {
        this.comentarios.remove(indice);
    }

    //Obtener Comentario
    public List<String> getComentarios() {
        return this.comentarios;
    }
}
