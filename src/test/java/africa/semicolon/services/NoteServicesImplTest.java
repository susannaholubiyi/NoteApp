package africa.semicolon.services;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.data.repositories.PageRepository;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.CreateNoteRequest;
import africa.semicolon.exceptions.NoteDoesNotExistException;
import africa.semicolon.exceptions.NoteNameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoteServicesImplTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteServices noteServices;
    @Autowired
    private PageRepository pageRepository;
    @BeforeEach
    public void setUp(){
        noteRepository.deleteAll();
        pageRepository.deleteAll();
    }

    @Test
    public void createNoteTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");

        noteServices.createNote(createNoteRequest);

        assertEquals(1, noteRepository.count());
    }
    @Test
    public void createAnotherNoteWithExistingNoteName_NoteNameAlreadyExistsExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        assertEquals(1, noteRepository.count());
        assertThrows(NoteNameAlreadyExistsException.class, ()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNoteWithNullNoteName_nullPointerExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName(null);
        createNoteRequest.setPassword("password");
        assertThrows(NullPointerException.class,()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNoteWithNullPassword_nullPointerExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword(null);
        assertThrows(NullPointerException.class,()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNoteWithEmptyPassword_nullPointerExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("");
        assertThrows(IllegalArgumentException.class,()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNoteWithEmptyNoteName_nullPointerExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("");
        createNoteRequest.setPassword("password");
        assertThrows(IllegalArgumentException.class,()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNote_createPageTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("notename");
        createPageRequest.setTitle("title");
        createPageRequest.setBody("body");
        noteServices.createPage(createPageRequest);

        note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, pageRepository.count());
        assertEquals(1, note.get().getPages().size());
    }
    @Test
    public void createPageWithoutCreatingNote_noteDoesNotExistExceptionIsThrownTest(){
        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("notename");
        createPageRequest.setTitle("title");
        createPageRequest.setBody("body");
        assertThrows(NoteDoesNotExistException.class, ()->noteServices.createPage(createPageRequest));


    }

}