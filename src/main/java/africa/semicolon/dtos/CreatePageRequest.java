package africa.semicolon.dtos;

import lombok.Data;

@Data
public class CreatePageRequest {
    private String title;
    private String body;
    private String noteName;
}
