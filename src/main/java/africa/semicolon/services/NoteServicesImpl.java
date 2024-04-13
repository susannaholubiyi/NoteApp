package africa.semicolon.services;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.CreatePageResponse;
import africa.semicolon.dtos.CreateNoteRequest;
import africa.semicolon.dtos.CreateNoteResponse;
import africa.semicolon.exceptions.NoteDoesNotExistException;
import africa.semicolon.exceptions.NoteNameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.utils.Helpers.validateIfEmpty;
import static africa.semicolon.utils.Helpers.validateIfNull;
import static africa.semicolon.utils.Mappers.*;

@Service
public class NoteServicesImpl implements NoteServices{
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PageServices pageServices;
    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        createNoteRequest.setNoteName(createNoteRequest.getNoteName().toLowerCase());
        validateUserName(createNoteRequest.getNoteName());
        validateIfNull(createNoteRequest.getNoteName());
        validateIfNull(createNoteRequest.getPassword());
        validateIfEmpty(createNoteRequest.getNoteName());
        validateIfEmpty(createNoteRequest.getPassword());
        Note note = mapRegisterUser(createNoteRequest);
        note.setLocked(false);
        noteRepository.save(note);
        return mapRegisterUser(note);
    }

    @Override
    public CreatePageResponse createPage(CreatePageRequest createPageRequest) {
        Note note = findNoteBy(createPageRequest.getNoteName().toLowerCase());
        Page newPage = pageServices.createPage(createPageRequest);
        note.getPages().add(newPage);
        noteRepository.save(note);
        return mapCreatePage(newPage);
    }

    private void validateUserName(String username) {
        boolean usernameExists = noteRepository.existsByNoteName(username.toLowerCase().strip());
        if(usernameExists) throw new NoteNameAlreadyExistsException(String.format("%s is a registered seller", username));
    }
    private Note findNoteBy(String noteName) {
        Optional<Note> note =  noteRepository.findByNoteName(noteName.toLowerCase().strip());
        if(note.isEmpty()) throw new NoteDoesNotExistException(String.format("%s is not a registered note, kindly register", noteName));
        noteRepository.save(note.get());
        return note.get();
    }
}
