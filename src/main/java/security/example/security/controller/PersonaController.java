package security.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import security.example.security.exception.PersonaException.DipAlreadyExistsException;
import security.example.security.exception.PersonaException.EmailAlreadyExistsException;
import security.example.security.exception.PersonaException.PersonaNotFoundException;
import security.example.security.model.entity.PrsPersona;
import security.example.security.model.service.interfaces.IPersonaService;

@PreAuthorize("hasRole('JEFE_SISTEMAS')")
@Controller
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private IPersonaService personaService;

    @GetMapping("/inicio")
    public String cargarInicio(Model model) {
        model.addAttribute("listaPersonas", personaService.findAll());
        model.addAttribute("operacion", "tabla");
        return "administrar/persona";
    }

    @GetMapping("/formulario")
    public String cargarFormulario(@ModelAttribute PrsPersona prsPersona, Model model) {
        model.addAttribute("operacion", "formulario");
        return "administrar/persona";
    }

    @PostMapping("/registrar")
    public String cargarInicio(@Validated PrsPersona prsPersona, Model model) {
        try {
            personaService.save(prsPersona);
            return "redirect:/persona/inicio";
        } catch (DipAlreadyExistsException e) {
            model.addAttribute("operacion", "formulario");
            model.addAttribute("error", "dip");

            return "administrar/persona";
        } catch (EmailAlreadyExistsException e) {
            model.addAttribute("operacion", "formulario");
            model.addAttribute("error", "email");

            return "administrar/persona";
        }
    }

    @GetMapping("/dip")
    public String buscarPorDip(@RequestParam String dip, Model model){
        try{
            model.addAttribute("listaPersonas", List.of(personaService.findByDip(dip)));
            model.addAttribute("operacion", "tabla");
            return "administrar/persona";
        }catch(PersonaNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();

            model.addAttribute("operacion", "tabla");
            model.addAttribute("errorNoExistente", e.getMessage());
            return "administrar/persona";
        }
    }

    @GetMapping("/email")
    public String buscarPorEmail(@RequestParam String email, Model model){
        try{
            model.addAttribute("listaPersonas", List.of(personaService.findByEmail(email)));
            model.addAttribute("operacion", "tabla");
            return "administrar/persona";
        }catch(PersonaNotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();

            model.addAttribute("operacion", "tabla");
            model.addAttribute("errorNoExistente", e.getMessage());
            return "administrar/persona";
        }
    }
}
