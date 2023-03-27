package security.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import security.example.security.model.entity.SecAutoridad;
import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IAutoridadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PostAuthorize hacen primero el cuerpo del metodo y antes de return 
 * validan si el usuario tiene el permiso para poder realizar la accion
 */
@Controller
@RequestMapping("/autoridad")
public class AutoridadController {
    private static final Logger log = LoggerFactory.getLogger(AutoridadController.class);
    
    @Autowired
    private IAutoridadService autoridadService;

    @PostAuthorize("hasRole('JEFE_SISTEMAS')")
    @GetMapping("/{autoridad}")
    public String buscarPorAutoridad(@PathVariable String autoridad, Model model){
        model.addAttribute("autoridad", autoridadService.findByAutoridad(autoridad));
        model.addAttribute("operacion", "LAZY");
        
        //Buscar en Terminal para Confirmar que si se ejecuta primero
        log.info("SE ACCEDE AL METODO DESPUES DE EJECUTAR ESTE CUERPO");
        return "seguridad/admAutoridad";
    }

    @PostAuthorize("hasRole('JEFE_SISTEMAS')")
    @GetMapping("/{autoridad}/EAGER")
    public String buscarPorAutoridadEagerly(@PathVariable String autoridad, Model model){
        SecAutoridad secAutoridad = autoridadService.findByAutoridadEagerly(autoridad);
        List<SecUsuario> usuariosConAutoridad = secAutoridad.getUsuarios();

        model.addAttribute("autoridad", secAutoridad);
        model.addAttribute("usuarios", usuariosConAutoridad);
        model.addAttribute("operacion", "EAGER");

        //Buscar en Terminal para Confirmar que si se ejecuta primero
        log.info("EVITAR HACER CUALQUIER PROCESO A EXCEPCION DE OBTENER UN DATO EN UN @PostAuthorize");
        return "seguridad/admAutoridad";
    }
}
