package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.payload.DTO.TipoDTO;
import gestpvv.entel.loginapi.payload.DTO.TiposAuxDTO;
import gestpvv.entel.loginapi.repository.PersonaClienteRepository;
import gestpvv.entel.loginapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aux/")
public class AuxController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PersonaClienteRepository personaClienteRepository;

    @GetMapping("/tiposDoc")
    public List<TipoDTO> listaTipoDoc(){
        List<TipoDTO> listaTipos = new ArrayList<>();
        personaClienteRepository.findTiposDocsAct().forEach(t -> {
            listaTipos.add(new TipoDTO(t.getIdTipoDocumento(), t.getTipoDocumentoDesc()));
        });
        return listaTipos;
    }

    @GetMapping("/tipos")
    public  TiposAuxDTO listaTipos(){
        List<TipoDTO> lDocs = new ArrayList<>();
        personaClienteRepository.findTiposDocsAct().forEach(t -> {
            lDocs.add(new TipoDTO(t.getIdTipoDocumento(), t.getTipoDocumentoDesc()));
        });
        List<TipoDTO> lUsrs = new ArrayList<>();
        personaClienteRepository.findTiposUsuarioAct().forEach(t -> {
            lUsrs.add(new TipoDTO(t.getIdTipoUsuario(), t.getTipoUsuarioDesc()));
        });
        return new TiposAuxDTO(lDocs, lUsrs);
    }
}
