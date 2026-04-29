package tablebook.backend.dto.request;

public record UserDTO(
    String email,
    String password,
    String comment,
    String userName,
    String phoneNumber,
    String orders,
    String likeZone
) {
}
