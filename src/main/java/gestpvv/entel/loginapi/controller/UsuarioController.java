package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.entity.*;
import gestpvv.entel.loginapi.repository.*;
import gestpvv.entel.loginapi.payload.ResUsuarioRegister;
import gestpvv.entel.loginapi.payload.ReqUsuarioRegister;
import gestpvv.entel.loginapi.payload.ResUsuarioUpdate;
import gestpvv.entel.loginapi.payload.ReqUsuarioUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PersonaClienteRepository personaClienteRepository;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private TelefonoRepository telefonoRepository;
    @Autowired
    private UContraRepository uContraRepository;
    @Autowired
    private UCorreoRepository uCorreoRepository;
    @Autowired
    private UCelularRepository uCelularRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResUsuarioRegister registrarUsuario(ReqUsuarioRegister request) {

        Optional<PersonaCliente> persona = personaClienteRepository.findbypersonaClienteDocumentoes(request.getPersonaCliente().getDoc().getDesc(), request.getPersonaCliente().getDoc().getDesc());
        gestpvv.entel.loginapi.entity.PersonaCliente per;
        gestpvv.entel.loginapi.payload.model.PersonaCliente perReq = request.getPersonaCliente();
        if (persona.isPresent()) {
            per = persona.get();
        } else {
            per = new PersonaCliente(perReq.getNombres(), perReq.getPriApe(), perReq.getSecApe(), perReq.getNacimiento(),1);
            per = personaClienteRepository.saveAndFlush(per);
            direccionRepository.save(new Direccion(perReq.getDireccion(), 1, per));
            emailRepository.save(new Email(perReq.getCorreo(), 1));
            documentoRepository.save(new Documento(perReq.getDoc().getDesc(), 1, personaClienteRepository.findTipoDocById(perReq.getDoc().getIdTipo())));
            telefonoRepository.save(new Telefono(perReq.getTel().getDesc(), 1, personaClienteRepository.findTipoTelById(perReq.getTel().getIdTipo())));

        }
        Optional<Usuario> usuario = usuarioRepository.findByIdpersonaClienteIdPersonaCliente(per.getIdPersonaCliente());
        if (usuario.isEmpty()) {
            Usuario usu = usuarioRepository.saveAndFlush(new Usuario(request.getUsuarioDesc(),"1", per, usuarioRepository.findTipPerm(request.getTipoPermiso().getId()), usuarioRepository.findTipUsuario(request.getTipoUsuario().getId())));
            String contra = request.getPersonaCliente().getDoc().getDesc();
            uContraRepository.save(new UsuarioContrasena(contra, passwordEncoder.encode(contra), 1, usu));
            uCorreoRepository.save(new UsuarioCorreo(perReq.getCorreo(), 1, usu));
            uCelularRepository.save(new UsuarioCelular(perReq.getTel().getDesc(), 1, usu));
            return new ResUsuarioRegister("EXITO", "");
        } else {
            return new ResUsuarioRegister("ERROR","ERU1 - Usuario ya existe");
        }
    }
    @PutMapping
    public ResUsuarioUpdate updateUsuario(ReqUsuarioUpdate request) {

        return null;
    }
    @PostMapping("/list")
    public List<Usuario> listarUsuario() {

        return null;
    }
}
