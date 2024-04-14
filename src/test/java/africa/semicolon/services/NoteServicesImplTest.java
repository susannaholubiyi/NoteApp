package africa.semicolon.services;

import africa.semicolon.data.model.Note;
import africa.semicolon.data.model.Page;
import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.data.repositories.PageRepository;
import africa.semicolon.dtos.*;
import africa.semicolon.exceptions.NoteDoesNotExistException;
import africa.semicolon.exceptions.NoteNameAlreadyExistsException;
import africa.semicolon.exceptions.PageDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
    public void createNoteWithEmptyPassword_IllegalArgumentExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("");
        assertThrows(IllegalArgumentException.class,()->noteServices.createNote(createNoteRequest));
    }
    @Test
    public void createNoteWithEmptyNoteName_IllegalArgumentExceptionIsThrownTest(){
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
    public void createNote_createPageWithDifferentNoteNameCasingTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("Notename");
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
    @Test
    public void createNote_createPageWithEmptyNoteName_NoteDoesNotExistExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("");
        createPageRequest.setTitle("title");
        createPageRequest.setBody("body");
        assertThrows(NoteDoesNotExistException.class,()->noteServices.createPage(createPageRequest));
    }
    @Test
    public void createNote_createPageWithEmptyTitle_IllegalArgumentExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("notename");
        createPageRequest.setTitle("");
        createPageRequest.setBody("body");
        assertThrows(IllegalArgumentException.class,()->noteServices.createPage(createPageRequest));
    }
    @Test
    public void createNote_createPageWithNullTitle_NullPointerExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("notename");
        createPageRequest.setTitle(null);
        createPageRequest.setBody("body");
        assertThrows(NullPointerException.class,()->noteServices.createPage(createPageRequest));
    }
    @Test
    public void createNote_createPageWithEmptyBody_IllegalArgumentExceptionIsThrownTest(){
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
        createPageRequest.setBody("");
        assertThrows(IllegalArgumentException.class,()->noteServices.createPage(createPageRequest));
    }
    @Test
    public void createNote_createPageWithNullBody_NullPointerExceptionIsThrownTest(){
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
        createPageRequest.setBody(null);
        assertThrows(NullPointerException.class,()->noteServices.createPage(createPageRequest));
    }
    @Test
    public void createNote_createTwoPages_viewAllPagesTest(){
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

        CreatePageRequest createPageRequest2 = new CreatePageRequest();
        createPageRequest2.setNoteName("notename");
        createPageRequest2.setTitle("title2");
        createPageRequest2.setBody("body2");
        noteServices.createPage(createPageRequest2);

        note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(2, pageRepository.count());
        assertEquals(2, note.get().getPages().size());

        List<Page> pages = noteServices.viewAllPages();
        assertEquals(2, pages.size());
    }

    @Test
    public void createTwoNotes_createTwoPagesInNoteOne_createOnePageInNoteTwo_viewAllPagesInNoteOneTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);

        CreateNoteRequest createNoteRequest2 = new CreateNoteRequest();
        createNoteRequest2.setNoteName("notename2");
        createNoteRequest2.setPassword("password2");
        noteServices.createNote(createNoteRequest2);

        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        Optional<Note> note2 = noteRepository.findByNoteName("notename2");
        assertTrue(note2.isPresent());
        assertEquals(2, noteRepository.count());

        CreatePageRequest createPageRequest = new CreatePageRequest();
        createPageRequest.setNoteName("notename");
        createPageRequest.setTitle("title");
        createPageRequest.setBody("body");
        noteServices.createPage(createPageRequest);

        CreatePageRequest createPageRequest2 = new CreatePageRequest();
        createPageRequest2.setNoteName("notename");
        createPageRequest2.setTitle("title2");
        createPageRequest2.setBody("body2");
        noteServices.createPage(createPageRequest2);

        CreatePageRequest createPageRequest3 = new CreatePageRequest();
        createPageRequest3.setNoteName("notename2");
        createPageRequest3.setTitle("title3");
        createPageRequest3.setBody("body3");
        noteServices.createPage(createPageRequest3);

        note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        note2 = noteRepository.findByNoteName("notename2");
        assertTrue(note2.isPresent());
        assertEquals(3, pageRepository.count());
        assertEquals(2, note.get().getPages().size());
        assertEquals(1, note2.get().getPages().size());

        ViewAllPagesRequest viewAllPagesRequest = new ViewAllPagesRequest();
        viewAllPagesRequest.setNoteName("notename");
        List<Page> pages = noteServices.viewPages(viewAllPagesRequest);
        assertEquals(2, pages.size());
    }


    @Test
    public void createNote_createPage_editPageTest(){
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
        Page page = note.get().getPages().get(0);
        assertEquals("title", page.getTitle());

        EditPageRequest editPageRequest = new EditPageRequest();
        page = note.get().getPages().get(0);
        editPageRequest.setPageId(page.getId());
        editPageRequest.setNoteName("notename");
        editPageRequest.setNewTitle("new title");
        editPageRequest.setNewBody("new body");
        noteServices.editPage(editPageRequest);

        note = noteRepository.findByNoteName("notename");
        pageRepository.findById(note.get().getPages().get(0).getId());
        System.out.println(note.get().getPages());
        assertTrue(note.isPresent());
        assertEquals(1, pageRepository.count());
        assertEquals("new title", note.get().getPages().get(0).getTitle());

    }
    @Test
    public void createNote_editPageWithoutCreatingOne_PageDoesNotExistExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        EditPageRequest editPageRequest = new EditPageRequest();
        editPageRequest.setPageId("1");
        editPageRequest.setNoteName("notename");
        editPageRequest.setNewTitle("new title");
        editPageRequest.setNewBody("new body");
        assertThrows(PageDoesNotExistException.class, ()->noteServices.editPage(editPageRequest));
    }
    @Test
    public void createNote_editPageWithDifferentNoteName_PageDoesNotExistExceptionIsThrownTest(){
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
        Page page = note.get().getPages().get(0);
        assertEquals("title", page.getTitle());

        EditPageRequest editPageRequest = new EditPageRequest();
        page = note.get().getPages().get(0);
        editPageRequest.setPageId(page.getId());
        editPageRequest.setNoteName("notename2");
        editPageRequest.setNewTitle("new title");
        editPageRequest.setNewBody("new body");
        assertThrows(NoteDoesNotExistException.class, ()->noteServices.editPage(editPageRequest));
    }
    @Test
    public void createNote_createTwoPages_viewOnePageTest(){
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

        CreatePageRequest createPageRequest2 = new CreatePageRequest();
        createPageRequest2.setNoteName("notename");
        createPageRequest2.setTitle("title two");
        createPageRequest2.setBody("body two");
        noteServices.createPage(createPageRequest);

        note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(2, pageRepository.count());
        assertEquals(2, note.get().getPages().size());

        ViewPageRequest viewPageRequest = new ViewPageRequest();
        Page page = note.get().getPages().get(0);
        viewPageRequest.setNoteName("notename");
        viewPageRequest.setPageId(page.getId());
        ViewPageResponse response = noteServices.viewOneParticularPageWith(viewPageRequest);
        assertNotNull(response);
        assertEquals(page.getTitle(), response.getTitle());
    }
    @Test
    public void createNote_viewAPageThatDoesNotExist_PageDoesNotExistExceptionIsThrownTest(){
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setNoteName("notename");
        createNoteRequest.setPassword("password");
        noteServices.createNote(createNoteRequest);
        Optional<Note> note = noteRepository.findByNoteName("notename");
        assertTrue(note.isPresent());
        assertEquals(1, noteRepository.count());

        ViewPageRequest viewPageRequest = new ViewPageRequest();
        viewPageRequest.setNoteName("notename");
        viewPageRequest.setPageId("1");
        assertThrows(PageDoesNotExistException.class, ()->noteServices.viewOneParticularPageWith(viewPageRequest)) ;

    }
    @Test
    public void iewAPageOfNoteThatDoesNotExist_NoteDoesNotExistExceptionIsThrownTest(){
        ViewPageRequest viewPageRequest = new ViewPageRequest();
        viewPageRequest.setNoteName("notename");
        viewPageRequest.setPageId("1");
        assertThrows(NoteDoesNotExistException.class, ()->noteServices.viewOneParticularPageWith(viewPageRequest)) ;

    }


}