package security.example.security.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import security.example.security.model.entity.SecAutoridad;

public interface IAutoridadDao extends JpaRepository<SecAutoridad, Long>{
    public SecAutoridad findByAutoridad(String autoridad);
    
    /**
     * 
     * @param autoridad
     * @return SecAutoridad pero ya puedes acceder a usuarios con POO
     * Ejemplo:
     * List<Usuarios> usuariosAutorizados = autoridadService.findByAutoridadEagerly("AUTORIDAD").getUsuarios();
     */
    @Query("SELECT s FROM SecAutoridad s JOIN FETCH s.usuarios WHERE s.autoridad = ?1")
    public SecAutoridad findByAutoridadEagerly(String autoridad);
}
