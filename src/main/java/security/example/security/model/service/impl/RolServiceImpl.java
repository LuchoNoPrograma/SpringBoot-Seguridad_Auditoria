package security.example.security.model.service.impl;

import java.util.List;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import security.example.security.model.dao.IRolDao;
import security.example.security.model.entity.SecRol;
import security.example.security.model.service.interfaces.IRolService;

@Service
public class RolServiceImpl implements IRolService{
    @Autowired
    private IRolDao dao;

    /**
     * @param nombre_rol
     * @return SecRol sin acceso a List<Usuario>
     */
    @Override
    public SecRol findByRol(String rol) {
        return dao.findByRol(rol);
    }

    @Override
    public SecRol save(SecRol secRol) {
        return dao.save(secRol);
    }

    @Override
    public byte delete(SecRol secRol) {
        return 0;
    }

    @Override
    public List<SecRol> findAll() {
        return dao.findAll();
    }

    /**
     * Transactional crea una sesion de BD y readOnly=true establece que solo se obtendra datos
     * la cual optimizara la BD en procesos.
     * Cuando un proceso falla en la transaccion del metodo, todos los procesos se cancelan
     * 
     * Hibernate.initialize(objetoLAZY): 
     * Inicializa de manera EAGER la relacion permitiendo obtenerlo mediante POO. 
     * 
     * Esto es necesario debido a que la relación entre SecRol y SecUsuario es LAZY por defecto.
     * @param nombre_rol
     * @return SecRol con List<Usuarios> cargados
     */
    @Transactional(readOnly = true)
    @Override
    public SecRol findByRolEagerly(String rol) {
        SecRol secRol = dao.findByRol(rol);
        Hibernate.initialize(secRol.getUsuarios());
        return secRol;
    }
    
}
