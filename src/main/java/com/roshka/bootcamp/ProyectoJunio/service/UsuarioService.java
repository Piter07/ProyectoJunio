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

    /*
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }
     */

    @Override
    public Usuario guardar(UsuarioRegistroDTO registroDTO) {
        /* Trae un los datos para un Usuario desde el formulario registrar
        *  para guardarlo en la base de datos.
        * */

        // se genera tokenVerificacion
        String tokenVerificacion = DigestUtils.sha256Hex(registroDTO.getEmail()
                + new Date().toString()
                + DigestUtils.md5Hex(UUID.randomUUID().toString()));

        // por defecto el estado del usuario es pendiente
        String estado = "I";

        /* por defecto el usuario tiene rol de 'user'
        Set<Rol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setNombre("user");
        roles.add(rol);*/

        //* validaciones
        //*  1. Correo con @roshka.com
        //*  2. Confirmar contraseña igual a contraseña
        //*  3. Estado del usuario
        //*  4. Permisos del usuario

        // guardar el usuario, rol y permiso */
        Permiso permiso = new Permiso("conectarse");
        Permiso permiso1 = new Permiso("ver_albums");
        permisoRepository.saveAll(Arrays.asList(permiso, permiso1));

        Rol rol = new Rol("USER", Arrays.asList(permiso, permiso1));
        rolRepository.save(rol);

        Usuario usuario = new Usuario(registroDTO.getNombre(),
                registroDTO.getApellido(),registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()),
                estado,
                Arrays.asList(rol), tokenVerificacion);

        /* guardar el usuario y lo retorna */
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

        /* validar roles */

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
        /* Mapea cada rol en 'roles' para que Spring security reconozca como un rol en el sistema */
        //List<Permiso> flat = roles.stream().flatMap(List::stream).collect(Collectors.toList());
        Collection<SimpleGrantedAuthority> permisos = new ArrayList<>();
        roles.stream().map(role -> ( role.getPermisos().stream().map(permiso ->
                permisos.add(new SimpleGrantedAuthority(permiso.getNombre())))
                .collect(Collectors.toList())));
        return permisos;
        //return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

}
