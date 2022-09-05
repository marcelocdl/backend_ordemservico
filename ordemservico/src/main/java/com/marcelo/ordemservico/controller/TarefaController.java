package com.marcelo.ordemservico.controller;

import com.marcelo.ordemservico.model.Tarefa;
import com.marcelo.ordemservico.repository.TarefaRepository;
import com.marcelo.ordemservico.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    private final TarefaRepository tarefaRepository;

    private TarefaService tarefaService;

    public TarefaController(TarefaRepository tarefaRepository, TarefaService tarefaService) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaService = tarefaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Tarefa>> getTarefas(@RequestParam(required = false) String nome){
        try {
            List<Tarefa> tarefas = new ArrayList<>();

            if(nome == null) {
                tarefas.addAll(tarefaRepository.findAll());
            }else{
                tarefas.addAll(tarefaRepository.getByNome(nome));
            }
            if(tarefas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(tarefas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefaById(@PathVariable("id") int id){
        Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

        return tarefaData.map(tarefa -> new ResponseEntity<>(tarefa, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa t){
        System.out.println(t.getFuncionario());

        Tarefa tarefa = tarefaService.cadastrarTarefa(t);

        return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Tarefa> editarTarefa(@PathVariable("id") int id, @RequestBody Tarefa t ) {
        Optional<Tarefa> tarefaData = tarefaRepository.findById(id);

        if (tarefaData.isPresent()) {
            Tarefa tarefa = tarefaData.get();
            tarefa.setNome(t.getNome());
            tarefa.setDescricao(t.getDescricao());
            tarefa.setLocalizacao(t.getLocalizacao());
            tarefa.setFuncionario(t.getFuncionario());

            return new ResponseEntity<>(tarefaRepository.save(tarefa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Tarefa> deletarTarefa(@PathVariable("id") int id) {

        try {
            tarefaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tarefa_funcionario/{id}")
    public ResponseEntity<List<Tarefa>> getMinhasTarefas(@PathVariable("id") int id){
        try {
            List<Tarefa> tarefas = new ArrayList<>();

            tarefas.addAll(tarefaRepository.getTodasTarefasByUser(id));

            if(tarefas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(tarefas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
