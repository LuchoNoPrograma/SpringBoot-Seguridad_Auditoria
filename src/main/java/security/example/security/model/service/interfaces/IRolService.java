package security.example.security.model.service.interfaces;


import java.util.List;

import security.example.security.model.entity.SecRol;

public interface IRolService {
    public SecRol save(SecRol secRol);
    public byte delete(SecRol secRol);
    public List<SecRol> findAll();
    public SecRol findByRol(String rol);
    public SecRol findByRolEagerly(String rol);
}
