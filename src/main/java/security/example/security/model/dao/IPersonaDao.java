package security.example.security.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import security.example.security.model.entity.PrsPersona;

public interface IPersonaDao extends JpaRepository<PrsPersona, Long>{
    public Optional<PrsPersona> findByEmail(String email);
    public Optional<PrsPersona> findByDip(String email);
}
