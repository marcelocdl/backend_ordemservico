package com.marcelo.ordemservico.controller;

import com.marcelo.ordemservico.model.Funcionario;

import com.marcelo.ordemservico.model.Usuario;
import com.marcelo.ordemservico.repository.FuncionarioRepository;
import com.marcelo.ordemservico.repository.UsuarioRepository;
import com.marcelo.ordemservico.service.FuncionarioService;
import com.marcelo.ordemservico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private final FuncionarioRepository funcionarioRepository;
    private FuncionarioService funcionarioService;

    private UsuarioRepository usuarioRepository;


    public FuncionarioController(FuncionarioRepository funcionarioRepository, FuncionarioService funcionarioService, UsuarioRepository usuarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioService = funcionarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<Funcionario>> getFuncionarios(@RequestParam(required = false) String nome){
        try {
            List<Funcionario> funcionarios = new ArrayList<>();

            if(nome == null) {
                funcionarios.addAll(funcionarioRepository.findAll());
            }else{
                funcionarios.addAll(funcionarioRepository.getByNome(nome));
            }
            if(funcionarios.isEmpty()){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(funcionarios, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id") int id){
        Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);

        return funcionarioData.map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario func){

        Funcionario funcionario = new Funcionario();
        Usuario u = new Usuario();

        u.setUsername(func.getUsuario().getUsername());
        u.setPassword(new BCryptPasswordEncoder().encode(func.getUsuario().getPassword()));
        u.setPermission("USER");

        func.setUsuario(u);
        
        Usuario user = usuarioRepository.save(u);

        if(user!=null) {
             funcionario = funcionarioRepository.save(new Funcionario(
                    func.getNome(),
                    func.getEndereco(),
                    func.getNumCpf(),
                    func.getTelefone(),
                    func.getUsuario()
            ));
        }
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Funcionario> editarFuncionario(@PathVariable("id") int id, @RequestBody Funcionario func ) {
        System.out.println("Vem da requi -> "+func);

        Optional<Funcionario> funcionarioData = funcionarioRepository.findById(id);

        if (funcionarioData.isPresent()) {
            Funcionario funcionario = funcionarioData.get();
            funcionario.setNome(func.getNome());
            funcionario.setEndereco(func.getEndereco());
            funcionario.setNumCpf(func.getNumCpf());
            funcionario.setTelefone(func.getTelefone());

            int id_u = func.getUsuario().getIdUsuario();
            Usuario u = usuarioRepository.findUserFunc(id_u);
            u.setUsername(func.getUsuario().getUsername());
            u.setPassword(
                    new BCryptPasswordEncoder().encode(func.getUsuario().getPassword())
            );

            funcionario.setUsuario(u);

            //return new ResponseEntity<>(funcionarioRepository.save(funcionario), HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Funcionario> deletarFuncionario(@PathVariable("id") int id) {

        try {
            if(funcionarioService.deletarFuncionario(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
