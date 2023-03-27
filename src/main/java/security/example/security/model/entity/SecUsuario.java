package security.example.security.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import security.example.security.model.entity.auditory.AuditoriaRevision;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sec_usuario")
public class SecUsuario extends AuditoriaRevision{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 60)
    private boolean enabled;

    /**
     * @ManyToMany (fetch= FetchType.EAGER): relacion muchos a muchos.
     *                   Donde FetchType.EAGER cargará todos los roles relacionados de
     *                   forma inmediata y obtener datos compuestos con POO. Ejemplo:
     *                   Set<SecRol> rolesUsuario = usuarioService.findByIdUsuario(idUsuario).getRoles();
     * 
     * @JoinTable: indica que la relación muchos-a-muchos donde se almacenará en una
     *             tabla intermedia llamada "sec_usuarios_roles".
     * 
     * @JoinColumn: define el nombre de la columna que se utilizará como clave
     *              externa en la tabla intermedia.
     * 
     * @UniqueConstraint: establece una restricción única en las columnas
     *                    "id_usuario" e "id_rol" para evitar elementos duplicados.
     *                    Set<Rol> tambien evita duplicado de roles en la Coleccion
     * 
     * IMPORTANTE! Para que SpringBoot diferencie entre un rol y una autoridad
     * el nombre del Rol debe tener el prefijo ROLE_. Ejemplo: ROLE_JEFE_SISTEMAS
     * 
     * @author Luis Alberto Morales Villaca 24/3/2023
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_rol" }))
    private Set<SecRol> roles = new HashSet<>();

    /**
     * @ManyToMany (fetch= FetchType.EAGER): relacion muchos a muchos.
     *                   Donde FetchType.EAGER cargará todos los roles relacionados de
     *                   forma inmediata y obtener datos compuestos con POO. Ejemplo:
     *                   Set<SecAutoridad> autoridadesUsuario = usuarioService.findByIdUsuario(idUsuario).getAutoridades();
     * 
     * @JoinTable: indica que la relación muchos-a-muchos donde se almacenará en una
     *             tabla intermedia llamada "sec_usuarios_autoridades".
     * 
     * @JoinColumn: define el nombre de la columna que se utilizará como clave
     *              externa en la tabla intermedia.
     * 
     * @UniqueConstraint: establece una restricción única en las columnas
     *                    "id_usuario" e "id_autoridad" para evitar elementos duplicados.
     *                    Set<Autoridad> tambien evita duplicado de roles en la Coleccion
     * 
     * 
     * @author Luis Alberto Morales Villaca 24/3/2023
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_usuarios_autoridades",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_autoridad"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_autoridad" }))
    private Set<SecAutoridad> autoridades = new HashSet<>();
}
