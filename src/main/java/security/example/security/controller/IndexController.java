package security.example.security.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @EnableGlobalMethodSecurity (prePostEnabled = true)
 * Anotarla en la clase SecurityConfig, permite utilizar las validaciones de
 * Roles y Autoridades.
 * @see UserDetailsServiceImpl donde se hace la conversion de SecUsuario a 
 * @see UserDetailsImpl que realiza la conversion de:
 * 
 * SecRoles y SecAutoridad a una Collection<GrantedAuthority>
 * que posteriormente SpringSecurity utiliza para realizar el proceso de 
 * validacion si un usuario tiene el ROL o AUTORIDAD
 * 
 * Existen 2 Anotaciones que se usaran en el proyecto para autenticar
 * los permisos de acceso a un metodo
 * @PreAuthorize
 * @PostAuthorize
 */
@Controller
@RequestMapping("/")
public class IndexController {
    /**
     * Solo pueden acceder los usuarios que tengan SOLO el rol JEFE_SISTEMAS
     */
    @PreAuthorize("hasRole('JEFE_SISTEMAS')")
    @GetMapping("/jefe-sistemas")
    public String rolJefeSistemas(Model model, HttpSession session){
        return "acceso/rol/jefe-sistemas";
    }

    /**
     * Acceden los usuarios que asocien el rol COORDINADOR_PROGRAMA
     */
    @PreAuthorize("hasAnyRole('COORDINADOR_PROGRAMA')")
    @GetMapping("/coordinador-programa")
    public String rolCoordinadorPrograma(Model model, HttpSession session){
        return "acceso/rol/coordinador-programa";
    }

    /**
     * Acceden los usuarios que asocien el rol SUBJEFE_PROGRAMA
     */
    @PreAuthorize("hasAnyRole('SUBJEFE_PROGRAMA')")
    @GetMapping("/subjefe-programa")
    public String rolSubjefePrograma(Model model, HttpSession session){
        return "acceso/rol/subjefe-programa";
    }

    /**
     * Acceden los usuarios que asocien la autoridad GENERAR_REPORTE
     */
    @PreAuthorize("hasAnyAuthority('GENERAR_REPORTE')")
    @GetMapping("/programas/reportes/generar")
    public String autoridadGenerarReporte(Model model, HttpSession session){
        return "acceso/autoridad/generar-reporte";
    }

    /**
     * Acceden los usuarios que asocien la autoridad VER_REPORTE
     */
    @PreAuthorize("hasAnyAuthority('VER_REPORTE')")
    @GetMapping("/programas/reportes/ver")
    public String autoridadVerReporte(Model model, HttpSession session){
        return "acceso/autoridad/ver-reporte";
    }
}

