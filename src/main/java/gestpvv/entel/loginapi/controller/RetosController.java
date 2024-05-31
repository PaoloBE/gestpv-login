package gestpvv.entel.loginapi.controller;

import gestpvv.entel.loginapi.repository.RetoRepository;
import gestpvv.entel.loginapi.repository.dtos.RetoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/retos")
public class RetosController {
    @Autowired
    private RetoRepository retoRep;

    @GetMapping("/list")
    public List<RetoJPA> listRetos(){
        return retoRep.findAllRetos();
    }
}
