package com.marcelo.ordemservico.model;

import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;

    private String username;
    private String password;
    private String permission;

    @Transient
    private String token;

    public Usuario() {
    }

    public Usuario(String username, String password, String permission) {
        this.username = username;
        this.password = password;
        this.permission = permission;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password) && Objects.equals(permission, usuario.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, username, password, permission);
    }
}
