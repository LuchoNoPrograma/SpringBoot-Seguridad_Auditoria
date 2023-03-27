package security.example.security.model.enums;

/**
 * Manejar por enums para poder mantener consistencia en Registros
 * 
 * Para poder establecer en una columna de una entidad utilizar 
 * @Enumerated (EnumType.STRING)
 * 
 * Se persiste o modifica como tipo String en la BD
 */
public enum Estado {
    /**
     * Indicar que una entidad esta activa y disponible luego de ser inicializada por defecto
     */
    A,

    /**
     * Indicar que la entidad no esta disponible o fue eliminada, se conserva en BD
     */
    X
}
