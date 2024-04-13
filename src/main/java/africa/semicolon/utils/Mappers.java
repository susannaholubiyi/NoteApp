package africa.semicolon.utils;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.CreatePageResponse;
import africa.semicolon.dtos.CreateNoteRequest;
import africa.semicolon.dtos.CreateNoteResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mappers {
    public static Note mapRegisterUser(CreateNoteRequest createNoteRequest){
        Note note = new Note();
        note.setNoteName(createNoteRequest.getNoteName().toLowerCase().strip());
        note.setPassword(createNoteRequest.getPassword());

        return note;
    }
    public static CreateNoteResponse mapRegisterUser(Note savedUser){
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setId(savedUser.getId());
        createNoteResponse.setNoteName(savedUser.getNoteName().toLowerCase().strip());
        return createNoteResponse;
    }

    public static Page mapCreatePage(CreatePageRequest createPageRequest) {
        Page newPage = new Page();
        newPage.setTitle(createPageRequest.getTitle());
        newPage.setBody(createPageRequest.getBody());
        return newPage;
    }
    public static CreatePageResponse mapCreatePage(Page page){
        CreatePageResponse createPageResponse = new CreatePageResponse();
        createPageResponse.setTitle(page.getTitle());
        createPageResponse.setBody(page.getBody());
        createPageResponse.setPageId(page.getId());
        createPageResponse.setDateCreated(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        return createPageResponse;
    }
}
