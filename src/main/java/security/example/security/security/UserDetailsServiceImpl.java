package security.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IUsuarioService;

/**
 * Servicio propio de SpringSecurity para manejar a un UserDetails y sus GrantedAuthority
 * para manejar los permisos
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private IUsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecUsuario usuario = userService.findByUsername(username);
        if (usuario == null) throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        return new UserDetailsImpl(usuario);
    }
    
}
