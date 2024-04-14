package africa.semicolon.services;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.data.repositories.PageRepository;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.EditPageRequest;
import africa.semicolon.dtos.ViewPageRequest;
import africa.semicolon.dtos.ViewPageResponse;
import africa.semicolon.exceptions.NoteDoesNotExistException;
import africa.semicolon.exceptions.PageDoesNotExistException;
import africa.semicolon.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.utils.Helpers.validateIfEmpty;
import static africa.semicolon.utils.Helpers.validateIfNull;
import static africa.semicolon.utils.Mappers.mapViewPageResponse;

@Service
public class PageServicesImpl implements PageServices {
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page createPage(CreatePageRequest createPageRequest) {
        validateIfEmpty(createPageRequest.getTitle());
        validateIfEmpty(createPageRequest.getBody());
        validateIfEmpty(createPageRequest.getNoteName());
        validateIfNull(createPageRequest.getNoteName());
        validateIfNull(createPageRequest.getTitle());
        validateIfNull(createPageRequest.getBody());
        Page newPage = Mappers.mapCreatePage(createPageRequest);
        pageRepository.save(newPage);

        return newPage;
    }
@Override
    public Page editPage(EditPageRequest editPageRequest){
    validateEditPageRequest(editPageRequest);
    Note note = findExistingNoteBy(editPageRequest.getNoteName().toLowerCase());
    Optional<Page> optionalPage = getPage(editPageRequest,note);
    if (optionalPage.isEmpty()) {
        throw new PageDoesNotExistException("Page not found with ID: " + editPageRequest.getPageId());
    }
    Page page = optionalPage.get();
    Page editedPage = Mappers.mapEditPageRequest(editPageRequest, page);
    pageRepository.save(editedPage);
    return editedPage;
    }
    @Override
    public ViewPageResponse viewOneParticularPageWith(ViewPageRequest viewPageRequest) {
        validateViewPageRequest(viewPageRequest);
        Optional<Note> optionalNote = noteRepository.findByNoteName(viewPageRequest.getNoteName().toLowerCase().strip());
        checkIfNoteExistsWith(viewPageRequest, optionalNote);
        Optional<Page> optionalPage = getPageFrom(viewPageRequest, optionalNote);
        validatePage(optionalPage);
        Page page = optionalPage.get();
        pageRepository.save(page);
        return  mapViewPageResponse(page);
    }

    private static void validateViewPageRequest(ViewPageRequest viewPageRequest) {
        validateIfEmpty(viewPageRequest.getNoteName());
        validateIfEmpty(viewPageRequest.getPageId());
        validateIfNull(viewPageRequest.getNoteName());
        validateIfNull(viewPageRequest.getPageId());
    }
    private static Optional<Page> getPageFrom(ViewPageRequest viewPageRequest, Optional<Note> optionalNote) {
        Note note = optionalNote.get();
        Optional<Page> adOptional;
        adOptional = note.getPages().stream()
                .filter(ad -> ad.getId().equals(viewPageRequest.getPageId()))
                .findFirst();
        return adOptional;
    }
    private static void validatePage(Optional<Page> optionalPage) {
        if (optionalPage.isEmpty()) {
            throw new PageDoesNotExistException("Page not found: ");
        }
    }

    @Override
    public List<Page> viewAllPages() {
        return pageRepository.findAll();
    }
    private static void checkIfNoteExistsWith(ViewPageRequest viewPageRequest, Optional<Note> optionalNote) {
        if (optionalNote.isEmpty()) {
            throw new NoteDoesNotExistException(String.format("Note with note name %s not found: ", viewPageRequest.getNoteName()));
        }
    }

    private static void validateEditPageRequest(EditPageRequest editPageRequest) {
        validateIfEmpty(editPageRequest.getPageId());
        validateIfEmpty(editPageRequest.getNewBody());
        validateIfEmpty(editPageRequest.getNewTitle());
        validateIfEmpty(editPageRequest.getNoteName());
        validateIfNull(editPageRequest.getPageId());
        validateIfNull(editPageRequest.getNewTitle());
        validateIfNull(editPageRequest.getNewBody());
        validateIfNull(editPageRequest.getNoteName());
    }
    private Note findExistingNoteBy(String noteName) {
        Optional<Note> note =  noteRepository.findByNoteName(noteName.toLowerCase().strip());
        if(note.isEmpty()) throw new NoteDoesNotExistException(String.format("%s is not a registered note, kindly register", noteName));
        noteRepository.save(note.get());
        return note.get();
    }
    private static Optional<Page> getPage(EditPageRequest editPageRequest, Note note) {
        Optional<Page> pageOptional;
        pageOptional = note.getPages().stream()
                .filter(page -> page.getId().equals(editPageRequest.getPageId()))
                .findFirst();
        return pageOptional;
    }

}
