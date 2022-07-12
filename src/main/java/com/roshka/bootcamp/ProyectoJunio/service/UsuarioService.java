package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Rol;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.repository.UsuarioRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        // se encripta la constraseña
        String clave = passwordEncoder.encode(registroDTO.getClave());
        // se genera tokenVerificacion
        String tokenVerificacion = DigestUtils.sha256Hex(registroDTO.getCorreo()
                + new Date().toString()
                + DigestUtils.md5Hex(UUID.randomUUID().toString()));
        // por defecto el estado del usuario es pendiente
        String estado = "I";
        // por defecto el usuario tiene rol de 'user'
        Set<Rol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setNombre("user");
        roles.add(rol);

        /* validaciones
        *  1. Correo con @roshka.com
        *  2. Confirmar contraseña igual a contraseña
        *  3. Estado del usuario
        *  4. Rol del usuario
        * */

        Usuario usuario = new Usuario();

        /* inicializar el usuario */
        usuario.setApellido(registroDTO.getApellido());
        usuario.setNombre(registroDTO.getNombre());
        usuario.setCorreo(registroDTO.getCorreo());
        usuario.setClave(clave);
        usuario.setTokenVerificacion(tokenVerificacion);
        usuario.setEstado(estado);
        // cargar los roles
        usuario.setRoles(roles);

        /* guardar el usuario y lo retorna */
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password invalidos");
        }

        return new User(usuario.getCorreo(), usuario.getClave(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        /* Mapea cada rol en 'roles' para que String security reconozca como un rol en el sistema */
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    public boolean existeUsuario(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
