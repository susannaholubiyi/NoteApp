package africa.semicolon.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String noteName;
    private String password;
}
