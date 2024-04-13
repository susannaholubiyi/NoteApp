package africa.semicolon.services;

import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.CreatePageResponse;
import africa.semicolon.dtos.CreateNoteRequest;
import africa.semicolon.dtos.CreateNoteResponse;

public interface NoteServices {
    CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);

    CreatePageResponse createPage(CreatePageRequest createNotesRequest);
}
