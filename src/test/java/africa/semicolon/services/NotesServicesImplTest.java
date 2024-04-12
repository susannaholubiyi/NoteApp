package africa.semicolon.services;

import africa.semicolon.data.repositories.NoteRepository;
import africa.semicolon.dtos.RegisterUserRequest;
import africa.semicolon.exceptions.UsernameAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NotesServicesImplTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteServices noteServices;
    @BeforeEach
    public void setUp(){
        noteRepository.deleteAll();
    }

    @Test
    public void registerUserTest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setNoteName("notename");
        registerUserRequest.setPassword("password");

        noteServices.register(registerUserRequest);

        assertEquals(1, noteRepository.count());
    }
    @Test
    public void registerAnotherUserWithExistingUsername_usernameAlreadyExistsExceptionIsThrownTest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setNoteName("notename");
        registerUserRequest.setPassword("password");
        noteServices.register(registerUserRequest);
        assertEquals(1, noteRepository.count());
        assertThrows(UsernameAlreadyExistsException.class, ()->noteServices.register(registerUserRequest));
    }
    @Test
    public void registerUserWithNullUsername_nullPointerExceptionIsThrownTest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setNoteName(null);
        registerUserRequest.setPassword("password");
        assertThrows(NullPointerException.class,()->noteServices.register(registerUserRequest));
    }
    @Test
    public void registerSellerWithNullPassword_nullPointerExceptionIsThrownTest(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setNoteName("notename");
        registerUserRequest.setPassword(null);
        assertThrows(NullPointerException.class,()->noteServices.register(registerUserRequest));
    }

}