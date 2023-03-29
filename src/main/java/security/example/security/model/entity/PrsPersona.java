package security.example.security.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import security.example.security.model.entity.auditory.AuditoriaRevision;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "prs_persona")
public class PrsPersona extends AuditoriaRevision{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    private String nombres;

    private String paterno;

    private String materno;

    @Column(unique = true)
    private String dip;

    @Column(unique = true)
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    //No se persiste en DB, muy util para evitar mucho texto en Thymeleaf
	@Transient
	private String nombreCompleto;

	public String getNombreCompleto() {
		return nombres + " " + paterno + " " + materno;
	}

    public PrsPersona(String nombres, String paterno, String materno, String dip, String email,
            LocalDate fechaNacimiento) {
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.dip = dip;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres.toUpperCase();
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno.toUpperCase();
    }

    public void setMaterno(String materno) {
        this.materno = materno.toUpperCase();
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
