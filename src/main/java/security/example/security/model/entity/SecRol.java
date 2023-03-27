package security.example.security.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sec_rol")
public class SecRol{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "rol", length = 55)
    private String rol;

    /**
     * @ManyToMany (fetch=FetchType.LAZY): relacion muchos a muchos.
     *                   Donde FetchType.LAZY 'NO' cargará todos los usuarios
     *                   con los roles relacionados de forma inmediata utilizando getUsuarios(); 
     * 
     * Esto con el objetivo de no sobrecargar la base de datos en una simple consulta
     * donde en un hipotetico caso se use FetchType.EAGER:
     * 
     * Rol asignarRolDocente = rolService.findByRol("ROLE_DOCENTE");
     * La cual cargaria todos los usuarios sobrecargando la DB
     * cuando solo queremos un rol para asignar a un SecUsuario
     * 
     * Para acceder a una columna con FetchType.LAZY:
     * @see RolServiceImpl en metodo findByRolEagerly(String rol)
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<SecUsuario> usuarios;

    public SecRol(String rol) {
        this.rol = rol;
    }
}
