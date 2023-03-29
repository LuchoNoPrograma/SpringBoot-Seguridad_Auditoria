package security.example.security.model.service.interfaces;

import java.util.List;

import security.example.security.model.entity.PrsPersona;

public interface IPersonaService {
    public PrsPersona save(PrsPersona prsPersona);
    public PrsPersona findByDip(String dip);
    public PrsPersona findByEmail(String email);
    public List<PrsPersona> findAll();
}
