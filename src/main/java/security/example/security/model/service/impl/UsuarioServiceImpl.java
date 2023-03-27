package security.example.security.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.example.security.model.dao.IUsuarioDao;
import security.example.security.model.entity.SecUsuario;
import security.example.security.model.service.interfaces.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioDao dao;

    @Override
    public SecUsuario findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public SecUsuario findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public SecUsuario save(SecUsuario secUsuario) {
        return dao.save(secUsuario);
    }

    @Override
    public byte delete(SecUsuario secUsuario) {
        dao.save(secUsuario);
        return 0;
    }

    @Override
    public List<SecUsuario> findAll(SecUsuario secUsuario) {
        return dao.findAll();
    }
}
