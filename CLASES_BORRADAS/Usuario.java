package com.TABOO.Backend;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@Entity
@EnableJpaRepositories
public class Usuario {
    @Id
    private Long id;
    private String usuario;
    private String correo;
    private String password;
    private String nTelefono;
    private List<Publicacion> publicaciones;

    public Usuario(Long id, String usuario, String correo, String password, String nTelefono, Object o) {
        this.id = id;
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.nTelefono = nTelefono;
        this.publicaciones = new ArrayList<>();
    }

    public Usuario(Object name, String username, String email, String encode) {
    }

    public Usuario(Object o, String nombrePorDefecto, String username, String email, String encode) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getnTelefono() {
        return nTelefono;
    }

    public void setnTelefono(String nTelefono) {
        this.nTelefono = nTelefono;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void agregarPublicacion(Publicacion publicacion) {
        this.publicaciones.add(publicacion);
    }

    public void eliminarPublicacion(Publicacion publicacion) {
        this.publicaciones.remove(publicacion);
    }
}
