package security.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import security.example.security.model.entity.SecRol;
import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IRolService;

/**
 * Es válido utilizarlo a nivel de clase
 * Todos los metodos requieren rol de JEFE_SISTEMAS para acceder
 * Se puede configurar en Controladores como Servicios y DAOs
 */
@PreAuthorize("hasRole('JEFE_SISTEMAS')")
@Controller
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private IRolService rolService;

    @GetMapping("/{rol}")
    public String buscarPorRol(@PathVariable String rol, Model model){
        model.addAttribute("rol", rolService.findByRol(rol));
        model.addAttribute("operacion", "LAZY");
        return "seguridad/admRol";
    }

    @GetMapping("/{rol}/EAGER")
    public String buscarPorRolEagerly(@PathVariable String rol, Model model){
        SecRol secRol = rolService.findByRolEagerly(rol);
        List<SecUsuario> usuariosConRol = secRol.getUsuarios();

        model.addAttribute("rol", secRol);
        model.addAttribute("usuarios", usuariosConRol);
        model.addAttribute("operacion", "EAGER");
        return "seguridad/admRol";
    }
}
