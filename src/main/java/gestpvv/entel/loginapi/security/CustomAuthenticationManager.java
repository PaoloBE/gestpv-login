package gestpvv.entel.loginapi.security;

import gestpvv.entel.loginapi.entity.*;
import gestpvv.entel.loginapi.payload.model.Document;
import gestpvv.entel.loginapi.repository.PersonaClienteRepository;
import gestpvv.entel.loginapi.repository.UserRepository;
import gestpvv.entel.loginapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CustomAuthenticationManager implements AuthenticationManager {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonaClienteRepository personaClienteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Document docLog = (Document) authentication.getPrincipal();
        CustomToken token = new CustomToken(authentication.getPrincipal(), authentication.getCredentials());
        Optional<PersonaCliente> persona = personaClienteRepository.findbypersonaClienteDocumentoes(docLog.getTipo(), docLog.getNumero());
        if (persona.isPresent()) {
            Optional<Usuario> usuario = usuarioRepository.findByIdpersonaClienteIdPersonaCliente(persona.get().getIdPersonaCliente());
            if (usuario.isPresent()) {
                String contrasena = usuarioRepository.findPasswordHash(usuario.get().getIdUsuario());
                if (passwordEncoder.matches(authentication.getCredentials().toString(), contrasena == null ? "" : contrasena)) {
                    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                    grantedAuthorityList.add(new SimpleGrantedAuthority(usuario.get().getIdtipoPermiso().getTipoPermisoDesc()));
                    token = new CustomToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorityList);
                    token.setName(persona.get().getPersonaNombres() + " " + persona.get().getPersonaPrimerApellido() + " " + persona.get().getPersonaSegundoApellido());
                    return token;
                } else {
                    token.setDetails("E1 - Contraseña Incorrecta");
                    return token;
                }
            } else {
                token.setDetails("E2 - No existe Usuario");
                return token;
            }

        } else {
            token.setDetails("E2 - No existe Usuario");
            return token;
        }
    }
}
