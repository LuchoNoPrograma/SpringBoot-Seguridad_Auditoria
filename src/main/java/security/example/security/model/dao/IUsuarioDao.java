package security.example.security.model.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import security.example.security.model.entity.SecUsuario;


public interface IUsuarioDao extends JpaRepository<SecUsuario, Long>{
    /**
     * JpaRepository<Entidad, Llave> procesa las consultas con la siguiente nomenclatura:
     * 
     * findBy'NombreAtributo'(String nombreAtributo)
     * 
     * donde esto es similar a buscar por posicion de parametro
     * @Query ("SELECT u FROM SecUsuario u WHERE u.username = ?1")
     * SecUsuario findByUsername(String username);
     */
    public SecUsuario findByUsername(String username);


    /**
     * Otra alternativa a buscar por posicion de parametro es
     * buscar por nombre de parametro. Ejemplo:
     * 
     * @Query ("SELECT u FROM SecUsuario u WHERE u.email = :email")
     * SecUsuario findByEmail(@Param("nombre") String email);
    */
    @Query("SELECT u FROM SecUsuario u WHERE u.email = ?1")
    public SecUsuario findByEmail(String email);
}
