package security.example.security.model.entity.auditory;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import security.example.security.model.enums.Estado;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //Para no recibir errores cuando se serializa a JSON
@EntityListeners(AuditingEntityListener.class) //Activar Auditoria, revisar AuditoriaConfig
public abstract class AuditoriaRevision {

    //Requerido anotar con updatable=false de lo contrario se sobreescribe en cada auditoria
    //Utilizar LocalDate para Fechas y LocalDateTime para fechas y horas
    @CreatedDate
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "_registro", updatable = false)
    private LocalDateTime fechaRegistro;
    
    /**
     * Requerido anotar con updatable=false de lo contrario se sobreescribe en cada auditoria
     *
     * @see AuditoriaConfig se configura auditarIdUsuario retornara un AuditorAware<TipoDato> 
     * y registra quien modifico la operacion por la anotacion @CreatedBy
    */
    @JsonIgnore
    @CreatedBy
    @Column(name = "_registrado_por_id_usuario", updatable = false)
    private Long registradoPorIdUsuario;

    //Utilizar LocalDate para Fechas y LocalDateTime para fechas y horas
    @LastModifiedDate
    @Column(name = "_modificacion")
    private LocalDateTime fechaModificacion;

    
    /**
     * @see AuditoriaConfig se configura auditarIdUsuario retornara un AuditorAware<TipoDato> 
     * y registra quien modifico la operacion por la anotacion @LastModifiedBy
     */
    @JsonIgnore
    @LastModifiedBy
    @Column(name = "_modificado_por_id_usuario")
    private Long modificadoPorIdUsuario;

    //Anotacion que convierte Enum en STRING
    @Enumerated(EnumType.STRING)
    @Column(name = "_estado", length = 55)
    private Estado estado = Estado.A;
}
