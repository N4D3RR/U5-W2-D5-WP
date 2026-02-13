package naderdeghaili.u5w2d5wp.exceptions;

import java.util.UUID;


public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Dipendente con id " + id + " Non trovato");
    }
}
