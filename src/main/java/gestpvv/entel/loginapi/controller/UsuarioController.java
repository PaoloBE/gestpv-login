package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.entity.*;
import gestpvv.entel.loginapi.entity.UsuarioCelular;
import gestpvv.entel.loginapi.entity.UsuarioCorreo;
import gestpvv.entel.loginapi.payload.DTO.PersonaDTO;
import gestpvv.entel.loginapi.payload.DTO.SubTipoDTO;
import gestpvv.entel.loginapi.payload.DTO.TipoDTO;
import gestpvv.entel.loginapi.payload.DTO.UsuarioDTO;
import gestpvv.entel.loginapi.payload.ReqListUsuario;
import gestpvv.entel.loginapi.payload.model.*;
import gestpvv.entel.loginapi.repository.*;
import gestpvv.entel.loginapi.payload.RespUsuarioOperation;
import gestpvv.entel.loginapi.payload.DTO.UsuarioOperationDTO;
import gestpvv.entel.loginapi.payload.ResUsuarioUpdate;
import gestpvv.entel.loginapi.repository.dtos.UsuarioListJPA;
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
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public RespUsuarioOperation registrarUsuario(@RequestBody UsuarioOperationDTO request) throws Exception {
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
        }
        Optional<Usuario> usuario = usuarioRepository.findByIdpersonaClienteIdPersonaCliente(per.getIdPersonaCliente());
        if (usuario.isEmpty()) {
            String permiso = request.getTipoUsuario().getDesc().equalsIgnoreCase("ADMIN") ? "TOTAL" : "LECTURA" ;
            Usuario usu = usuarioRepository.saveAndFlush(new Usuario(request.getUsuarioDesc(),"1", request.getPadres(), per, usuarioRepository.findTipPermDesc(permiso), usuarioRepository.findTipUsuario(request.getTipoUsuario().getId())));
            String contra = request.getPersonaCliente().getDoc().getDesc();
            uContraRep.save(new UsuarioContrasena(contra, passwordEncoder.encode(contra), 1, usu));
            uCorreoRep.save(new UsuarioCorreo(perReq.getCorreo(), 1, usu));
            uCelulrRep.save(new UsuarioCelular(perReq.getTel(), 1, usu));
            return new RespUsuarioOperation("EXITO", "");
        } else {
            return new RespUsuarioOperation("ERROR","ERU1 - Usuario ya existe");
        }
    }

    @PostMapping("/addBulk")
    public RespUsuarioOperation bulkadd(@RequestBody UsuarioOperationDTO request) throws Exception {
        System.out.println(JSONUtil.toJSON(request));
        PersonaClienteDto perDTO = request.getPersonaCliente();
        PersonaCliente per = new PersonaCliente(perDTO.getNombres(), perDTO.getPriApe(), perDTO.getSecApe(), "",1);
        DireccionReq dirReq = perDTO.getDireccion();
        String concat = dirReq.getUbigeo().getConcat();
        personaRep.saveAndFlush(per);
        direccionRepository.insertDireccionBulk(per.getIdPersonaCliente(), perDTO.getDireccion().getDesc(),
                concat.split("-")[0],concat.split("-")[1],concat.split("-")[2], dirReq.getCity());
        documentoRepository.insertDocumentoBulk(per.getIdPersonaCliente(), perDTO.getDoc().getDesc(), perDTO.getDoc().getTipo());
        telefonoRepository.insertDireccionBulk(per.getIdPersonaCliente(), perDTO.getTel());
        Usuario usu = usuarioRepository.saveAndFlush(new Usuario(request.getUsuarioDesc(),"1", request.getPadres(), per, usuarioRepository.findTipPermDesc("TOTAL"), usuarioRepository.findTipUsuario(request.getTipoUsuario().getId())));

        uContraRep.save(new UsuarioContrasena("123456", "", 1, usu));
        uCorreoRep.save(new UsuarioCorreo(perDTO.getCorreo(), 1, usu));
        uCelulrRep.save(new UsuarioCelular(perDTO.getTel(), 1, usu));

        return new RespUsuarioOperation("EXITO","");
    }

    @PostMapping("/addBulkVend")
    public RespUsuarioOperation bulkaddVend(@RequestBody UsuarioOperationDTO request) throws Exception {
        System.out.println(JSONUtil.toJSON(request));
        PersonaClienteDto perDTO = request.getPersonaCliente();
        PersonaCliente per = new PersonaCliente(perDTO.getNombres(), perDTO.getPriApe(), perDTO.getSecApe(), "",1);
        DireccionReq dirReq = perDTO.getDireccion();
        String concat = dirReq.getUbigeo().getConcat();
        personaRep.saveAndFlush(per);
        direccionRepository.insertDireccionBulk(per.getIdPersonaCliente(), perDTO.getDireccion().getDesc(),
                concat.split("-")[0],concat.split("-")[1],concat.split("-")[2], dirReq.getCity());
        documentoRepository.insertDocumentoBulk(per.getIdPersonaCliente(), perDTO.getDoc().getDesc(), perDTO.getDoc().getTipo());
        telefonoRepository.insertDireccionBulk(per.getIdPersonaCliente(), perDTO.getTel());
        Usuario usu = usuarioRepository.saveAndFlush(new Usuario(request.getUsuarioDesc(),"1", request.getPadres(), per, usuarioRepository.findTipPermDesc("TOTAL"), usuarioRepository.findTipUsuario(request.getTipoUsuario().getId())));

        uContraRep.save(new UsuarioContrasena("123456", "", 1, usu));
        uCorreoRep.save(new UsuarioCorreo(perDTO.getCorreo(), 1, usu));
        uCelulrRep.save(new UsuarioCelular(perDTO.getTel(), 1, usu));

        return new RespUsuarioOperation("EXITO","");
    }

    @PutMapping
    public RespUsuarioOperation updateUsuario(@RequestBody UsuarioOperationDTO request) throws Exception {
        System.out.println(JSONUtil.toJSON(request));

        Optional<Usuario> opUsuario = usuarioRepository.findNativeById(request.getId());
        if (opUsuario.isPresent()) {
            System.out.println("PRESENTE");
            Usuario usuario = opUsuario.get();
            usuario.setUsuarioPadres(request.getPadres());
            PersonaCliente persona = usuario.getIdpersonaCliente();
            persona.setPersonaNombres(request.getPersonaCliente().getNombres());
            persona.setPersonaNacimiento(request.getPersonaCliente().getNacimiento());
            persona.setPersonaPrimerApellido(request.getPersonaCliente().getPriApe());
            persona.setPersonaSegundoApellido(request.getPersonaCliente().getSecApe());
            if(request.getTipo().equalsIgnoreCase("PDV")){
                persona.setPersonaRazonSocial(request.getPersonaCliente().getRazSoc());
                Direccion direc = direccionRepository.findByPersonaId(persona.getIdPersonaCliente());
                DireccionReq direcReq = request.getPersonaCliente().getDireccion();
                direc.setDireccionDesc(direcReq.getDesc());
                direc.setDireccionZona(direcReq.getZona());
                direc.setDireccionRef(direcReq.getRef());
                direc.setUbigeo(ubigeoRepository.findByDepProDist(direcReq.getUbigeo().getConcat().split("-")[0],direcReq.getUbigeo().getConcat().split("-")[1],direcReq.getUbigeo().getConcat().split("-")[2]));
                direccionRepository.saveAndFlush(direc);
                Documento doc = documentoRepository.findDocumentBussiness(persona.getIdPersonaCliente());
                doc.setDocumentoDesc(request.getPersonaCliente().getDocE().getDesc());
                documentoRepository.saveAndFlush(doc);
            }
            usuarioRepository.saveAndFlush(usuario);
            usuarioRepository.updateUserCell(request.getId(), request.getPersonaCliente().getTel());
            usuarioRepository.updateUserCorreo(request.getId(), request.getPersonaCliente().getCorreo());
            if(request.getTipo().equalsIgnoreCase("GEST")){
                personaRep.updateGestorType(request.getPersonaCliente().getGest().getIdTipo());
            }
            if(request.getTipo().equalsIgnoreCase("KAM")){
                Ubigeo ubi = ubigeoRepository.findMainDep(request.getPersonaCliente().getDireccion().getUbigeo().getConcat());
                direccionRepository.updatePersonaDireccionUbigeo(persona.getIdPersonaCliente(), ubi.getIdUbigeo());
            }
            return new RespUsuarioOperation("EXITO", "");
        } else {
            return new RespUsuarioOperation("ERROR","ERU2 - Usuario no existe");
        }
    }
    @PostMapping("/list")
    public List<UsuarioListJPA> listarUsuarios(@RequestBody ReqListUsuario reqListUsuario) {
        List<UsuarioListJPA> usuariosList = usuarioRepository.findUsuariosOffset(reqListUsuario.getMin(), reqListUsuario.getMax());
        if (!usuariosList.isEmpty()) {
            return usuariosList;
        } else {
            return new ArrayList<UsuarioListJPA>();
        }
    }

    @GetMapping("/list/{id}")
    public UsuarioOperationDTO listaUnUsuario(@PathVariable Integer id) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            TipoUsuario type = usuario.get().getIdtipoUsuario();
            PersonaCliente personaCliente = usuario.get().getIdpersonaCliente();
            String typeResp = type.getTipoUsuarioDesc().equalsIgnoreCase("Gestor") ? "GEST" : type.getTipoUsuarioDesc().equalsIgnoreCase("KAM") ? "KAM" : type.getTipoUsuarioDesc().contains("PDV") ? "PDV" : "NORMAL";
            UsuarioOperationDTO usuarioUp = new UsuarioOperationDTO(usuario.get().getIdUsuario(), typeResp, usuario.get().getUsuarioDesc(), new TipoUsuarioDTO(type.getIdTipoUsuario(), type.getTipoUsuarioDesc()));
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
            }
            usuarioUp.setPersonaCliente(personaDto);
            usuarioUp.setPadres(usuario.get().getUsuarioPadres());
            if (usuarioUp.getPadres() != null) {
                String[] listaPadres = usuarioUp.getPadres().split(",");
                usuarioUp.setPadresList(usuarioRepository.findUsuariosInList(listaPadres));
            }
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
