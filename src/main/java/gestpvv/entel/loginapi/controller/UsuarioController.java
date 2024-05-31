package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.entity.*;
import gestpvv.entel.loginapi.entity.UsuarioCelular;
import gestpvv.entel.loginapi.entity.UsuarioCorreo;
import gestpvv.entel.loginapi.payload.DTO.PersonaDTO;
import gestpvv.entel.loginapi.payload.DTO.SubTipoDTO;
import gestpvv.entel.loginapi.payload.DTO.TipoDTO;
import gestpvv.entel.loginapi.payload.DTO.UsuarioDTO;
import gestpvv.entel.loginapi.payload.model.*;
import gestpvv.entel.loginapi.repository.*;
import gestpvv.entel.loginapi.payload.ResUsuarioRegister;
import gestpvv.entel.loginapi.payload.DTO.UsuarioOperationDTO;
import gestpvv.entel.loginapi.payload.ResUsuarioUpdate;
import gestpvv.entel.loginapi.payload.ReqUsuarioUpdate;
import gestpvv.entel.loginapi.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import gestpvv.entel.loginapi.enums.TipoUsuarioName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PersonaClienteRepository personaRep;
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private DocumentoRepository documentoRepository;
    @Autowired
    private TelefonoRepository telefonoRepository;
    @Autowired
    private UContraRepository uContraRep;
    @Autowired
    private UCorreoRepository uCorreoRep;
    @Autowired
    private UCelularRepository uCelulrRep;
    @Autowired
    private GestorRepository gestorRepository;
    @Autowired
    private UbigeoRepository ubigeoRepository;
    @Autowired
    private BancoRepository bancoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public ResUsuarioRegister registrarUsuario(@RequestBody UsuarioOperationDTO request) throws Exception {
        boolean gest = request.getTipo().equalsIgnoreCase("GEST");
        boolean kam = request.getTipo().equalsIgnoreCase("KAM");
        boolean pdv = request.getTipo().equalsIgnoreCase("PDV");
        String nameU = TipoUsuarioName.valueOf(request.getTipoUsuario().getDesc()).getCode();
        Optional<String> nameL = usuarioRepository.findLastOfTipoUsuario(request.getTipoUsuario().getDesc());
        String nameF = "";
        nameF = nameL.map(s -> nameU + String.format("%04d", Integer.parseInt(s.replaceAll("[^\\d.]", "")) + 1)).orElseGet(() -> nameU + "0001");
        request.setUsuarioDesc(nameF);
        System.out.println(JSONUtil.toJSON(request));
        System.out.println(gest+" - "+kam+" - "+pdv);
        Optional<PersonaCliente> persona = personaRep.findbypersonaClienteDocumentoes(request.getPersonaCliente().getDoc().getDesc(), request.getPersonaCliente().getDoc().getDesc());
        PersonaCliente per;
        PersonaClienteDto perReq = request.getPersonaCliente();
        if (persona.isPresent()) {
            per = persona.get();
        } else {
            per = new PersonaCliente(perReq.getNombres(), perReq.getPriApe(), perReq.getSecApe(), perReq.getNacimiento(),1);
            if (gest) {
                per.setTipoGestorPersona(gestorRepository.findByIdTipoGestor(perReq.getGest().getIdTipo()));
            }
            if (pdv) {
                per.setPersonaRazonSocial(perReq.getRazSoc());
            }
            per = personaRep.saveAndFlush(per);
            if (kam) {
                direccionRepository.save(new Direccion(ubigeoRepository.findMainDep(perReq.getDireccion().getUbigeo().getConcat()), per));
            }
            if (pdv) {
                DireccionReq dirReq = perReq.getDireccion();
                String concat = dirReq.getUbigeo().getConcat();
                direccionRepository.save(new Direccion(dirReq.getDesc(),dirReq.getRef(),
                        "0",dirReq.getLat(),dirReq.getLon(),
                        dirReq.getZona(),dirReq.getCity(),
                        per, ubigeoRepository.findByDepProDist(concat.split("-")[0],concat.split("-")[1],concat.split("-")[2])));
                documentoRepository.save(new Documento(perReq.getDocE().getDesc(), 1, personaRep.findTipoDocByDesc(perReq.getDocE().getTipo()), per));
            }
            emailRepository.save(new Email(perReq.getCorreo(), 1, per));
            documentoRepository.save(new Documento(perReq.getDoc().getDesc(), 1, personaRep.findTipoDocByDesc(perReq.getDoc().getTipo()), per));
            telefonoRepository.save(new Telefono(perReq.getTel(), 1, personaRep.findTipoTelByDesc("CELULAR TRABAJO"), per));
            bancoRepository.save(new Banco(perReq.getBanco().getNombre(), perReq.getBanco().getCuenta(), perReq.getBanco().getCuentaCCI(), perReq.getBanco().getTipoCu(), per));
        }
        Optional<Usuario> usuario = usuarioRepository.findByIdpersonaClienteIdPersonaCliente(per.getIdPersonaCliente());
        if (usuario.isEmpty()) {
            String permiso = request.getTipoUsuario().getDesc().equalsIgnoreCase("ADMIN") ? "TOTAL" : "LECTURA" ;
            Usuario usu = usuarioRepository.saveAndFlush(new Usuario(request.getUsuarioDesc(),"1", per, usuarioRepository.findTipPermDesc(permiso), usuarioRepository.findTipUsuario(request.getTipoUsuario().getId())));
            String contra = request.getPersonaCliente().getDoc().getDesc();
            uContraRep.save(new UsuarioContrasena(contra, passwordEncoder.encode(contra), 1, usu));
            uCorreoRep.save(new UsuarioCorreo(perReq.getCorreo(), 1, usu));
            uCelulrRep.save(new UsuarioCelular(perReq.getTel(), 1, usu));
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
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (!usuarios.isEmpty()) {
            List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                UsuarioDTO usuarioR = new UsuarioDTO(usuario.getIdUsuario(), usuario.getUsuarioDesc(), usuario.getUsuarioEstado());
                usuarioR.setTipoUsuario(new TipoDTO(usuario.getIdtipoUsuario().getIdTipoUsuario(), usuario.getIdtipoUsuario().getTipoUsuarioDesc()));
                UsuarioCelular usuarioCelular = uCelulrRep.findCelAct(usuario.getIdUsuario());
                usuarioR.setCelular(new TipoDTO(usuarioCelular.getIdUsuarioCelular(), usuarioCelular.getCelularNumeroDesc()));
                UsuarioCorreo usuarioCorreo = uCorreoRep.findCorreoAct(usuario.getIdUsuario());
                usuarioR.setCorreo(new TipoDTO(usuarioCorreo.getIdUsuarioCorreo(),usuarioCorreo.getCorreoDesc()));
                PersonaCliente persona = usuario.getIdpersonaCliente();
                PersonaDTO personaDTO = new PersonaDTO(persona.getIdPersonaCliente(),
                        persona.getPersonaNombres(),
                        persona.getPersonaPrimerApellido(),
                        persona.getPersonaSegundoApellido(),
                        persona.getPersonaNacimiento());
                Documento documento = personaRep.findDocByPersonaIdAct(persona.getIdPersonaCliente());
                personaDTO.setDocumento(new SubTipoDTO(documento.getIdDocumento(), documento.getDocumentoDesc(), new TipoDTO(documento.getDocumentoTipoDocumento().getIdTipoDocumento(), documento.getDocumentoTipoDocumento().getTipoDocumentoDesc())));
                usuarioR.setPersona(personaDTO);
                usuarioDTOS.add(usuarioR);
            }

            return usuarioDTOS; 
        } else {
            return new ArrayList<UsuarioDTO>();
        }
    }

    @GetMapping("/list/{id}")
    public UsuarioOperationDTO listaUnUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            TipoUsuario type = usuario.get().getIdtipoUsuario();
            PersonaCliente personaCliente = usuario.get().getIdpersonaCliente();
            String typeResp = type.getTipoUsuarioDesc().equalsIgnoreCase("Gestor") ? "GEST" : type.getTipoUsuarioDesc().equalsIgnoreCase("KAM") ? "KAM" : type.getTipoUsuarioDesc().contains("PDV") ? "PDV" : "NORMAL";
            UsuarioOperationDTO usuarioUp = new UsuarioOperationDTO(typeResp, usuario.get().getUsuarioDesc(), new TipoUsuarioDTO(type.getIdTipoUsuario(), type.getTipoUsuarioDesc()));
            PersonaClienteDto personaDto = new PersonaClienteDto(personaCliente.getPersonaNombres(), personaCliente.getPersonaPrimerApellido(), personaCliente.getPersonaSegundoApellido(), personaCliente.getPersonaNacimiento());
            personaDto.setDoc(new Cont(personaRep.findDocByPersonaIdAct(personaCliente.getIdPersonaCliente())));
            personaDto.setTel(personaRep.findTelDescByPersonaIdAct(personaCliente.getIdPersonaCliente()));
            personaDto.setCorreo(personaRep.findEmailDescByPersonaIdAct(personaCliente.getIdPersonaCliente()));

            if (typeResp.equalsIgnoreCase("GEST")){
                personaDto.setGest(new Gest(gestorRepository.findGestorbyId(personaCliente.getIdPersonaCliente())));
            }
            if (typeResp.equalsIgnoreCase("KAM")){
                personaDto.setDireccion(new DireccionReq(direccionRepository.findKAMDep(personaCliente.getIdPersonaCliente())));
            }
            if (typeResp.equalsIgnoreCase("PDV")) {
                personaDto.setRazSoc(personaCliente.getPersonaRazonSocial());
                personaDto.setDocE(new Cont(personaRep.findDocEmpByPersonaIdAct(personaCliente.getIdPersonaCliente())));
                personaDto.setDireccion(new DireccionReq(direccionRepository.findDirPDV(personaCliente.getIdPersonaCliente())));
                personaDto.setBanco(new BancoReq(personaRep.findBancById(personaCliente.getIdPersonaCliente())));
            }
            usuarioUp.setPersonaCliente(personaDto);

            return usuarioUp;
        } else {
            return new UsuarioOperationDTO();
        }
    }

    @GetMapping("/update/state/{id}/{estado}")
    public String updateState(@PathVariable Integer id, @PathVariable String estado) {
        String cambio = estado.equalsIgnoreCase("1") ? "0" : "1";
        usuarioRepository.updateUserState(id,cambio);
        return "exito";
    }
}
