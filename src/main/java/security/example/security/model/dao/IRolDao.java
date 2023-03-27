package security.example.security.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import security.example.security.model.entity.SecRol;

public interface IRolDao extends JpaRepository<SecRol, Long>{
    public SecRol findByRol(String rol);
}
