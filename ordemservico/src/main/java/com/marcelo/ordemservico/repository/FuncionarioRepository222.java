package com.marcelo.ordemservico.repository;

import com.marcelo.ordemservico.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface FuncionarioRepository222 extends JpaRepository<Funcionario, Integer> {
    Collection<? extends Funcionario> getByNome(String nome);

}