package africa.semicolon.services;

import africa.semicolon.data.model.Page;
import africa.semicolon.dtos.*;

import java.util.List;

public interface NoteServices {
    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);

    CreatePageResponse createPage(CreatePageRequest createNotesRequest);

    EditPageResponse editPage(EditPageRequest editPageRequest);

    List<Page> viewAllPages();

    List<Page> viewPages(ViewAllPagesRequest viewAllPagesRequest);
}
