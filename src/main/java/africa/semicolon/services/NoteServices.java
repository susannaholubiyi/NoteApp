package africa.semicolon.services;

import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;

public interface NoteServices {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
}
