package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.payload.DTO.TipoDTO;
import gestpvv.entel.loginapi.payload.DTO.TiposAuxDTO;
import gestpvv.entel.loginapi.repository.*;
import gestpvv.entel.loginapi.repository.dtos.UsuariosSuperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aux/")
public class AuxController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private PersonaClienteRepository personaClienteRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private GestorRepository gestorRepository;

    @GetMapping("/loc/{type}/{subtype}")
    public List<String> listLocations(@PathVariable String type, @PathVariable String subtype){
        if(type.equalsIgnoreCase("DEP")) {
            return direccionRepository.findDeps();
        } else if (type.equalsIgnoreCase("PRO")) {
            return direccionRepository.findProvs(subtype);
        } else if (type.equalsIgnoreCase("DST")) {
            return direccionRepository.findDists(subtype);
        }
        return new ArrayList<String>();
    }

    @GetMapping("/tiposDoc")
    public List<TipoDTO> listaTipoDoc(){
        List<TipoDTO> listaTipos = new ArrayList<>();
        documentoRepository.findTiposDocsAct().forEach(t -> {
            listaTipos.add(new TipoDTO(t.getIdTipoDocumento(), t.getTipoDocumentoDesc()));
        });
        return listaTipos;
    }

    @GetMapping("/tipos")
    public  TiposAuxDTO listaTipos(){
        List<TipoDTO> lDocs = new ArrayList<>();
        documentoRepository.findTiposDocsAct().forEach(t -> {
            lDocs.add(new TipoDTO(t.getIdTipoDocumento(), t.getTipoDocumentoDesc()));
        });
        List<TipoDTO> lUsrs = new ArrayList<>();
        usuarioRepository.findTiposUsuarioAct().forEach(t -> {
            lUsrs.add(new TipoDTO(t.getIdTipoUsuario(), t.getTipoUsuarioDesc()));
        });
        List<TipoDTO> lPerm = new ArrayList<>();
        usuarioRepository.findTipoPermAct().forEach(t -> {
            lPerm.add(new TipoDTO(t.getIdTipoPermiso(), t.getTipoPermisoDesc()));
        });
        List<TipoDTO> lTelf = new ArrayList<>();
        personaClienteRepository.findTipoTelAct().forEach(t -> {
            lTelf.add(new TipoDTO(t.getIdTipoTelefono(), t.getTipoTelefonoDesc()));
        });
        List<TipoDTO> lGest = new ArrayList<>();
        gestorRepository.findTipoGestAct().forEach(t -> {
            lGest.add(new TipoDTO(t.getIdTipoGestor(), t.getTipoGestorDesc()));
        });
        return new TiposAuxDTO(lDocs, lUsrs, lPerm, lTelf, lGest);
    }

    @GetMapping("/super/{desc}")
    public List<UsuariosSuperDTO> supervisoresDisp(@PathVariable String desc){
        String criteria = "";
        switch (desc) {
            case "Socio":
                criteria = "KAM";
                break;
            case "Supervisor":
                criteria = "Socio";
                break;
            case "Gestor":
                criteria = "Supervisor";
                break;
            case "PDV":
            case "PDVX":
                criteria = "Gestor";
                break;
            case "Lider":
            case "Itinerante":
                criteria = "PDV%";
                break;
            default:
                criteria = "";
        }
        return usuarioRepository.findUsuariosByCriteria(criteria);
    }

    @GetMapping("/amount/usuarios")
    public Integer usuariosAmount(){
        return usuarioRepository.findAmountUsuarios();
    }
}
