package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String noteName;
    private String password;
}
