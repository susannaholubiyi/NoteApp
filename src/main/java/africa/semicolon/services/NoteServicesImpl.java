package africa.semicolon.services;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.dtos.RegisterUserResponse;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Helpers.validateIfEmpty;
import static africa.semicolon.utils.Helpers.validateIfNull;
import static africa.semicolon.utils.Mappers.mapRegisterUser;

@Service
public class NoteServicesImpl implements NoteServices{
    @Autowired
    private NoteRepository noteRepository;
    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        registerUserRequest.setNoteName(registerUserRequest.getNoteName().toLowerCase());
        validateUserName(registerUserRequest.getNoteName());
        validateIfNull(registerUserRequest.getNoteName());
        validateIfNull(registerUserRequest.getPassword());
        validateIfEmpty(registerUserRequest.getNoteName());
        validateIfEmpty(registerUserRequest.getPassword());
        Note note = mapRegisterUser(registerUserRequest);
        note.setLocked(false);
        noteRepository.save(note);
        return mapRegisterUser(note);
    }
    private void validateUserName(String username) {
        boolean usernameExists = noteRepository.existsByNoteName(username.toLowerCase().strip());
        if(usernameExists) throw new UsernameAlreadyExistsException(String.format("%s is a registered seller", username));
    }
}
