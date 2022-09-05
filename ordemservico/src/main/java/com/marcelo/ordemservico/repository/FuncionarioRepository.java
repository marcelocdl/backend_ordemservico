package com.marcelo.ordemservico.repository;

import com.marcelo.ordemservico.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Collection<? extends Funcionario> getByNome(String nome);

    @Query(value = "SELECT id_usuario FROM funcionario WHERE funcionario.id_funcionario = ?1", nativeQuery = true)
    int getUserByFunc(int id);
}