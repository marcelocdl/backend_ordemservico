package com.marcelo.ordemservico.service;

import com.marcelo.ordemservico.model.Funcionario;
import com.marcelo.ordemservico.model.Tarefa;
import com.marcelo.ordemservico.repository.FuncionarioRepository;
import com.marcelo.ordemservico.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private final TarefaRepository tarefaRepository;

    private FuncionarioRepository funcionarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, FuncionarioRepository funcionarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Tarefa cadastrarTarefa(Tarefa t){
        System.out.println("tarefa id -> "+t.getFuncionario());
        Optional<Funcionario> funcionarioData = funcionarioRepository.findById(t.getFuncionario().getId_funcionario());



        if (funcionarioData.isPresent()) {
            Funcionario funcionario = funcionarioData.get();
            System.out.println("Funcionario -> "+funcionario.getNome());
            t.setFuncionario(funcionario);
        }

        return this.tarefaRepository.save(t);
    }

}
