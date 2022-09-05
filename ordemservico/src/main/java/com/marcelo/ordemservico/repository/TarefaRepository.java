package com.marcelo.ordemservico.repository;

import com.marcelo.ordemservico.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query(value = "SELECT * FROM tarefa t WHERE t.id_funcionario = ?1", nativeQuery = true)
    Optional<Tarefa> getTarefaByFunc(int id);

    @Query(value = "SELECT t.id_tarefa, t.nome, t.descricao, t.id_funcionario, t.localizacao FROM tarefa t " +
            "JOIN funcionario func ON t.id_funcionario = func.id_funcionario " +
            " JOIN usuario us ON func.id_usuario = us.id_usuario " +
            " WHERE us.id_usuario = ?1", nativeQuery = true)
    Collection<? extends Tarefa> getTodasTarefasByUser(int id);

    Collection<? extends Tarefa> getByNome(String nome);

}