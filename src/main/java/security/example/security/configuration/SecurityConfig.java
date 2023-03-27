package security.example.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import security.example.security.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Anotacion que permite habilitar @PreAuthorize y @PostAuthorize
// @SuppressWarnings("deprecation")
//NUEVA VERSION DONDE NO ES NECESARIO EXTENDER LA CLASE
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/{
    /**
     * =========================================================================================================
     * @see UserDetails es una interfaz propia de SpringSecurity que permite manejar los Usuarios Autenticados
     * REQUISITOS:
     * Entidad SecUsuario
     * Entidad SecRol
     * Entidad SecAutoridad (Opcional)
     * Clase que implemente la interfaz UserDetails
     * 
     * @see UserDetailsImpl
     * Ahi se maneja la conversion de SecUsuario a UserDetails
     * =========================================================================================================
     * @see UserDetailsService es una interfaz propia de SpringSecurity que permite establecer un Usuario Autenticado
     * a un UserDetailsImpl
     * REQUISITOS:
     * DAO y Service para consultar SecUsuario
     * Clase que implemente la interfaz UserDetailsService
     * 
     * @see UserDetailsServiceImpl
     * Ahi se maneja la consulta de SecUsuario y establece el SecUsuario como un UserDetailsImpl
     * para que SpringSecurity pueda manejar al usuario autenticado
     * =========================================================================================================
     */

    //ANTIGUA INYECCION DE DEPENDENCIA
    // private final UserDetailsServiceImpl userDetailsServiceImpl;
    // public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
    //     this.userDetailsServiceImpl = userDetailsServiceImpl;
    // }

    /**
     * @return Desencriptador de passwords
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 
     * @return Servicio que implementa UserDetailsService para obtener al usuario
     * y establecer un nuevo UserDetailsImpl
     */
    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    /**
     * @return Autenticador que crea un UserDetailsImpl
     * 
     */
    @Bean
    DaoAuthenticationProvider authProvider(){
        final var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    /**
     * 
     * @param http
     * @return Configuracion de acceso a URL con permisos de ROL Y AUTHORITY, Login, Logout
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                // Se permiten todos los recursos estáticos o URL en estos patrones a todos
                .antMatchers("/css/**", "/js/**", "/images/**", "/img/**", "/scss/**")
                    .permitAll()
                // Se restringe el acceso a este patrón de URL de un controlador a usuarios con rol "JEFE_SISTEMAS"
                .antMatchers("/admin/**")
                    .hasRole("JEFE_SISTEMAS").anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    // Se especifica la URL de procesamiento de la solicitud de inicio de sesión
                    // Importante tener un <form th:action=@{/login-process} method="POST">
                    .loginProcessingUrl("/login-process")
                    .defaultSuccessUrl("/inicio", true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout") //URL Para cerrar sesion
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                .build();
    }


    /**
     * VERSION ANTIGUA
     * Establecer el autenticador y su servicio de autentificacion
     */
    /*
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authProvider());
    }
     */

    
    /**
     * VERSION ANTIGUA
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests() 
                // Se permiten todos los recursos estáticos o URL en estos patrones a todos
                .antMatchers("/css/**", "/js/**", "/images/**", "/img/**", "/scss/**")
                    .permitAll()
                // Se restringe el acceso a este patrón de URL de un controlador a usuarios con rol "JEFE_SISTEMAS"
                .antMatchers("/admin/**")
                    .hasRole("JEFE_SISTEMAS").anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    // Se especifica la URL de procesamiento de la solicitud de inicio de sesión
                    // Importante tener un <form th:action=@{/login-process} method="POST">
                    .loginProcessingUrl("/login-process")
                    .defaultSuccessUrl("/inicio", true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout") //URL Para cerrar sesion
                    .logoutSuccessUrl("/login")
                    .permitAll();
    }
     */
}
