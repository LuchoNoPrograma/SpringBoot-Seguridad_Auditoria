package security.example.security.loader;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.example.security.model.entity.SecAutoridad;
import security.example.security.model.entity.SecRol;
import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IAutoridadService;
import security.example.security.model.service.interfaces.IRolService;
import security.example.security.model.service.interfaces.IUsuarioService;

//Anotarlo para que sea valido poder manejarlo por IoC de SpringBoot y utilizar sus anotaciones
@Component
public class UsuarioLoader {
    private boolean alreadySetup = false;
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IAutoridadService autoridadService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @EventListener(ApplicationReadyEvent.class) //Anotacion que se ejecuta cada que se inicia el programa
    public void initializeSecurity() {
        if (alreadySetup) return;

        SecRol rolJefeSistema = createRoleIfNotFound("ROLE_JEFE_SISTEMAS");
        SecRol rolCoordinadorPrograma = createRoleIfNotFound("ROLE_COORDINADOR_PROGRAMA");
        SecRol rolSubjefePrograma = createRoleIfNotFound("ROLE_SUBJEFE_PROGRAMA");

        SecAutoridad authGenerarReporte = createAuthorityIfNotFound("GENERAR_REPORTE");
        SecAutoridad authLeerReporte = createAuthorityIfNotFound("VER_REPORTE");

        createUserIfNotFound("jefesistemas", "jefesistemas", "jefesistemas", true, 
                Set.of(rolJefeSistema, rolCoordinadorPrograma, rolSubjefePrograma),
                Set.of(authGenerarReporte,authLeerReporte));

        createUserIfNotFound("coordinador", "coordinador", "coordinador",
                true, Set.of(rolCoordinadorPrograma), Set.of(authGenerarReporte, authLeerReporte));

        createUserIfNotFound("subjefe", "subjefe", "subjefe",
                true, Set.of(rolSubjefePrograma), Set.of(authLeerReporte));

        alreadySetup = true;
    }

    @Transactional
    SecRol createRoleIfNotFound(String rol) {
        SecRol secRol = rolService.findByRol(rol);
        if (secRol == null) {
            secRol = new SecRol(rol);
            rolService.save(secRol);
        }
        return secRol;
    }

    @Transactional
    SecAutoridad createAuthorityIfNotFound(String autoridad){
        SecAutoridad secAutoridad = autoridadService.findByAutoridad(autoridad);
        if (secAutoridad == null){
            secAutoridad = new SecAutoridad(autoridad);
            autoridadService.save(secAutoridad);
        }
        return secAutoridad;
    }

    @Transactional
    void createUserIfNotFound(String username, String password, String email,
            boolean enabled, Set<SecRol> roles) {
        SecUsuario secUsuario = usuarioService.findByEmail(email);
        if (secUsuario == null) {
            secUsuario = new SecUsuario();
            secUsuario.setUsername(username);
            secUsuario.setPassword(bCryptPasswordEncoder.encode(password));
            secUsuario.setEmail(email);
            secUsuario.setRoles(roles);
            secUsuario.setEnabled(true);
            usuarioService.save(secUsuario);
        }
    }

    @Transactional
    void createUserIfNotFound(String username, String password, String email,
            boolean enabled, Set<SecRol> roles, Set<SecAutoridad> autoridades) {
        SecUsuario secUsuario = usuarioService.findByEmail(email);
        if (secUsuario == null) {
            secUsuario = new SecUsuario();
            secUsuario.setUsername(username);
            secUsuario.setPassword(bCryptPasswordEncoder.encode(password));
            secUsuario.setEmail(email);
            secUsuario.setRoles(roles);
            secUsuario.setAutoridades(autoridades);
            secUsuario.setEnabled(true);
            usuarioService.save(secUsuario);
        }
    }
}
