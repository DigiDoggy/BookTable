package tablebook.backend.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tablebook.backend.exceptions.DTO.ApiCrmError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CrmException.class)
    public ResponseEntity<ApiCrmError> handleCrmException(CrmException exception) {
        final CrmErrorMessage errorMessage = exception.getErrorMessage();
        final HttpStatus status = CrmErrorMessage.getHttpStatus(errorMessage);
        final ApiCrmError body = createApiCrmError(errorMessage);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(value = {
            MethodArgumentTypeMismatchException.class,
    })

    public ResponseEntity<ApiCrmError> handleExceptions(MethodArgumentTypeMismatchException exception) {
        final CrmErrorMessage errorMessage = CrmErrorMessage.BAD_REQUEST;
        final HttpStatus status = CrmErrorMessage.getHttpStatus(errorMessage);

        final String paramName = exception.getName();

        String requiredTypeName = "unknown";
        if (exception.getRequiredType() != null) {
            requiredTypeName = exception.getRequiredType().getSimpleName();
        }

        String providedTypeName = "unknown";
        if (exception.getValue() != null) {
            providedTypeName = exception.getValue().getClass().getSimpleName();
        }

        final String message = String.format(
                "Invalid value for parameter '%s'. Expected type: '%s', but got: '%s'.",
                paramName, requiredTypeName, providedTypeName
        );

        final ApiCrmError body = createApiCrmError(errorMessage, message);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCrmError> handleValidationExceptions(MethodArgumentNotValidException exception) {
        final Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach((error) -> {
                    final String fieldName = error.getField();
                    final String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        final CrmErrorMessage errorMessage = CrmErrorMessage.BAD_REQUEST;
        final HttpStatus status = CrmErrorMessage.getHttpStatus(errorMessage);
        final ApiCrmError body = new ApiCrmError(errorMessage, errors.toString());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCrmError> handleGenericException(Exception exception) {
        final CrmErrorMessage errorMessage = CrmErrorMessage.INTERNAL_SERVER_ERROR;
        final HttpStatus status = CrmErrorMessage.getHttpStatus(errorMessage);
        final ApiCrmError body = createApiCrmError(errorMessage);
        return ResponseEntity.status(status).body(body);
    }

    private ApiCrmError createApiCrmError(CrmErrorMessage errorMessage) {
        final String message = CrmErrorMessage.getMessage(errorMessage);
        return new ApiCrmError(errorMessage, message);
    }

    private ApiCrmError createApiCrmError(CrmErrorMessage errorMessage, String message) {
        return new ApiCrmError(errorMessage, message);
    }


}
