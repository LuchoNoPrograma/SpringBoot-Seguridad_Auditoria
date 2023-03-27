package security.example.security.model.service.interfaces;

import java.util.List;

import security.example.security.model.entity.SecAutoridad;

public interface IAutoridadService {
    public SecAutoridad save(SecAutoridad secRol);
    public byte delete(SecAutoridad secRol);
    public List<SecAutoridad> findAll();
    public SecAutoridad findByAutoridad(String autoridad);
    public SecAutoridad findByAutoridadEagerly(String autoridad);
}
