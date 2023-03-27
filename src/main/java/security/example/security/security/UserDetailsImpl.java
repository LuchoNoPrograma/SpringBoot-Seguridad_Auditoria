package security.example.security.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import security.example.security.model.entity.SecUsuario;

/**
 * Al instanciar la clase se establece SecUsuario y empezar la Conversion
 * a la clase UserDetails para el manejo de Seguridad de SpringSecurity
 */
public class UserDetailsImpl implements UserDetails {

    private SecUsuario secUsuario;

    public UserDetailsImpl(SecUsuario secUsuario) {
        this.secUsuario = secUsuario;
    }

    public Long getId() {
        return secUsuario.getIdUsuario();
    }

    /**
     * Tanto ROLE y AUTHORITY pertenecen a la clase SimpleGrantedAuthority
     * 
     * ROLE_ es un prefijo para diferenciar de un
     * AUTHORITY
     * 
     * Requerido establecerlo para poder realizar permisos de acceso a metodos de @Controller o @RestController
     * 
     * 
     * Utilizar la anotacion: Para restringir acceso a metodos de @Controller que tengan de parametro @PathVariable
     * @PreAuthorize (metodoEspecial("Nombre"))            -> Nombre Original en BD
     * @PostAuthorize (metodoEspecial("Nombre"))           -> Nombre Original en BD
     * 
     * 
     * @PreAuthorize (hasRole("NOMBRE_ROLE"))              -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAnyRole("NOMBRE_ROLE"))           -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAuthority("ROLE_NOMBRE_ROLE"))    -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAnyAuthority("ROLE_NOMBRE_ROLE")) -> ROLE_NOMBRE_ROLE
     * 
     * @PreAuthorize (hasAuthority("NOMBRE_AUTORIDAD"))    -> NOMBRE_AUTORIDAD
     * @PreAuthorize (hasAnyAuthority("NOMBRE_AUTORIDAD")) -> NOMBRE_AUTORIDAD
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        secUsuario.getRoles().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority(rol.getRol()));
        });

        secUsuario.getAutoridades().forEach(autoridad ->{
            authorities.add(new SimpleGrantedAuthority(autoridad.getAutoridad()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return secUsuario.getPassword();
    }

    @Override
    public String getUsername() {
        return secUsuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return secUsuario.isEnabled();
    }
}
