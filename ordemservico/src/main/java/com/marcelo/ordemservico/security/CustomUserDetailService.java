package com.marcelo.ordemservico.security;

import com.marcelo.ordemservico.model.Usuario;
import com.marcelo.ordemservico.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Credenciais inválidas");
        }else{
            return User.withUsername(usuario.getUsername())
                    .password(usuario.getPassword())
                    .authorities(usuario.getPermission()).build();
        }
    }
}
