package tablebook.backend.exceptions;

import lombok.Getter;

@Getter
public class CrmException extends RuntimeException{
    private final CrmErrorMessage errorMessage;

    public CrmException(CrmErrorMessage errorMessage){
        super();
        this.errorMessage = errorMessage;
    }
}
