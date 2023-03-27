package security.example.security.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.example.security.model.dao.IAutoridadDao;
import security.example.security.model.entity.SecAutoridad;
import security.example.security.model.service.interfaces.IAutoridadService;

@Service
public class AutoridadServiceImpl implements IAutoridadService{
    @Autowired
    private IAutoridadDao dao;

    /**
     * @return SecAutoridad sin acceso a List<Usuario>
     */
    @Override
    public SecAutoridad save(SecAutoridad secRol) {
        return dao.save(secRol);
    }

    @Override
    public byte delete(SecAutoridad secRol) {
        dao.save(secRol);
        return 1;
    }

    @Override
    public List<SecAutoridad> findAll() {
        return dao.findAll();
    }

    @Override
    public SecAutoridad findByAutoridad(String autoridad) {
        return dao.findByAutoridad(autoridad);
    }
    
    /**
     * @return SecAutoridad con List<SecUsuario> cargados
     */
    @Override
    public SecAutoridad findByAutoridadEagerly(String autoridad){
        return dao.findByAutoridadEagerly(autoridad);
    }
}
