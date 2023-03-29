package security.example.security.exception.PersonaException;

public class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(String message) {
        super(message);
    }
}
