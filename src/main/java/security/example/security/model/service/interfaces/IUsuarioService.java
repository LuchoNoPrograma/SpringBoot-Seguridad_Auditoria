package security.example.security.model.service.interfaces;

import java.util.List;

import security.example.security.model.entity.SecUsuario;

public interface IUsuarioService {
    public SecUsuario save(SecUsuario secUsuario);
    public byte delete(SecUsuario secUsuario);

    public List<SecUsuario> findAll(SecUsuario secUsuario);

    public SecUsuario findByUsername(String username);
    public SecUsuario findByEmail(String email);
}
