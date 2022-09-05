package com.marcelo.ordemservico.repository;

import com.marcelo.ordemservico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT *  FROM usuario WHERE username = ?", nativeQuery = true)
    Usuario findByUsername(String username);

    @Query(value = "SELECT * FROM usuario WHERE id_usuario = ?1", nativeQuery = true)
    Usuario findUserFunc(int id);

}