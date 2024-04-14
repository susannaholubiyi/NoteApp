package africa.semicolon.controllers;

import africa.semicolon.dtos.ApiResponse;
import africa.semicolon.dtos.CreateNoteRequest;
import africa.semicolon.dtos.CreatePageRequest;
import africa.semicolon.dtos.ViewAllPagesRequest;
import africa.semicolon.services.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private NoteServices noteServices;
@PostMapping("/create-note")
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest createNoteRequest){
        try {
            var response =  noteServices.createNote(createNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create-page")
    public ResponseEntity<?> createPage(@RequestBody CreatePageRequest createPageRequest){
        try {
            var response =  noteServices.createPage(createPageRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view-all-pages")
    public ResponseEntity<?> viewAllPages(){
        try {
            var response = noteServices.viewAllPages();
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view-pages-in-a-note")
    public ResponseEntity<?> viewAParticularNotePages(@RequestBody ViewAllPagesRequest viewAllPagesRequest){
        try {
            var response = noteServices.viewPages(viewAllPagesRequest);
            return new ResponseEntity<>(new ApiResponse(true,response), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
