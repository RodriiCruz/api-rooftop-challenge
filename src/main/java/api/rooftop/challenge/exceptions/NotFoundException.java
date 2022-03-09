package api.rooftop.challenge.exceptions;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}
