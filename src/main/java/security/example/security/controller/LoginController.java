package security.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IUsuarioService;

@Controller
public class LoginController {
    @Autowired
    private IUsuarioService usuarioService;

    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Atributo de Sesiones, se mantendran hasta que se cierre la sesion
     */
    @GetMapping({"/inicio", "/"})
    public String inicio(Authentication auth, HttpSession session, Model model){
        SecUsuario usuarioAutenticado = usuarioService.findByUsername(auth.getName());
        session.setAttribute("idUsuario", usuarioAutenticado.getIdUsuario());
        session.setAttribute("username", usuarioAutenticado.getUsername());        
        return "inicio";
    }

    /**
     * Se invalida la sesion, en el proceso elimina los atributos de sesion
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        session.invalidate();
        return "redirect:/login?logout";
    }
}
