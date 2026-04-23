package tablebook.backend.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public enum CrmErrorMessage {

        //USER
        USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
        USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User already exists"),
        //CUSTOMER
        CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "Customer not found"),
        CUSTOMER_ALREADY_EXISTS(HttpStatus.CONFLICT, "Customer already exists"),
        PEOPLE_COUNT_EXCEEDS_CAPACITY(HttpStatus.BAD_REQUEST, "People count exceeds capacity"),

        //FILES
        CREATE_DIRECTORY_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create a directory"),
        UPLOAD_IMAGE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image"),
        DELETE_IMAGE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete image"),
        IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "Image not found"),

        //TIME
        INVALID_TIME_FORMAT(HttpStatus.BAD_REQUEST, "Invalid time format"),
        INVALID_TIME_RANGE(HttpStatus.BAD_REQUEST, "Invalid time range"),


        //RESERVATION
        RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "Reservation not found"),
        RESERVATION_ALREADY_EXISTS(HttpStatus.CONFLICT, "Reservation already exists"),
        TABLE_NOT_AVAILABLE(HttpStatus.CONFLICT, "Table not available, there is a reservation for this time"),
        TABLE_NOT_FOUND(HttpStatus.NOT_FOUND, "Table not found, please check the table id"),
        RESERVATION_ALREADY_CANCELLED(HttpStatus.CONFLICT, "Reservation already cancelled"),
        RESERVATION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "Reservation already completed"),

        //GENERIC
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Something wrong happened"),
        BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request"),
        READ_IMAGE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to read image");

        private final HttpStatus httpStatus;
        private final String description;

        CrmErrorMessage(HttpStatus httpStatus, String description) {
            this.httpStatus = httpStatus;
            this.description = description;
        }


        public static HttpStatus getHttpStatus(CrmErrorMessage errorMessage) {
            return Arrays.stream(CrmErrorMessage.values())
                    .filter(element -> element.equals(errorMessage))
                    .findFirst()
                    .map(element -> element.getHttpStatus())
                    .orElse(null);

        }

        public static String getMessage(CrmErrorMessage errorMessage) {
            return Arrays.stream(CrmErrorMessage.values())
                    .filter(element -> element.equals(errorMessage))
                    .findFirst()
                    .map(CrmErrorMessage::getDescription)
                    .orElse(null);
        }

}

