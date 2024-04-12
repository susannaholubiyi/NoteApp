package africa.semicolon.utils;

import africa.semicolon.data.model.Note;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;

public class Mappers {
    public static Note mapRegisterUser(RegisterUserRequest registerUserRequest){
        Note note = new Note();
        note.setNoteName(registerUserRequest.getNoteName().toLowerCase().strip());
        note.setPassword(registerUserRequest.getPassword());

        return note;
    }
    public static RegisterUserResponse mapRegisterUser(Note savedUser){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId(savedUser.getId());
        registerUserResponse.setNoteName(savedUser.getNoteName().toLowerCase().strip());
        return registerUserResponse;
    }
}
