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
@Table(name = "sec_autoridad")
public class SecAutoridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autoridad")
    private Long idAutoridad; 

    private String autoridad;

    /**
     * @ManyToMany (fetch=FetchType.LAZY): relacion muchos a muchos.
     *                   Donde FetchType.LAZY 'NO' cargará todos los usuarios
     *                   con las autoridades relacionadas de forma inmediata utilizando getUsuarios();
     * 
     * Esto con el objetivo de no sobrecargar la base de datos en una simple consulta
     * donde en un hipotetico caso se use FetchType.EAGER:
     * 
     * SecAutoridad autoridadVerReporte = autoridadService.findByAutoridad("VER_REPORTE")
     * La cual cargaria todos los usuarios sobrecargando la DB
     * cuando solo queremos asignarle la autoridad a un SecUsuario
     * 
     * Para acceder a ello se puede crear un metodo en el repositorio propio 
     * @see IAutoridadDao tiene un metodo donde utiliza JOIN FETCH para cargar la
     * columna con anotacion FetchType.LAZY
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "autoridades")
    private List<SecUsuario> usuarios;

    public SecAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }    
}
