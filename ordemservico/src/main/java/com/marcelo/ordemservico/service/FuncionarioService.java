package com.marcelo.ordemservico.service;

import com.marcelo.ordemservico.model.Funcionario;
import com.marcelo.ordemservico.model.Tarefa;
import com.marcelo.ordemservico.model.Usuario;
import com.marcelo.ordemservico.repository.FuncionarioRepository;
import com.marcelo.ordemservico.repository.TarefaRepository;
import com.marcelo.ordemservico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    boolean retorno;

    @Autowired
    private final FuncionarioRepository funcionarioRepository;
    private TarefaRepository tarefaRepository;
    private UsuarioRepository usuarioRepository;


    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean deletarFuncionario(int id){
        Optional<Tarefa> t = tarefaRepository.getTarefaByFunc(id);

        int id_u = funcionarioRepository.getUserByFunc(id);

        if(t.isPresent()){
            Tarefa tarefa = t.get();
            System.out.println(tarefa.getId_tarefa()+" NOME -> "+tarefa.getNome());
            tarefaRepository.deleteById(tarefa.getId_tarefa());

            funcionarioRepository.deleteById(id);
            return true;

        }
        else if(!t.isPresent() && id_u == 0){
            funcionarioRepository.deleteById(id);
            return true;
        }
        else if(id_u > 0){
            funcionarioRepository.deleteById(id);
            usuarioRepository.deleteById(id_u);
            return true;
        }else{
            return false;
        }
    }
}
