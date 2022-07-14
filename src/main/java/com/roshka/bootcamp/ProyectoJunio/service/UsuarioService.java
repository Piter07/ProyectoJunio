package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Permiso;
import com.roshka.bootcamp.ProyectoJunio.model.Rol;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.repository.PermisoRepository;
import com.roshka.bootcamp.ProyectoJunio.repository.RolRepository;
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

    @Autowired
    RolRepository rolRepository;
    @Autowired
    PermisoRepository permisoRepository;

    @Autowired
    CorreoService correoService;

    /*
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }
     */

    @Override
    public Usuario guardarDTO(UsuarioRegistroDTO registroDTO) {
        /* Trae un objeto con los datos para un Usuario desde el formulario registrar
        *  para guardarlo en la base de datos.
        * */

        //* validaciones
        //*  1. Correo con @roshka.com
        //*  2. Confirmar contraseña igual a contraseña
        //*  3. Estado del usuario
        //*  4. Permisos del usuario

        // se genera tokenVerificacion
        String tokenVerificacion = DigestUtils.sha256Hex(registroDTO.getEmail()
                + new Date().toString()
                + DigestUtils.md5Hex(UUID.randomUUID().toString()));

        // por defecto el estado del usuario es pendiente
        String estado = "I";

        /* guardar el usuario, rol y permiso */
        Permiso permiso = new Permiso("conectarse");
        Permiso permiso1 = new Permiso("crear albums");
        permisoRepository.saveAll(Arrays.asList(permiso, permiso1));

        Rol rol = new Rol("ADMIN", Arrays.asList(permiso, permiso1));
        rolRepository.save(rol);

        /* enviar mensaje al correo registrado en el formulario para activar la cuenta */
        String text = "http://localhost:8080/verificacion?token=" + tokenVerificacion +
                "&correo=" + registroDTO.getEmail();

        correoService.sendEmail(registroDTO.getEmail(),
                "Correo de verificación Roshkagram",
                text);

        /* genera un objeto del tipo Usuario para guardarlo en la
        *  base de datos
        * */
        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                estado, Arrays.asList(rol), tokenVerificacion);

        /* guardar el usuario y lo retorna */
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        /* Trae un objeto del tipo Usuario y lo guardar en la base de
        *  datos
        * */
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Del formulario Login trae el username, luego busca en la base de datos para agregar como usuario
        *  en el Spring Security con su respectos roles(permisos).
        * */
        Usuario usuario = usuarioRepository.findByEmail(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario o password invalidos");
        }

        /* validar que la cuenta esté 'A' [activa] para loguearse */
        if(usuario.getEstado().equals("I")) {
            throw new UsernameNotFoundException("El usuario no está activo");
        }

        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        /* retorna una lista con todos los usuarios */
        return usuarioRepository.findAll();
    }

    public Usuario existeUsuario(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        /* Mapea cada rol del usuario (lista de listas) a una lista para que Spring security reconozca como
         * un rol (permiso) en el sistema.
         * */
        //List<Permiso> flat = roles.stream().flatMap(List::stream).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> permisos = new ArrayList<>();

        roles.stream().map(role -> ( role.getPermisos().stream().map(permiso ->
                permisos.add(new SimpleGrantedAuthority(permiso.getNombre())))
                .collect(Collectors.toList())));

        /* ver los permisos del usuario logueado */
        for (SimpleGrantedAuthority permiso : permisos) {
            System.out.println(permiso.getAuthority());
        }

        return permisos;
        //return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

}
