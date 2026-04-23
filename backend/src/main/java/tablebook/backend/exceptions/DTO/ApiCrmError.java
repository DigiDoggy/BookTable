package tablebook.backend.exceptions.DTO;

import tablebook.backend.exceptions.CrmErrorMessage;

public record ApiCrmError(CrmErrorMessage code, String message) {
}
