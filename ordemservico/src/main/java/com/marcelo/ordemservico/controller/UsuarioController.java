package com.marcelo.ordemservico.controller;

import com.marcelo.ordemservico.model.Funcionario;
import com.marcelo.ordemservico.model.Usuario;
import com.marcelo.ordemservico.repository.UsuarioRepository;
import com.marcelo.ordemservico.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @CrossOrigin
    @PostMapping("/cadastrar")
    public Usuario cadastrarUsuario(@RequestBody Usuario u){

        System.out.println("Salvando usuario: "+u.getUsername());
        return this.usuarioService.cadastrarUsuario(u);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") int id){
        Optional<Usuario> usuarioData = usuarioRepository.findById(id);

        return usuarioData.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
