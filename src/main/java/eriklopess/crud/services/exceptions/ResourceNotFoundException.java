package eriklopess.crud.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Integer id) {
        super("Data not found with id: " + id);
    }
}
