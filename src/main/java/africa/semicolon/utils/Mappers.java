package africa.semicolon.utils;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.*;

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
        createPageResponse.setDateCreated(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        createPageResponse.setTitle(page.getTitle());
        createPageResponse.setBody(page.getBody());
        createPageResponse.setPageId(page.getId());
        return createPageResponse;
    }
    public static Page mapEditPageRequest(EditPageRequest editPageRequest, Page page){
        page.setTitle(editPageRequest.getNewTitle());
        page.setBody(editPageRequest.getNewBody());
        return page;
    }
    public static EditPageResponse mapEditPageResponse(Page page){
        EditPageResponse editPageResponse = new EditPageResponse();
        editPageResponse.setDateEdited(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()));
        editPageResponse.setNewTitle(page.getTitle());
        editPageResponse.setNewBody(page.getBody());
        return editPageResponse;
    }
    public static ViewPageResponse mapViewPageResponse(Page page){
        ViewPageResponse viewPageResponse = new ViewPageResponse();
        viewPageResponse.setDateCreated(String.valueOf(page.getDateCreated()));
        viewPageResponse.setTitle(page.getTitle());
        viewPageResponse.setBody(page.getBody());
        return viewPageResponse;
    }
}
