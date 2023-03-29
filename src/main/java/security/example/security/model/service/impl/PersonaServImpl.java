package security.example.security.model.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import security.example.security.exception.PersonaException.DipAlreadyExistsException;
import security.example.security.exception.PersonaException.EmailAlreadyExistsException;
import security.example.security.exception.PersonaException.PersonaNotFoundException;
import security.example.security.model.dao.IPersonaDao;
import security.example.security.model.entity.PrsPersona;
import security.example.security.model.service.interfaces.IPersonaService;

@Service
public class PersonaServImpl implements IPersonaService {
    @Autowired
    private IPersonaDao dao;

    /**
     * Utilizar transactional para detener cualquier proceso si ocurre una excepcion
     * en registro de datos de repositorio
     */
    @Transactional
    public PrsPersona save(PrsPersona prsPersona) {
        try {
            return dao.save(prsPersona);
        } catch (DataIntegrityViolationException e) {
            /**
             * Para obtener excepciones de llave unica duplicada, se tiene que obtener el Throwable y su mensaje
             * por defecto muestra:
             * duplicate key value violates unique constraint "uk_4466lfles67r4esdxl8e0e4s2"
            *       Detail: Key (dip)=(13163117) already exists.
             */
            Throwable mostSpecificCause = e.getMostSpecificCause();
            System.out.println("EXCEPCION DETECTADA");
            String errorMessage = mostSpecificCause.getMessage();

            if (errorMessage != null && errorMessage.contains("(dip)")) {
                System.out.println("EXCEPCION DIP");
                throw new DipAlreadyExistsException("El DIP ingresado ya existe");

            } else if (errorMessage != null && errorMessage.contains("(email)")) {
                System.out.println("EXCEPCION EMAIL");
                throw new EmailAlreadyExistsException("El correo electrónico ingresado ya existe");
            }
            throw e;
        }

    }

    @Override
    public PrsPersona findByDip(String dip) {
        return dao.findByDip(dip).orElseThrow(() -> new PersonaNotFoundException("Persona no encontrada por DIP: " + dip));
            
    }

    @Override
    public PrsPersona findByEmail(String email) {
        return dao.findByEmail(email).orElseThrow(() -> new PersonaNotFoundException("Persona no encontrada por email: " + email));
    }

    @Override
    public List<PrsPersona> findAll() {
        return dao.findAll();
    }
}
