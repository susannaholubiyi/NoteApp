package africa.semicolon.dtos;

import lombok.Data;

@Data
public class EditPageResponse {
    private String dateEdited;
    private String newTitle;
    private String newBody;
}
